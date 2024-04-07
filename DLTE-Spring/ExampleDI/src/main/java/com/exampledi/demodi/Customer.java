package com.exampledi.demodi;

// Customer.java
public class Customer {
    private String name;

    public Customer() {
        System.out.println("Object created");
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

