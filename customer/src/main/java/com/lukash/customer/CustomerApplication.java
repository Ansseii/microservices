package com.lukash.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@EnableEurekaClient
@EnableFeignClients(basePackages = "com.lukash.clients")
@PropertySource("classpath:clients-${spring.profiles.active}.properties")
@SpringBootApplication(scanBasePackages = {"com.lukash.customer", "com.lukash.amqp"})
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}

