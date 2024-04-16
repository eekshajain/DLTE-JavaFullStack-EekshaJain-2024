package com.example.beans;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(DemoApplication.class);
        for(String s:context.getBeanDefinitionNames()){
            System.out.println(s);
        }
        //by type
//        UserServices userServices=context.getBean(UserServices.class);
//        System.out.println("User service list"+userServices.getList());
//        // Retrieve bean by name
//        UserServices userServicesName = (UserServices) context.getBean("hello");
//        System.out.println("User service list: " + userServicesName.getList());

    }

}

