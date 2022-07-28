import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel

plugins {
    java
    idea
    id("org.springframework.boot") version "2.7.1" apply false
    id("io.freefair.lombok") version "6.5.0.2" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.google.cloud.tools.jib") version "3.2.1"
}

idea {
    project {
        languageLevel = IdeaLanguageLevel(17)
    }

    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

extra["springBootVersion"] = "2.7.1"
extra["springCloudVersion"] = "2021.0.3"

allprojects {
    group = "com.lukash"

    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "io.spring.dependency-management")

    configure(subprojects.filter {
        listOf("customer", "fraud", "eureka-server", "notification", "api-gateway").contains(it.name)
    }) {
        apply(plugin = "com.google.cloud.tools.jib")

        jib {
            from {
                image = "eclipse-temurin:17@sha256:2b47a8ea946ce1e5365a1562414ed576e378b7b670cadff3fb98ebecf2890cdc"
                platforms {
                    platform {
                        architecture = "amd64"
                        os = "linux"
                    }
                }
            }

            to {
                image = "ansseii/${project.name}:${project.version}"
                tags = setOf("latest")
            }
        }
    }

    dependencyManagement {
        dependencies {
            imports {
                mavenBom("org.springframework.boot:spring-boot-dependencies:${property("springBootVersion")}")
                mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
            }
        }
    }

    dependencies {
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
        implementation("org.springframework.boot:spring-boot-starter-test")
        implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
        implementation("org.springframework.cloud:spring-cloud-sleuth-zipkin")
    }
}

tasks.getByName<Task>("jib").enabled = false
tasks.getByName<Task>("jibBuildTar").enabled = false
tasks.getByName<Task>("jibDockerBuild").enabled = false