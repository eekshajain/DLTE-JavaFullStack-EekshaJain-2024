package com.payment.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//@ComponentScan("com.paymentdao.payment.service")  in soap dao
@SpringBootApplication
public class WebservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebservicesApplication.class, args);
    }

}
