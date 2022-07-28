package com.lukash.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

@EnableEurekaClient
@PropertySource("classpath:clients-${spring.profiles.active}.properties")
@SpringBootApplication(scanBasePackages = {"com.lukash.notification", "com.lukash.amqp"})
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
}
