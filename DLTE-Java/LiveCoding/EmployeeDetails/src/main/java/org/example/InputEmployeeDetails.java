package org.example;

public interface InputEmployeeDetails {
  EmployeeName collectEmployeeID();
  EmployeeName collectNames();
  EmployeeContactDetails collectContactDetails();
  EmployeeAddress collectTemporaryAddress();
  EmployeeAddress collectPermanentAddress();
}
