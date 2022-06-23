package ltd.toktok.tox4j.gen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import ltd.toktok.tox4j.gen.ir.*
import java.io.File

private const val HANDLE_NAME = "handle"

private data class FunSpecResult(val funSpec: FunSpec, val externalFunSpec: FunSpec?)
private data class PropertySpecResult(
    val propertySpec: PropertySpec,
    val externalGetterFunSpec: FunSpec?,
    val externalSetterFunSpec: FunSpec?
)

class ClassBuilder(
    private val clazz: IRClass,
    private val interfacesDir: File,
    private val inner: Boolean = false,
    private val outerClasses: List<IRClass> = listOf()
) {
    private val className = clazz.name + if (inner) "Impl" else ""
    private val classBuilder =
        if (clazz.isCallback) TypeSpec.funInterfaceBuilder(className) else TypeSpec.classBuilder(className)
    private var companionObjectBuilder: TypeSpec.Builder? = null

    private fun requireCompanionObjectBuilder(): TypeSpec.Builder {
        if (companionObjectBuilder == null) {
            companionObjectBuilder = TypeSpec.companionObjectBuilder()
        }
        return companionObjectBuilder!!
    }

    fun build(): TypeSpec {
        clazz.handle?.let { handle ->
            var parameterSpecBuilder: ParameterSpec.Builder? = null
            val propertySpecBuilder: PropertySpec.Builder
            val constructorBuilder: FunSpec.Builder = FunSpec.constructorBuilder()

            when (handle) {
                is IRNativeHandle -> {
                    if (handle.allocFunc == null) {
                        parameterSpecBuilder = ParameterSpec.builder(HANDLE_NAME, Long::class)
                    }
                    propertySpecBuilder = PropertySpec.builder(HANDLE_NAME, Long::class, KModifier.INTERNAL)
                }
                is IRNumberHandle -> {
                    val handleType = buildType(handle.type)
                    parameterSpecBuilder = ParameterSpec.builder(HANDLE_NAME, handleType)
                    propertySpecBuilder = PropertySpec.builder(HANDLE_NAME, handleType, KModifier.INTERNAL)
                }
            }

            parameterSpecBuilder?.build()?.let {
                constructorBuilder.addParameter(it)
                propertySpecBuilder.initializer(HANDLE_NAME)
            } ?: run {
                if (handle is IRNativeHandle) {
                    if (handle.allocFunc != null) {
                        if (handle.allocFunc.params.isNotEmpty()) {
                            constructorBuilder.apply {
                                for (param in handle.allocFunc.params) {
                                    addParameter(buildFuncArg(param, originalType = true))
                                }
                            }
                        }

                        val allocFunc = buildFunction(handle.allocFunc, private = true).funSpec

                        requireCompanionObjectBuilder().addFunction(allocFunc)
                        propertySpecBuilder.initializer(
                            "%N(%L)",
                            allocFunc.name,
                            allocFunc.parameters.joinToString(separator = ", ") { "${it.name}.handle" },
                        )
                    }

                    val deallocFunc = buildFunction(handle.deallocFunc, private = true).funSpec
                    requireCompanionObjectBuilder().addFunction(deallocFunc)
                }
            }

            if (inner) {
                constructorBuilder.addModifiers(KModifier.PRIVATE)
                classBuilder.primaryConstructor(constructorBuilder.build())
            } else if (constructorBuilder.parameters.isNotEmpty()) {
                classBuilder.primaryConstructor(constructorBuilder.build())
            }
            classBuilder.addProperty(propertySpecBuilder.build())
        }

        for (property in clazz.properties) {
            if (property.isStatic) {
                companionObjectBuilder =
                    requireCompanionObjectBuilder().addProperty(buildProperty(property).propertySpec)
            } else {
                val (propertySpec, externalGetterFunSpec, externalSetterFunSpec) = buildProperty(
                    property,
                    abstract = clazz.isCallback
                )
                classBuilder.addProperty(propertySpec)
                if (externalGetterFunSpec != null) {
                    classBuilder.addFunction(externalGetterFunSpec)
                }
                if (externalSetterFunSpec != null) {
                    classBuilder.addFunction(externalSetterFunSpec)
                }
            }
        }

        for (function in clazz.functions) {
            if (function.isStatic) {
                requireCompanionObjectBuilder().addFunction(
                    buildFunction(
                        function,
                        abstract = clazz.isCallback
                    ).funSpec
                )
            } else {
                val (funSpec, externalFunSpec) = buildFunction(
                    function,
                    abstract = clazz.isCallback
                )
                classBuilder.addFunction(funSpec)
                if (externalFunSpec != null) {
                    classBuilder.addFunction(externalFunSpec)
                }
            }
        }

        if (inner) {
            classBuilder.addModifiers(KModifier.INTERNAL, KModifier.INNER)
                .addSuperinterface(ClassName(PACKAGE_NAME, clazz.name))
        }

        for (innerClass in clazz.innerClasses) {
            val interfaceName = innerClass.name
            FileSpec.builder(PACKAGE_NAME, interfaceName)
                .indent("    ")
                .addType(buildInterface(innerClass))
                .build()
                .writeTo(interfacesDir)
            classBuilder.addType(
                ClassBuilder(
                    innerClass,
                    interfacesDir,
                    inner = true,
                    outerClasses = outerClasses + if (outerClasses.isEmpty()) clazz
                    else clazz.copy(name = className)
                ).build()
            )
        }

        if (companionObjectBuilder != null) {
            classBuilder.addType(requireCompanionObjectBuilder().build())
        }

        return classBuilder.build()
    }

    private fun buildInterface(clazz: IRClass): TypeSpec {
        val interfaceBuilder = TypeSpec.interfaceBuilder(clazz.name)
            .addModifiers(KModifier.SEALED)

        for (property in clazz.properties) {
            interfaceBuilder.addProperty(buildProperty(property, abstract = true).propertySpec)
        }

        for (function in clazz.functions) {
            interfaceBuilder.addFunction(
                buildFunction(
                    function,
                    abstract = true
                ).funSpec
            )
        }

        return interfaceBuilder.build()
    }

    private fun buildProperty(
        property: IRProperty,
        abstract: Boolean = false,
    ): PropertySpecResult {
        val propertyName = snakeCaseToCamelCase(property.name)
        val propertyBuilder = PropertySpec.builder(
            propertyName,
            buildType(property.getter.returnType.type)
        )
        var resultExternalGetterFunSpec: FunSpec? = null
        var resultExternalSetterFunSpec: FunSpec? = null

        if (abstract) {
            propertyBuilder.addModifiers(KModifier.ABSTRACT)
        } else {
            if (inner) {
                propertyBuilder.addModifiers(KModifier.OVERRIDE)
            }

            val getterBuilder = FunSpec.getterBuilder()
            val setterBuilder = FunSpec.setterBuilder()

            if (property.isStatic) {
                propertyBuilder.addAnnotation(JvmStatic::class)
                getterBuilder.addModifiers(KModifier.EXTERNAL)
                setterBuilder.addModifiers(KModifier.EXTERNAL)
            } else {
                val (getterFunSpec, externalGetterFunSpec) = buildFunction(
                    property.getter.copy(name = snakeCaseToCamelCase("get_${property.name}")),
                    private = true,
                    abstract = abstract,
                    getterOrSetter = true,
                )
                getterBuilder.addCode(getterFunSpec.body)
                resultExternalGetterFunSpec = externalGetterFunSpec

                if (property.setter != null) {
                    val setterWithUnitReturnType = property.setter.let { setter ->
                        setter.copy(
                            name = snakeCaseToCamelCase("set_${property.name}"),
                            returnType = setter.returnType.let { returnType ->
                                returnType.copy(
                                    type = returnType.type.copy(name = "void")
                                )
                            }
                        )
                    }
                    val (setterFunSpec, externalSetterFunSpec) = buildFunction(
                        setterWithUnitReturnType,
                        private = true,
                        abstract = abstract,
                        getterOrSetter = true,
                    )
                    setterBuilder.addParameters(setterFunSpec.parameters)
                        .addCode(setterFunSpec.body)
                    resultExternalSetterFunSpec = externalSetterFunSpec
                }
            }

            propertyBuilder.getter(getterBuilder.build())
            if (property.setter != null) {
                propertyBuilder.mutable()
                    .setter(setterBuilder.build())
            }

            property.getter.throws?.let { exceptionName ->
                propertyBuilder.addAnnotation(
                    AnnotationSpec.builder(Throws::class)
                        .addMember(
                            "%T::class",
                            ClassName(PACKAGE_NAME + ERRORS_SUB_PACKAGE, exceptionName + EXCEPTION_SUFFIX)
                        )
                        .useSiteTarget(AnnotationSpec.UseSiteTarget.GET)
                        .build()
                )
            }
            property.setter?.throws?.let { exceptionName ->
                propertyBuilder.addAnnotation(
                    AnnotationSpec.builder(Throws::class)
                        .addMember(
                            "%T::class",
                            ClassName(PACKAGE_NAME + ERRORS_SUB_PACKAGE, exceptionName + EXCEPTION_SUFFIX)
                        )
                        .useSiteTarget(AnnotationSpec.UseSiteTarget.SET)
                        .build()
                )
            }
        }

        return PropertySpecResult(propertyBuilder.build(), resultExternalGetterFunSpec, resultExternalSetterFunSpec)
    }

    private fun buildFunction(
        function: IRFunction,
        private: Boolean = false,
        abstract: Boolean = false,
        getterOrSetter: Boolean = false,
    ): FunSpecResult {
        var externalFunSpec: FunSpec? = null
        val functionBuilder = FunSpec.builder(snakeCaseToCamelCase(function.name))
            .returns(buildType(function.returnType.type))

        if (abstract) {
            functionBuilder.addModifiers(KModifier.ABSTRACT)
        } else {
            if (inner) {
                functionBuilder.addModifiers(KModifier.OVERRIDE)
            }

            if (function.isStatic) {
                functionBuilder.addModifiers(KModifier.EXTERNAL)
                    .addAnnotation(JvmStatic::class)
            } else {
                functionBuilder.addCode(
                    com.squareup.kotlinpoet.buildCodeBlock {
                        add("return ${if (getterOrSetter) "" else "\n"}")
                        if (!getterOrSetter) indent()

                        addStatement("${snakeCaseToCamelCase(function.name)}(")
                        withIndent {
                            outerClasses.plus(clazz.copy(name = className))
                                .map { "this@${it.name}.handle" }
                                .plus(
                                    function.params.map {
                                        val param = it.toParam()
                                        val paramSpec = buildParam(param)
                                        if (param.type.containsNumberHandle) {
                                            "(${paramSpec.name} as ${param.type.name + "Impl"}).handle"
                                        } else {
                                            paramSpec.name
                                        }
                                    }
                                )
                                .forEach { addStatement("$it,") }
                        }
                        add(")")

                        if (!getterOrSetter) unindent()
                        add("\n")
                    }
                )

                val externalFunctionBuilder = FunSpec.builder(snakeCaseToCamelCase(function.name))
                    .addModifiers(KModifier.PRIVATE, KModifier.EXTERNAL)
                    .returns(buildType(function.returnType.type))

                function.throws?.let { exceptionName ->
                    externalFunctionBuilder.addAnnotation(
                        AnnotationSpec.builder(Throws::class)
                            .addMember(
                                "%T::class",
                                ClassName(PACKAGE_NAME + ERRORS_SUB_PACKAGE, exceptionName + EXCEPTION_SUFFIX)
                            )
                            .build()
                    )
                }

                for (outerClass in outerClasses.plus(clazz.copy(name = className))) {
                    externalFunctionBuilder.addParameter(
                        name = outerClass.name.let {
                            it[0].lowercaseChar() + it.substring(1) + "Handle"
                        },
                        type = when (val handle = outerClass.handle!!) {
                            is IRNativeHandle -> LONG
                            is IRNumberHandle -> buildType(handle.type)
                        },
                    )
                }

                for (funcArg in function.params) {
                    externalFunctionBuilder.addParameter(buildFuncArg(funcArg, originalType = true))
                }

                externalFunSpec = externalFunctionBuilder.build()
            }
        }

        if (private) {
            functionBuilder.addModifiers(KModifier.PRIVATE)
        }

        function.throws?.let { exceptionName ->
            functionBuilder.addAnnotation(
                AnnotationSpec.builder(Throws::class)
                    .addMember(
                        "%T::class",
                        ClassName(PACKAGE_NAME + ERRORS_SUB_PACKAGE, exceptionName + EXCEPTION_SUFFIX)
                    )
                    .build()
            )
        }

        for (funcArg in function.params) {
            functionBuilder.addParameter(buildFuncArg(funcArg))
        }

        return FunSpecResult(functionBuilder.build(), externalFunSpec)
    }

    private fun IRFuncArg.toParam(): IRParam = when (this) {
        is IRBufferWrapper -> bufferParam
        is IRParam -> this
    }

    private fun buildFuncArg(funcArg: IRFuncArg, originalType: Boolean = false): ParameterSpec =
        buildParam(funcArg.toParam(), originalType)

    private fun buildParam(param: IRParam, originalType: Boolean = false): ParameterSpec =
        if (originalType && param.type.containsNumberHandle) {
            ParameterSpec.builder(
                snakeCaseToCamelCase(param.name) + "ImplHandle",
                INT,
            ).build()
        } else {
            ParameterSpec.builder(
                snakeCaseToCamelCase(param.name),
                buildType(if (originalType && param.replacedType != null) param.replacedType else param.type)
            ).build()
        }

    private val typesMap: Map<String, ClassName> = mutableMapOf(
        "byte" to BYTE,
        "short" to SHORT,
        "ushort" to SHORT,
        "uint" to INT,
        "ulong" to LONG,
        "char" to CHAR,
        "bool" to BOOLEAN,
        "void" to UNIT,
    )

    private fun buildType(type: IRType): TypeName =
        if (type.name in typesMap) {
            val typeName: TypeName? = typesMap[type.name]
            if ((typeName == CHAR && type.isArray) || type.actsAsString) {
                STRING
            } else if (type.isArray) {
                ARRAY.parameterizedBy(typeName!!)
            } else {
                typeName!!
            }
        } else if (type.isArray) {
            ARRAY.parameterizedBy(ClassName(PACKAGE_NAME, type.name))
        } else if (type.name.endsWith("Cb")) {
            ClassName(PACKAGE_NAME + CALLBACKS_SUB_PACKAGE, type.name)
        } else {
            ClassName(PACKAGE_NAME, type.name)
        }
}