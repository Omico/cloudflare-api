@file:Suppress("UnstableApiUsage")

rootProject.name = "cloudflare-api"

pluginManagement {
    val versions = object {
        val gradleEnterprisePlugin = "3.6.3"
        val kotlinPlugin = "1.5.31"
    }
    plugins {
        id("com.gradle.enterprise") version versions.gradleEnterprisePlugin
        kotlin("jvm") version versions.kotlinPlugin
        kotlin("plugin.serialization") version versions.kotlinPlugin
    }
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

enableFeaturePreview("VERSION_CATALOGS")

createVersionCatalog("kotlinx")
createVersionCatalog("okhttp4")
createVersionCatalog("retrofit2")

plugins {
    id("com.gradle.enterprise")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
    }
}

include(":dns")

fun createVersionCatalog(name: String) =
    dependencyResolutionManagement.versionCatalogs.create(name) {
        from(files("gradle/common-version-catalogs/$name.versions.toml"))
    }
