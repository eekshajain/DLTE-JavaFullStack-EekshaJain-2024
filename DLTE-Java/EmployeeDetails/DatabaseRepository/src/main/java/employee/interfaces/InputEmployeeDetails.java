package employee.interfaces;


import employee.entity.Employee;

import java.util.List;

public  interface InputEmployeeDetails {
    Employee saveAll(Employee employee);
    Employee displayRequired(int employeeID);
    List<Employee> displayBasedOnPinCode(int pinCode);
    List<Employee> displayAll();
    boolean doesEmployeeExists(int empID);
    boolean deleteByID(int employeeID);
    void close();
}
