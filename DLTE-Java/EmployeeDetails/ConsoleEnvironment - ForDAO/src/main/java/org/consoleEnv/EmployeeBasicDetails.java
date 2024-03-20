package org.consoleEnv;


public class EmployeeBasicDetails {
    private int employeeID;
    private String firstName;
    private String middleName;
    private String lastName;
    private long phoneNumber;
    private String emailID;
    EmployeeAddress temporaryAddress;
    EmployeeAddress permanentAddress;

    public EmployeeBasicDetails(EmployeeAddress temporaryAddress, EmployeeAddress permanentAddress) {
        this.temporaryAddress = temporaryAddress;
        this.permanentAddress = permanentAddress;
    }

    public EmployeeAddress getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(EmployeeAddress temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public EmployeeAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(EmployeeAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public EmployeeBasicDetails() {
    }


    public String toString() {
        return "EmployeeBasicDetails{" +
                "employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailID='" + emailID + '\'' +
                '}';
    }




    public EmployeeBasicDetails(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
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
}

