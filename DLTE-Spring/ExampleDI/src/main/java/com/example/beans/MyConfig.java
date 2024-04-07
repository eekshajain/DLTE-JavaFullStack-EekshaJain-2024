package com.example.beans;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean(name="hello")
    public UserServices getUser(){
        return new UserServices();
    }
}
