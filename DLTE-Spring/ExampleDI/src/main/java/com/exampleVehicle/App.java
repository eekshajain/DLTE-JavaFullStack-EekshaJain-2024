package com.exampleVehicle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("vehicle.xml");

        // Retrieve Vehicle bean
        Vehicle vehicle = (Vehicle) context.getBean("vehicle");
        vehicle.start();
        vehicle.stop();


    }
}
