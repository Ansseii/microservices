dependencies {
    implementation("org.springframework.boot:spring-boot-starter-amqp")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}