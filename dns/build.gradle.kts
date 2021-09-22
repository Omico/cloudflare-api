import me.omico.buildSrc.configureMavenLibraryPublish

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withSourcesJar()
}

dependencies {
    compileOnly(kotlinx.coroutines.core)
    compileOnly(kotlinx.serialization.json)
    compileOnly(okhttp4.okhttp)
    compileOnly(retrofit2.converter.kotlinxSerialization)
    compileOnly(retrofit2.retrofit)
}

configureMavenLibraryPublish(
    mavenPublicationName = "maven",
    versionName = version.toString(),
)
