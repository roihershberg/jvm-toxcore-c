package ltd.toktok.tox4j.gen

import com.squareup.kotlinpoet.*
import ltd.toktok.tox4j.gen.ir.*
import java.io.File

const val PACKAGE_NAME = "ltd.toktok.tox4j"
const val ERRORS_SUB_PACKAGE = ".errors"
const val CALLBACKS_SUB_PACKAGE = ".callbacks"

const val EXCEPTION_SUFFIX = "Exception"

fun snakeCaseToCamelCase(string: String): String =
    string.split("_")
        .mapIndexed { index, word ->
            if (index == 0) {
                word
            } else {
                word[0].uppercaseChar() + word.substring(1)
            }
        }
        .joinToString("")

object KotlinAPIGen {
    fun generate(root: Root, dir: File) {
        for (enum in root.enums) {
            val enumFile = FileSpec.builder(
                if (enum.name.startsWith("ToxErr")) PACKAGE_NAME + ERRORS_SUB_PACKAGE else PACKAGE_NAME,
                enum.name
            )
                .indent("    ")
                .addType(buildEnum(enum))
                .build()

            enumFile.writeTo(dir)
        }

        for (exception in root.exceptions) {
            val exceptionFile = FileSpec.builder(
                PACKAGE_NAME + ERRORS_SUB_PACKAGE,
                exception.name + EXCEPTION_SUFFIX
            )
                .indent("    ")
                .addType(buildException(exception))
                .build()

            exceptionFile.writeTo(dir)
        }

        for (clazz in root.classes) {
            val classFile = FileSpec.builder(
                if (clazz.isCallback) PACKAGE_NAME + CALLBACKS_SUB_PACKAGE else PACKAGE_NAME,
                clazz.name
            )
                .indent("    ")
                .addType(ClassBuilder(clazz, dir).build())
                .build()

            classFile.writeTo(dir)
        }
    }

    private fun buildEnum(enum: IREnum): TypeSpec {
        val enumBuilder = TypeSpec.enumBuilder(enum.name)

        for (enumValue in enum.values) {
            enumBuilder.addEnumConstant(enumValue.name)
        }

        return enumBuilder.build()
    }

    private fun buildException(exception: IRException): TypeSpec {
        val enumClass = ClassName(PACKAGE_NAME + ERRORS_SUB_PACKAGE, exception.enum_name)

        return TypeSpec.classBuilder(exception.name + EXCEPTION_SUFFIX)
            .primaryConstructor(
                FunSpec.constructorBuilder()
                    .addParameter(
                        "errorCode",
                        enumClass,
                    )
                    .build()
            )
            .superclass(RuntimeException::class)
            .addSuperclassConstructorParameter("errorCode.name")
            .addProperty(
                PropertySpec.builder("errorCode", enumClass)
                    .initializer("errorCode")
                    .build()
            )
            .build()
    }

}
