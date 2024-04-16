package com.employeedao.employee.entity;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class EmployeeBasicDetails {
    @NotNull
    private int employeeID;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabetic characters")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z']+$",message = "Middle name can have alphabets and apostrophe")
    private String middleName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only alphabetic characters")
    private String lastName;

    @Range(min = 7000000000L, max = 9999999999L, message = "Invalid Indian phone number")
    private long phoneNumber;

    @Email(message = "Email should be valid")
    private String emailID;

    @NotNull
    EmployeeAddress temporaryEmployeeAddress;

    @NotNull
    EmployeeAddress permanentEmployeeAddress;

    public EmployeeBasicDetails() {
    }

    public EmployeeBasicDetails(int employeeID, String firstName, String middleName, String lastName, long phoneNumber, String emailID, EmployeeAddress temporaryEmployeeAddress, EmployeeAddress permanentEmployeeAddress) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
        this.temporaryEmployeeAddress = temporaryEmployeeAddress;
        this.permanentEmployeeAddress = permanentEmployeeAddress;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public EmployeeAddress getTemporaryEmployeeAddress() {
        return temporaryEmployeeAddress;
    }

    public void setTemporaryEmployeeAddress(EmployeeAddress temporaryEmployeeAddress) {
        this.temporaryEmployeeAddress = temporaryEmployeeAddress;
    }

    public EmployeeAddress getPermanentEmployeeAddress() {
        return permanentEmployeeAddress;
    }

    public void setPermanentEmployeeAddress(EmployeeAddress permanentEmployeeAddress) {
        this.permanentEmployeeAddress = permanentEmployeeAddress;
    }
}
