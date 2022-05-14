plugins {
    kotlin("jvm") version "1.6.21"
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    val slf4j = "1.7.36"

    implementation("org.slf4j:slf4j-api:$slf4j")
    implementation("org.slf4j:slf4j-simple:$slf4j")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.20")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.6"
        }
    }

    register("addJniLibToResources") {
        mustRunAfter(":jni:buildToxcore")
        dependsOn(":jni:build")

        doLast {
            val jniLib = System.mapLibraryName("tox4j-jni")
            file("${project(":jni").buildDir}/lib/main/debug/$jniLib")
                .copyTo(
                    file("$projectDir/src/main/resources/ltd/toktok/tox4j/${OSChecker.platform}/$jniLib"),
                    overwrite = true
                )
        }
    }

    register("buildHost") {
        mustRunAfter("addJniLibToResources")
        dependsOn("build")
    }
}
