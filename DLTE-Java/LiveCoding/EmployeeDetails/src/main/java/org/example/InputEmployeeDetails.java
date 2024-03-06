package org.example;

public interface InputEmployeeDetails {
  EmployeeName collectEmployeeID();
  EmployeeName collectNames();
  EmployeeContactDetails collectContactDetails();
  EmployeeName collectBasic();
  EmployeeAddress collectTemporaryAddress();
  EmployeeAddress collectPermanentAddress();
  EmployeeAddress collectAddress();
}
