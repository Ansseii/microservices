plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.boot:spring-boot-starter-amqp")

    runtimeOnly("org.postgresql:postgresql")

    implementation(project(":clients"))
    implementation(project(":amqp"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}