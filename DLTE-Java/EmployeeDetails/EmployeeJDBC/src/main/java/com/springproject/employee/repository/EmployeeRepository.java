package com.springproject.employee.repository;

import com.springproject.employee.entity.EmployeeBasicDetails;

import java.util.List;

public interface EmployeeRepository {
    EmployeeBasicDetails saveAll(EmployeeBasicDetails employee);
    EmployeeBasicDetails displayRequired(int employeeID);
    List<EmployeeBasicDetails> displayBasedOnPinCode(int pinCode);
    List<EmployeeBasicDetails> displayAll();
    boolean doesEmployeeExists(int empID);
    boolean deleteByID(int employeeID);
}
