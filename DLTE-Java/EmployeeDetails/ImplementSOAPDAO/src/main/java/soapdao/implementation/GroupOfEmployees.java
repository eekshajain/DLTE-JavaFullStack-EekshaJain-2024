package soapdao.implementation;

import employee.entity.Employee;

import java.util.ArrayList;

public class GroupOfEmployees {
    private ArrayList<Employee> employeeArrayList;

    public GroupOfEmployees(ArrayList<Employee> employeeArrayList) {
        this.employeeArrayList = employeeArrayList;
    }

    public GroupOfEmployees() {
    }

    public ArrayList<Employee> getEmployeesArrayList() {
        return employeeArrayList;
    }

    public void setEmployeesArrayList(ArrayList<Employee> employeeArrayList) {
        this.employeeArrayList = employeeArrayList;
    }

    @Override
    public String toString() {
        return "GroupOfEmployee{" +
                "employeeArrayList=" + employeeArrayList +
                '}';
    }
}
