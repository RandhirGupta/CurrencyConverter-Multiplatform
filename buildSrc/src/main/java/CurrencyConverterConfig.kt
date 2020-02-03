object CurrencyConverterConfig {

    private const val kotlinVersion = "1.3.61"
    private const val kotlinXCoroutines = "1.2.1"
    private const val serialization = "0.11.1"
    private const val ktor = "1.2.2"

    const val version = "1.0"


    object SdkVersion {
        const val compileSdkVersion = 29
        const val targetSdkVersion = 29
        const val minSdkVersion = 22
    }

    object Plugins {
        const val androidPlugin = "com.android.tools.build:gradle:3.5.1"
        const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    }

    object Libs {

        object Kotlin {

            const val jvm = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
            const val kotlinStdlibCommon =
                "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion"
            const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        }

        object Support {
            private const val buildToolVersion = "29.0.3"

            const val appCompat = "androidx.appcompat:appcompat:1.0.0-alpha1"
            const val design = "com.android.support:design:$buildToolVersion"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
            const val multiDex = "com.android.support:multidex:1.0.3"
            const val annotations = "com.android.support:support-annotations:$buildToolVersion"
            const val materialDesign = "com.google.android.material:material:1.0.0-alpha1"
        }

        object Test {

            const val junit = "junit:junit:4.12"

            const val kotlinxCoroutinesTest =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinXCoroutines"

            const val kotlinTestCommon = "org.jetbrains.kotlin:kotlin-test-common:$kotlinVersion"

            const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion"

            const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:$kotlinVersion"
        }

        object AndroidTest {

            const val testRunner = "androidx.test:runner:1.1.0"
            const val testRules = "androidx.test:rules:1.1.0"
        }

        object KotlinX {

            const val kotlinxCoroutinesCoreCommon =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$kotlinXCoroutines"

            const val kotlinxCoroutinesCoreJs =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$kotlinXCoroutines"

            const val kotlinxCoroutinesCoreNative =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$kotlinXCoroutines"

            const val kotlinxCoroutinesAndroid =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinXCoroutines"

            const val kotlinxSerializationRuntimeCommon =
                "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serialization"

            const val kotlinxSerializationRuntimeNative =
                "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$serialization"

            const val kotlinxSerializationRuntime =
                "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serialization"
        }

        object Ktor {

            const val ktorClientCore = "io.ktor:ktor-client-core:$ktor"
            const val ktorClientJson = "io.ktor:ktor-client-json:$ktor"
            const val ktorClientLogging = "io.ktor:ktor-client-logging:$ktor"
            const val ktorClientSerialization = "io.ktor:ktor-client-serialization:$ktor"
            const val ktorClientIos = "io.ktor:ktor-client-ios:$ktor"
            const val ktorClientJsonNative = "io.ktor:ktor-client-json-native:$ktor"
            const val ktorClientLoggingNative = "io.ktor:ktor-client-logging-native:$ktor"
            const val ktorClientSerializationNative =
                "io.ktor:ktor-client-serialization-native:$ktor"
            const val ktorClientJsonJvm = "io.ktor:ktor-client-json-jvm:$ktor"
            const val ktorClientLoggingJvm = "io.ktor:ktor-client-logging-jvm:$ktor"
            const val ktorClientSerializationJvm = "io.ktor:ktor-client-serialization-jvm:$ktor"
        }
    }
}