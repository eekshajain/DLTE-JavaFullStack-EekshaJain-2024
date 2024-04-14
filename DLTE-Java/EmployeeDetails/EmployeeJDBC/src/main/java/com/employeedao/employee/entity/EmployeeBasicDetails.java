package com.employeedao.employee.entity;

public class EmployeeBasicDetails {
    //@NotNull(message = "")
    private int employeeID;
    private String firstName;
    private String middleName;
    private String lastName;
    private long phoneNumber;
    private String emailID;
    EmployeeAddress temporaryEmployeeAddress;
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