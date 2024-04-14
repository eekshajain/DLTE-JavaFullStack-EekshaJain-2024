package com.springboot.webservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
@ComponentScan("com.springproject.employee")
public class EmployeeController {
    private final String url = "http://employee.services";
    @Autowired
    public EmployeeBasicDetails
}