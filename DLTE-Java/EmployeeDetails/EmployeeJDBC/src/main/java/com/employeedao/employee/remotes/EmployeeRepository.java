package com.employeedao.employee.remotes;

import com.employeedao.employee.entity.EmployeeBasicDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {
    EmployeeBasicDetails saveAll(EmployeeBasicDetails employee);
    EmployeeBasicDetails displayRequired(int employeeID);
    List<EmployeeBasicDetails> displayBasedOnPinCode(int pinCode);
    List<EmployeeBasicDetails> displayAll();
    boolean doesEmployeeExists(int empID);
    boolean deleteByID(int employeeID);
}
