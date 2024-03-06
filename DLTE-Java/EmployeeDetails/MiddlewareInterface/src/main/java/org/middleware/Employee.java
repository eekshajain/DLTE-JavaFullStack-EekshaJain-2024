package org.middleware;

import java.io.Serializable;

public class Employee implements Serializable {
    EmployeeBasicDetails employeeBasicDetails;
    EmployeeAddress employeeAddress;
   private static final long serialVersionUID = 9022159217832292360L;
    @Override
    public String toString() {
        return "Employee Details:"+
                "Employee ID:"+employeeBasicDetails.getEmployeeID()+"\n"+
                "Employee Full Name:"+employeeBasicDetails.getFirstName()+" "+employeeBasicDetails.getMiddleName()+" "+employeeBasicDetails.getLastName()+"\n"+
                "Employee Contact Details:"+"\n"+
                "Phone Number:"+employeeBasicDetails.getPhoneNumber()+"\n"+"Email ID:"+employeeBasicDetails.getEmailID()+"\n"+
                "Employee Address:"+"\n"+
                "Temporary Address:"+employeeAddress.getTemporaryHouseName()+" "+employeeAddress.getTemporaryHouseStreet()+" "+employeeAddress.getTemporaryCityName()+" "+employeeAddress.getTemporaryStateName()+" "+employeeAddress.getTemporaryPinCode()+"\n"+
                "Permanent Address:"+employeeAddress.getPermanentHouseName()+" "+employeeAddress.getPermanentHouseStreet()+" "+employeeAddress.getPermanentCityName()+" "+employeeAddress.getPermanentStateName()+" "+employeeAddress.getPermanentPinCode()+"\n";
    }

    public Employee(EmployeeBasicDetails employeeBasicDetails, EmployeeAddress employeeAddress) {
        this.employeeBasicDetails = employeeBasicDetails;
        this.employeeAddress = employeeAddress;
    }

    public EmployeeBasicDetails getEmployeeBasicDetails() {
        return employeeBasicDetails;
    }

    public void setEmployeeBasicDetails(EmployeeBasicDetails employeeBasicDetails) {
        this.employeeBasicDetails = employeeBasicDetails;
    }

    public EmployeeAddress getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(EmployeeAddress employeeAddress) {
        this.employeeAddress = employeeAddress;
    }
}
