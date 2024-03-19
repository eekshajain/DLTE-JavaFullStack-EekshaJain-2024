package org.consoleEnv;



public class EmployeeConsole {
    EmployeeBasicDetailsConsole employeeBasicDetails;
    EmployeeAddressConsole temporaryEmployeeAddress;
    EmployeeAddressConsole permanentEmployeeAddress;

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

    public EmployeeConsole() {
    }

    public EmployeeConsole(EmployeeBasicDetailsConsole employeeBasicDetails, EmployeeAddressConsole temporaryEmployeeAddress, EmployeeAddressConsole permanentEmployeeAddress) {
        this.employeeBasicDetails = employeeBasicDetails;
        this.temporaryEmployeeAddress = temporaryEmployeeAddress;
        this.permanentEmployeeAddress = permanentEmployeeAddress;
    }

    public EmployeeBasicDetailsConsole getEmployeeBasicDetails() {
        return employeeBasicDetails;
    }

    public void setEmployeeBasicDetails(EmployeeBasicDetailsConsole employeeBasicDetails) {
        this.employeeBasicDetails = employeeBasicDetails;
    }

    public EmployeeAddressConsole getTemporaryEmployeeAddress() {
        return temporaryEmployeeAddress;
    }

    public void setTemporaryEmployeeAddress(EmployeeAddressConsole temporaryEmployeeAddress) {
        this.temporaryEmployeeAddress = temporaryEmployeeAddress;
    }

    public EmployeeAddressConsole getPermanentEmployeeAddress() {
        return permanentEmployeeAddress;
    }

    public void setPermanentEmployeeAddress(EmployeeAddressConsole permanentEmployeeAddress) {
        this.permanentEmployeeAddress = permanentEmployeeAddress;
    }
}

