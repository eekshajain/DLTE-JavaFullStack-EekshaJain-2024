package com.example.beans;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class MyConfig {

    @Bean(name="hello")
   // @Lazy
    public UserServices getUser(){

        System.out.println("UserServices bean created");
        return new UserServices();
    }
}
