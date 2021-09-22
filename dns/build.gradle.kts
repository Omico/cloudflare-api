plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    `maven-publish`
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "cloudflare-dns"
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
        }
    }
}
