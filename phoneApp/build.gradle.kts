plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.cn.phoneapp"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.cn.phoneapp"
        minSdk =  libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
        ndk.abiFilters += listOf("arm64-v8a")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }
    }
    signingConfigs {
        /*  named("debug") {
              storeFile = rootProject.file("key.jks")
              storePassword = "123456"
              keyAlias = "key0"
              keyPassword = "123456"
          }

          named("debug") {
              storeFile = rootProject.file("key.jks")
              storePassword = "123456"
              keyAlias = "key0"
              keyPassword = "123456"
          }*/
    }
    buildTypes {
        debug{
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = true
            isDebuggable=true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        //noinspection DataBindingWithoutKapt
        dataBinding= true
        buildConfig=true
    }
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(project(":common_library"))
    implementation(project(":login_library"))
    implementation(project(":ui_library"))
    implementation(project(":service_library"))
    implementation(project(":inlet_library"))
    implementation(project(":mylibrary"))
    implementation(project(":web_library"))
    implementation(project(":phoneApp_Library"))
    implementation(project(":main_library"))
    implementation (libs.arouter.api){
        exclude(
            "com.android.support",
            "support-compat"
        )
        exclude(
            "com.android.support",
            "support-v4"
        )
    }
    kapt ("androidx.room:room-compiler:2.1.0-alpha06")
    androidTestImplementation ("androidx.room:room-testing:2.1.0-alpha06")
    kapt (libs.arouter.compiler)
}