plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("kotlin-parcelize")

}

android {
    namespace = "com.ijcsj.ui_library"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src\\main\\assets", "src\\main\\assets")
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.design)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.flexbox)
    implementation(libs.arouter.api)
    implementation(libs.retrofit.rxjava)
    compileOnly("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    api (libs.dialog)
    api("ren.qinc.edit:lib:0.0.3")
    api("com.github.getActivity:ShapeView:8.3")
    api("com.kongzue.dialogx:DialogX:0.0.49")
    api ("com.github.zhpanvip:bannerviewpager:3.5.11")
    api("com.github.widemouth-dz:wmrichtexteditor:2.0.4")
    implementation(libs.kotlinx.coroutines.android)
//PickerView框架 当前版本4.1.9
    api("com.contrarywind:Android-PickerView:4.1.9")

    api("com.superkung:dashboard-view:1.1.1")
    api ("com.github.xiaohaozi9825:adapter_plus:3.07.00")
    api ("com.github.zcweng:switch-button:0.0.3@aar")
    implementation(libs.glide)
    kapt(libs.glide.compiler)
    implementation(libs.glide.okhttp)
    api ("com.github.wildma:IDCardCamera:1.1.1")
    api ("com.lzy.widget:ninegridview:0.2.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.4.20")
    api ("io.github.scwang90:refresh-layout-kernel:2.1.0")    //核心必须依赖
            api ("io.github.scwang90:refresh-header-classics:2.1.0")     //经典刷新头
            api ("io.github.scwang90:refresh-header-radar:2.1.0")        //雷达刷新头
            api ("io.github.scwang90:refresh-header-falsify:2.1.0")    //虚拟刷新头
            api ("io.github.scwang90:refresh-header-material:2.1.0")     //谷歌刷新头
            api ("io.github.scwang90:refresh-header-two-level:2.1.0")    //二级刷新头
            api ("io.github.scwang90:refresh-footer-ball:2.1.0")       //球脉冲加载
            api ("io.github.scwang90:refresh-footer-classics:2.1.0")    //经典加载
    api("io.github.lucksiege:pictureselector:v3.11.2")
    api("io.github.lucksiege:compress:v3.11.2")
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
    api ("com.github.PhilJay:MPAndroidChart:v3.1.0")
    api ("com.github.li-xiaojun:XPopup:2.9.19")
    api ("com.gongwen:marqueelibrary:1.1.3")
}