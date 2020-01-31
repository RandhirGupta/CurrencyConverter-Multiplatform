import com.android.build.gradle.BaseExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(CurrencyConverterConfig.Plugins.androidPlugin)
        classpath(CurrencyConverterConfig.Plugins.kotlinPlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}



allprojects {
    repositories {
        google()
        jcenter()
    }

    if ((group as String).isNotEmpty()) {
        conFigureAndroid()
    }
}


task("clean") {
    delete(rootProject.buildDir)
}

fun Project.conFigureAndroid() {
    apply(plugin = "com.android.application")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-android-extensions")
    apply(plugin = "kotlin-kapt")

    configure<BaseExtension> {
        compileSdkVersion(CurrencyConverterConfig.SdkVersion.compileSdkVersion)

        defaultConfig {
            minSdkVersion(CurrencyConverterConfig.SdkVersion.minSdkVersion)
            targetSdkVersion(CurrencyConverterConfig.SdkVersion.targetSdkVersion)
            versionCode = 1
            versionName = CurrencyConverterConfig.version
        }
    }
}