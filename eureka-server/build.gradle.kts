plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}