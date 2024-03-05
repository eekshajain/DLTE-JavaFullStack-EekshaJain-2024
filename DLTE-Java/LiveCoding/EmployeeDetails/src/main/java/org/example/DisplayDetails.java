package org.example;

import java.util.List;

public class DisplayDetails implements DisplayEmployeeDetails {
    @Override
    public void displayName(List<EmployeeName> employeeName) {
        for(int index=0;index<employeeName.size();index++){
            EmployeeName employee=employeeName.get(index);
            if(employee.getFirstName()!=null) {
                if(employee.getMiddleName()!=null)
                    if(employee.getLastName()!=null)
                        System.out.println("Name:" + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
            }
        }

    }

    @Override
    public void displayContactDetails(List<EmployeeContactDetails> employeeContactDetails) {
        for(EmployeeContactDetails each:employeeContactDetails){
            System.out.println("Phone Number:"+each.getPhoneNumber()+"\n"+"Email Address:"+each.getEmailID());
        }
    }

    @Override
    public void displayAddress(List<EmployeeAddress> tempEmployeeAddress, List<EmployeeAddress> permEmployeeAddress) {
        for(int index=0;index<tempEmployeeAddress.size();index++){
            EmployeeAddress tempAddress=tempEmployeeAddress.get(index);
            EmployeeAddress permAddress=permEmployeeAddress.get(index);
            System.out.println("Temporary address:"+tempAddress.getTemporaryHouseName()+","+tempAddress.getTemporaryHouseStreet()+","+tempAddress.getTemporaryCityName()+","+tempAddress.getTemporaryStateName()+"-"+tempAddress.getTemporaryPinCode());
            System.out.println("Permanent address:"+permAddress.getPermanentHouseName()+","+permAddress.getPermanentHouseStreet()+","+permAddress.getPermanentCityName()+","+permAddress.getPermanentStateName()+"-"+permAddress.getPermanentPinCode());
        }

    }
public void display(List<EmployeeName> employeeID,List<EmployeeName> employeeName, List<EmployeeContactDetails> employeeContactDetails, List<EmployeeAddress> tempEmployeeAddress, List<EmployeeAddress> permEmployeeAddress,int empID){
        int index=employeeID.indexOf(empID);
        EmployeeName employee=employeeName.get(index);
        EmployeeContactDetails employeeContactDetails1=employeeContactDetails.get(index);
        EmployeeAddress tempAddress=tempEmployeeAddress.get(index);
        EmployeeAddress permAddress=permEmployeeAddress.get(index);
    if(employee.getFirstName()!=null) {
        if(employee.getMiddleName()!=null)
            if(employee.getLastName()!=null)
                System.out.println("Name:" + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
    }else{
        System.out.println("null");}
    System.out.println("Phone Number:"+employeeContactDetails1.getPhoneNumber()+"\n"+"Email Address:"+employeeContactDetails1.getEmailID());
    System.out.println("Temporary address:"+tempAddress.getTemporaryHouseName()+","+tempAddress.getTemporaryHouseStreet()+","+tempAddress.getTemporaryCityName()+","+tempAddress.getTemporaryStateName()+"-"+tempAddress.getTemporaryPinCode());
    System.out.println("Permanent address:"+permAddress.getPermanentHouseName()+","+permAddress.getPermanentHouseStreet()+","+permAddress.getPermanentCityName()+","+permAddress.getPermanentStateName()+"-"+permAddress.getPermanentPinCode());
}
    @Override
    public void displayAll(List<EmployeeName> employeeID, List<EmployeeName> employeeName, List<EmployeeContactDetails> employeeContactDetails, List<EmployeeAddress> tempEmployeeAddress, List<EmployeeAddress> permEmployeeAddress) {
        for(int index=0;index<employeeName.size();index++){
            EmployeeName id=employeeID.get(index);
            EmployeeName employee=employeeName.get(index);
            EmployeeContactDetails employeeContactDetails1=employeeContactDetails.get(index);
            EmployeeAddress tempAddress=tempEmployeeAddress.get(index);
            EmployeeAddress permAddress=permEmployeeAddress.get(index);
            System.out.println("Employee ID:"+id.getEmployeeID());
            if(employee.getFirstName()!=null) {
                if(employee.getMiddleName()!=null)
                    if(employee.getLastName()!=null)
                        System.out.println("Name:" + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
            }else{
                System.out.println("null");}
            System.out.println("Phone Number:"+employeeContactDetails1.getPhoneNumber()+"\n"+"Email Address:"+employeeContactDetails1.getEmailID());
            System.out.println("Temporary address:"+tempAddress.getTemporaryHouseName()+","+tempAddress.getTemporaryHouseStreet()+","+tempAddress.getTemporaryCityName()+","+tempAddress.getTemporaryStateName()+"-"+tempAddress.getTemporaryPinCode());
            System.out.println("Permanent address:"+permAddress.getPermanentHouseName()+","+permAddress.getPermanentHouseStreet()+","+permAddress.getPermanentCityName()+","+permAddress.getPermanentStateName()+"-"+permAddress.getPermanentPinCode());
        }
    }
    }

