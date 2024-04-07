package com.exampledi.demodi;

import org.springframework.beans.factory.annotation.Autowired;

// CustomerService.java
public class CustomerService {
  //  private final CustomerRepository customerRepository;

//    public CustomerService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
private CustomerRepository customerRepository;
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
//@Autowired // This annotation injects the dependency
//private CustomerRepository customerRepository;



    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}

