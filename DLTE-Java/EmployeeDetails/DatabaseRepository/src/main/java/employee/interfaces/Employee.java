package employee.interfaces;

import employee.implementation.EmployeeExceptions;

public class Employee {
    EmployeeBasicDetails employeeBasicDetails;
   EmployeeAddress temporaryEmployeeAddress;
    EmployeeAddress permanentEmployeeAddress;

    @Override
    public String toString() {
        return "Employee Details:"+
                "Employee ID:"+employeeBasicDetails.getEmployeeID()+"\n"+
                "Employee Full Name:"+employeeBasicDetails.getFirstName()+" "+employeeBasicDetails.getMiddleName()+" "+employeeBasicDetails.getLastName()+"\n"+
                "Employee Contact Details:"+"\n"+
                "Phone Number:"+employeeBasicDetails.getPhoneNumber()+"\n"+"Email ID:"+employeeBasicDetails.getEmailID()+"\n"+
                "Employee Address:"+"\n"+
                "Temporary Address:"+temporaryEmployeeAddress.getHouseName()+" "+temporaryEmployeeAddress.getHouseStreet()+" "+temporaryEmployeeAddress.getCityName()+" "+temporaryEmployeeAddress.getStateName()+" "+temporaryEmployeeAddress.getPinCode()+"\n"+
                "Permanent Address:"+permanentEmployeeAddress.getHouseName()+" "+permanentEmployeeAddress.getHouseStreet()+" "+permanentEmployeeAddress.getCityName()+" "+permanentEmployeeAddress.getStateName()+" "+permanentEmployeeAddress.getPinCode()+"\n";
    }

    public Employee() {
    }

    public Employee(EmployeeBasicDetails employeeBasicDetails, EmployeeAddress temporaryEmployeeAddress, EmployeeAddress permanentEmployeeAddress) {
        this.employeeBasicDetails = employeeBasicDetails;
        this.temporaryEmployeeAddress = temporaryEmployeeAddress;
        this.permanentEmployeeAddress = permanentEmployeeAddress;
    }

    public EmployeeBasicDetails getEmployeeBasicDetails() {
        return employeeBasicDetails;
    }

    public void setEmployeeBasicDetails(EmployeeBasicDetails employeeBasicDetails) {
        this.employeeBasicDetails = employeeBasicDetails;
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