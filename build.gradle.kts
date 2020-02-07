// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(CurrencyConverterConfig.Plugins.androidPlugin)
        classpath(CurrencyConverterConfig.Plugins.kotlinPlugin)
        classpath(CurrencyConverterConfig.Plugins.kotlinSerialization)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}



allprojects {
    repositories {
        google()
        jcenter()
        maven {
            setUrl("https://dl.bintray.com/badoo/maven")
        }
    }
}


task("clean") {
    delete(rootProject.buildDir)
}