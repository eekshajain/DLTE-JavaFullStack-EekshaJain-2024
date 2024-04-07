package com.exampledi.demodi;

// MainApp.java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("customer.xml");//Spring IoC container

        CustomerService customerService = (CustomerService) context.getBean("customerService");//get instance

        Customer customer = new Customer("Nayana");
        customerService.saveCustomer(customer);
    }
}
