package org.example;

import java.util.List;

public interface DisplayEmployeeDetails {
   void displayName(List<EmployeeName> employeeName);
   void displayContactDetails(List<EmployeeContactDetails> employeeContactDetails);
   void displayAddress(List<EmployeeAddress> tempEmployeeAddress, List<EmployeeAddress> permEmployeeAddress);
   void displayAll(List<EmployeeName> employeeName,List<EmployeeContactDetails> employeeContactDetails,List<EmployeeAddress> tempEmployeeAddress, List<EmployeeAddress> permEmployeeAddress);
}
