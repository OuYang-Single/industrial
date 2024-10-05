pluginManagement {
    repositories {
        google()
        mavenCentral()

        maven { url =
            uri("https://jitpack.io")}
        maven { url =
            uri("https://raw.githubusercontent.com/martinloren/AabResGuard/mvn-repo")}
        maven { url = uri("https://raw.githubusercontent.com/martinloren/AabResGuard/mvn-repo") }
        maven { url = uri("https://raw.githubusercontent.com/martinloren/AabResGuard/mvn-repo") }
        maven { url = uri("https://raw.githubusercontent.com/martinloren/AabResGuard/mvn-repo") }
        maven { url = uri("https://jitpack.io") }
        maven { url  = uri("https://maven.aliyun.com/repository/jcenter") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public/") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/releases/") }
        // 配置HMS Core SDK的Maven仓地址。
        maven { url = uri("https://developer.huawei.com/repo/") }
        maven { url = uri("https://repo1.maven.org/maven2/") }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url =
            uri("https://jitpack.io")}
        google()

        mavenCentral()
        maven { url =
            uri("https://raw.githubusercontent.com/martinloren/AabResGuard/mvn-repo")}
        maven { url = uri("https://raw.githubusercontent.com/martinloren/AabResGuard/mvn-repo") }
        maven { url = uri("https://raw.githubusercontent.com/martinloren/AabResGuard/mvn-repo") }
        maven { url = uri("https://raw.githubusercontent.com/martinloren/AabResGuard/mvn-repo") }
        maven { url = uri("https://jitpack.io") }
        maven { url  = uri("https://maven.aliyun.com/repository/jcenter") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/groups/public/") }
        maven { url = uri("https://maven.aliyun.com/nexus/content/repositories/releases/") }
        // 配置HMS Core SDK的Maven仓地址。
        maven { url = uri("https://developer.huawei.com/repo/") }
        maven { url = uri("https://repo1.maven.org/maven2/") }
        mavenCentral()

    }
}

rootProject.name = "gosling"
include(":app")
include(":ui_library")
include(":common_library")
include(":service_library")
include(":login_library")
include(":inlet_library")
include(":mylibrary")
include(":web_library")
include(":main_library")
include(":datalibrary")
include(":settinglibrary")
include(":phoneApp")
include(":phoneApp_Library")
