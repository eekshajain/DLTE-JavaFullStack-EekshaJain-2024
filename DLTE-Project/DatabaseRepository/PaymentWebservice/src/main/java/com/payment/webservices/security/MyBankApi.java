package com.payment.webservices.security;//package com.payment.webservices.security;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.security.MyBankUsers;
import com.paymentdao.payment.security.MyBankUsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class MyBankApi {
    @Autowired
    MyBankUsersServices service;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @PostMapping("/register")
//    public MyBankUsers save(@RequestBody MyBankUsers myBankUsers){
//        myBankUsers.setPassword(passwordEncoder.encode(myBankUsers.getPassword()));
//        return service.signUp(myBankUsers);
//    }

    @PostMapping("/register")
    public Customer save(@RequestBody Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return service.signUp(customer);
    }
}
