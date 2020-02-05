plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}
android {

    compileSdkVersion(CurrencyConverterConfig.SdkVersion.compileSdkVersion)

    defaultConfig {
        applicationId = "com.cyborg.currencyconverter_multiplatform"
        minSdkVersion(CurrencyConverterConfig.SdkVersion.minSdkVersion)
        targetSdkVersion(CurrencyConverterConfig.SdkVersion.targetSdkVersion)
        versionCode = 1
        versionName = CurrencyConverterConfig.version
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(CurrencyConverterConfig.Libs.Kotlin.jvm)

    implementation(project(":common"))

    implementation(CurrencyConverterConfig.Libs.Support.appCompat)
    implementation(CurrencyConverterConfig.Libs.Support.design)
    implementation(CurrencyConverterConfig.Libs.Support.constraintLayout)
    implementation(CurrencyConverterConfig.Libs.Support.multiDex)
    implementation(CurrencyConverterConfig.Libs.Support.annotations)
    implementation(CurrencyConverterConfig.Libs.Support.materialDesign)

    implementation(CurrencyConverterConfig.Libs.Misc.glide)

    testImplementation(CurrencyConverterConfig.Libs.Test.junit)
    androidTestImplementation(CurrencyConverterConfig.Libs.AndroidTest.testRunner)
    androidTestImplementation(CurrencyConverterConfig.Libs.AndroidTest.testRules)

    implementation(CurrencyConverterConfig.Libs.Reactive.reaktive)
    implementation(CurrencyConverterConfig.Libs.Network.okHttp)
}

