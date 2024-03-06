package org.middleware;

import java.util.List;

public interface InputEmployeeDetails {
    void saveAll(List<Employee> employee);
    Employee displayRequired(int employeeID);
    Employee displayBasedOnPinCode(int pinCode);
    List<Employee> displayAll();
}
