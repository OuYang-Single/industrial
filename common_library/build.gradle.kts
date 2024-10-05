plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.ijcsj.common_library"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }

    }

    buildTypes {
        debug{
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField("String", "base_url", "\"http://111.180.206.54:2403\"")
        }

        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "base_url", "\"http://111.180.206.54:2403\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding= true
        buildConfig=true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

dependencies {


    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    api(libs.koin)
    api(libs.androidx.design)
    api(libs.androidx.paging)
    api(libs.androidx.flexbox)

    api(libs.okhttp3)
    api(libs.okhttp.logging)
    api(libs.retrofit)
    api(libs.retrofit.gson)
    api(libs.glide)
    api(libs.retrofit.gson)
    api(libs.retrofit.rxjava)
    api(libs.gson)
    api(libs.kotlinx.lifecycle )
    api("com.jakewharton.rxbinding4:rxbinding-material:4.0.0")
    api("com.jakewharton.rxbinding4:rxbinding-core:4.0.0")
    api("com.jakewharton.rxbinding4:rxbinding-appcompat:4.0.0")
    api("com.jakewharton.rxbinding4:rxbinding-drawerlayout:4.0.0")
    api("com.jakewharton.rxbinding4:rxbinding-leanback:4.0.0")
    api("com.jakewharton.rxbinding4:rxbinding-recyclerview:4.0.0")
    api("com.jakewharton.rxbinding4:rxbinding-slidingpanelayout:4.0.0")
    api("com.jakewharton.rxbinding4:rxbinding-swiperefreshlayout:4.0.0")
    api("com.jakewharton.rxbinding4:rxbinding-viewpager:4.0.0")
    api("com.jakewharton.rxbinding4:rxbinding-viewpager2:4.0.0")
    api("com.jakewharton.rxbinding3:rxbinding-material:3.0.0-alpha2")
    api("com.yaoxiaowen:download:1.4.1")
    api("com.qcloud.cos:cos-android:5.9.+")
    //1. 云刷脸SDK
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    api(files("libs/DC-WBFaceService-v2.0.6-5f51043.aar"))
    api(files("libs/DC-WBNormal-v2.0.9-13cfc37.aar"))
    api(files("libs/WbCloudFaceLiveSdk-v4.5.5.0-50930867.aar"))
    api(files("libs/WbCloudNormal-v5.1.7-2c93d01.aar"))

    //2. 云normal SDK
 /*   api ("org.apache.poi:poi:4.1.2")
            api ("org.apache.poi:poi-ooxml:4.1.2")
    api ("stax:stax-api:1.0-2")*/
    api ("com.github.ctiao:DanmakuFlameMaster:0.9.25")
            api ("com.github.ctiao:ndkbitmap-armv7a:0.9.21")

    api ("com.github.ctiao:ndkbitmap-armv5:0.9.21")
    api ("com.github.ctiao:ndkbitmap-x86:0.9.21")
    api(libs.logger){
        exclude(
            "com.android.support",
            "support-compat"
        )
    }
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
    api ("com.github.li-xiaojun:XPopup:2.9.19"){
        exclude(
            "com.davemorrissey.labs",
            "subsampling-scale-image-view-androidx"
        )
    }
    implementation("junit:junit:4.12")
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    kapt (libs.arouter.compiler)
    api(project(":service_library"))
    api(project(":ui_library"))
    api (libs.android.startup)
    api(libs.mmkv)
    api ("com.github.JessYanCoding:AndroidAutoSize:v1.2.1")
    // PictureSelector 基础 (必须)

    api("androidx.work:work-runtime:2.7.1")
    api("androidx.work:work-runtime-ktx:2.7.1")
    api("androidx.work:work-gcm:2.7.1")
    api("androidx.work:work-testing:2.7.1")
    api("com.amap.api:location:6.4.3")
/*    api("com.amap.api:3dmap:6.4.3")
    api("com.amap.api:search:6.4.3")*/


    api("com.github.AlexLiuSheng:CheckVersionLib:2.4.2")
    api("me.jessyan:retrofit-url-manager:1.4.0")
    api("org.nanohttpd:nanohttpd:2.3.1")
    // 图片压缩 (按需引入)

    api("com.github.getActivity:XXPermissions:18.62")
    api("com.github.youlookwhat:ByWebView:1.2.1")
    api("com.blankj:utilcodex:1.30.6") // 请检查最新版本

    api("com.tencent.imsdk:imsdk-plus:7.8.5505")

    // 如果您需要添加 Quic 插件，请取消下一行的注释（注意：要求插件版本号和 IM SDK 版本号相同）
    api("com.tencent.imsdk:timquic-plugin:7.8.5505")

    api ("androidx.room:room-runtime:2.1.0-alpha06")
    api("androidx.room:room-ktx:2.1.0-alpha06")
    kapt ("androidx.room:room-compiler:2.1.0-alpha06")
    androidTestImplementation ("androidx.room:room-testing:2.1.0-alpha06")


}