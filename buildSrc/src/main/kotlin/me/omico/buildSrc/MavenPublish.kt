package me.omico.buildSrc

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.plugins.signing.SigningExtension
import java.util.*

fun Project.configureMavenLibraryPublish(
    mavenPublicationName: String,
    versionName: String,
) {
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "org.gradle.signing")
    afterEvaluate {
        configure<PublishingExtension> {
            publications {
                create<MavenPublication>(mavenPublicationName) {
                    groupId = getProperty<String>("POM_GROUP_ID")
                    artifactId = getProperty<String>("POM_ARTIFACT_ID")
                    version = versionName
                    from(components["kotlin"])
                    artifact(tasks["sourcesJar"])
                    pom {
                        name.set(getProperty<String>("POM_NAME"))
                        description.set(getProperty<String>("POM_DESCRIPTION"))
                        url.set(getProperty<String>("POM_URL"))
                        licenses {
                            license {
                                name.set(getProperty<String>("POM_LICENCE_NAME"))
                                url.set(getProperty<String>("POM_LICENCE_URL"))
                            }
                        }
                        developers {
                            developer {
                                id.set(getProperty<String>("POM_DEVELOPER_ID"))
                                name.set(getProperty<String>("POM_DEVELOPER_NAME"))
                            }
                        }
                        scm {
                            connection.set(getProperty<String>("POM_SCM_CONNECTION"))
                            developerConnection.set(getProperty<String>("POM_SCM_DEV_CONNECTION"))
                            url.set(getProperty<String>("POM_SCM_URL"))
                        }
                    }
                }
            }
            repositories {
                maven {
                    credentials {
                        username = localProperties.getProperty("NEXUS_USERNAME")
                        password = localProperties.getProperty("NEXUS_PASSWORD")
                    }
                    val name = when {
                        isSnapshot(versionName) -> "NEXUS_PUBLISH_SNAPSHOT_URL"
                        else -> "NEXUS_PUBLISH_RELEASE_URL"
                    }
                    setUrl(localProperties.getProperty(name))
                }
            }
            if (!isSnapshot(versionName)) {
                configure<SigningExtension> {
                    useGpgCmd()
                    sign(publications[mavenPublicationName])
                }
            }
        }
    }
}

private fun isSnapshot(versionName: String): Boolean = versionName.endsWith("SNAPSHOT")

private inline fun <reified T> Project.getProperty(name: String): T = property(name) as T

private inline val Project.localProperties: Properties
    get() = Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }
