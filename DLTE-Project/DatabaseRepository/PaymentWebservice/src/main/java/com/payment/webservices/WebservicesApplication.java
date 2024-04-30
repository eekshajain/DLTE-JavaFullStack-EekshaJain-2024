package com.payment.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:apps.properties")
public class WebservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebservicesApplication.class, args);
    }

}
