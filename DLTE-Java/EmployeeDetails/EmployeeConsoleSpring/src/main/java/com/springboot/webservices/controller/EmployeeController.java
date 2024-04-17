package com.springboot.webservices.controller;

import com.employeedao.employee.entity.EmployeeAddress;
import com.employeedao.employee.entity.EmployeeBasicDetails;
import com.employeedao.employee.exception.EmployeeException;
import com.employeedao.employee.remotes.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Endpoint
@ComponentScan("com.employeedao.employee")
public class EmployeeController {
    private final String url = "http://employee.services";
   @Autowired
    public EmployeeRepository employeeRepository;

@PayloadRoot(namespace = url,localPart = "newEmployeeRequest")
@ResponsePayload
    public NewEmployeeResponse addNewEmployee(@Valid @RequestPayload NewEmployeeRequest newEmployeeRequest)
{
    NewEmployeeResponse newEmployeeResponse = new NewEmployeeResponse();
    ServiceStatus serviceStatus = new ServiceStatus();

    try {
        Employebasicdetails actualEmployee = newEmployeeRequest.getEmployeeBasic();
        EmployeeBasicDetails daoEmployee = new EmployeeBasicDetails();
       // BeanUtils.copyProperties(actualEmployee, daoEmployee);
        daoEmployee=copy(actualEmployee);
        daoEmployee = employeeRepository.saveAll(daoEmployee);
        if(daoEmployee!=null){
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage(daoEmployee.getEmployeeID()+"inserted");
           // BeanUtils.copyProperties(daoEmployee,actualEmployee);
            actualEmployee=copy(daoEmployee);
            newEmployeeResponse.setEmployee(actualEmployee);
        }else{
            throw new EmployeeException("no employee");
        }
    }catch (EmployeeException e){
        serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
        serviceStatus.setMessage(e.getMessage());
    }
    newEmployeeResponse.setServiceStatus(serviceStatus);
    return newEmployeeResponse;
}

@PayloadRoot(namespace = url,localPart = "displayAllRequest")
    @ResponsePayload
    public DisplayAllResponse listAll(@RequestPayload DisplayAllRequest displayAllRequest ){
    DisplayAllResponse displayAllResponse=new DisplayAllResponse();
    ServiceStatus serviceStatus=new ServiceStatus();
    try{
        List<Employebasicdetails> employebasicdetailsList=new ArrayList<>();
        List<EmployeeBasicDetails> daoEmployee=employeeRepository.displayAll();
        if(daoEmployee!=null){
            daoEmployee.forEach(each->{
                Employebasicdetails tempEmployee=new Employebasicdetails();
           //     BeanUtils.copyProperties(each,tempEmployee);
                tempEmployee=copy(each);
                employebasicdetailsList.add(tempEmployee);
                    });
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage("Employee details "+displayAllRequest);
            displayAllResponse.getEmployee().addAll(employebasicdetailsList);

        }
    }catch (EmployeeException e){
        serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
        serviceStatus.setMessage(e.getMessage());
    }
    displayAllResponse.setServiceStatus(serviceStatus);
    return displayAllResponse;
}

    @PayloadRoot(namespace = url,localPart = "displayBasedOnIdRequest")
    @ResponsePayload
    public DisplayBasedOnIdResponse listBasedOnId(@RequestPayload DisplayBasedOnIdRequest displayBasedOnIdRequest ){
        DisplayBasedOnIdResponse displayBasedOnIdResponse = new DisplayBasedOnIdResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            EmployeeBasicDetails daoEmployee = employeeRepository.displayRequired(displayBasedOnIdRequest.getEmployeeID());
            if (daoEmployee != null) {
                Employebasicdetails employebasicdetails = copy(daoEmployee);
                displayBasedOnIdResponse.setEmployee(employebasicdetails); // Assuming setEmployee() sets a single element
                serviceStatus.setStatus(HttpServletResponse.SC_OK);
                serviceStatus.setMessage("Employee details " + displayBasedOnIdRequest);
            } else {
                serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
                serviceStatus.setMessage("Employee not found for ID: " + displayBasedOnIdRequest.getEmployeeID());
            }
        } catch (EmployeeException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(e.getMessage());
        }
        displayBasedOnIdResponse.setServiceStatus(serviceStatus);
        return displayBasedOnIdResponse;
    }

    @PayloadRoot(namespace = url,localPart = "displayBasedOnPincodeRequest")
    @ResponsePayload
    public DisplayBasedOnPincodeResponse listBasedOnPincode(@RequestPayload DisplayBasedOnPincodeRequest displayBasedOnPincodeRequest ){
        DisplayBasedOnPincodeResponse displayBasedOnPincodeResponse=new DisplayBasedOnPincodeResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        try{
            List<Employebasicdetails> employebasicdetailsList=new ArrayList<>();
            List<EmployeeBasicDetails> daoEmployee=employeeRepository.displayBasedOnPinCode(displayBasedOnPincodeRequest.getPinCode());
            if(daoEmployee!=null){
                daoEmployee.forEach(each->{
                    Employebasicdetails tempEmployee=new Employebasicdetails();
                    //     BeanUtils.copyProperties(each,tempEmployee);
                    tempEmployee=copy(each);
                    employebasicdetailsList.add(tempEmployee);
                });
                serviceStatus.setStatus(HttpServletResponse.SC_OK);
                serviceStatus.setMessage("Employee details "+displayBasedOnPincodeRequest);
                displayBasedOnPincodeResponse.getEmployee().addAll(employebasicdetailsList);

            }
        }catch (EmployeeException e){
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(e.getMessage());
        }
        displayBasedOnPincodeResponse.setServiceStatus(serviceStatus);
        return displayBasedOnPincodeResponse;
    }

    @PayloadRoot(namespace = url,localPart = "employeeExistsRequest")
    @ResponsePayload
   public EmployeeExistsResponse doesEmployeeExist(@RequestPayload EmployeeExistsRequest employeeExistsRequest){
        EmployeeExistsResponse employeeExistsResponse = new EmployeeExistsResponse();
        boolean employeeExists = employeeRepository.doesEmployeeExists(employeeExistsRequest.getEmployeeId());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (employeeExists) {
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            employeeExistsResponse.setEmployeeExists(true);
        } else {
            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
            serviceStatus.setMessage("Employee with ID " + employeeExistsRequest.getEmployeeId() + " not found");
            // Set employeeExists to false in case you want to include it in the response
            employeeExistsResponse.setEmployeeExists(false);
        }
        employeeExistsResponse.setServiceStatus(serviceStatus);
        return employeeExistsResponse;
    }


    public Employebasicdetails copy(EmployeeBasicDetails employeeBasicDetails){
    Employebasicdetails employebasicdetails=new Employebasicdetails();
    Employeeaddress tempAddress=new Employeeaddress();
    Employeeaddress permAddress=new Employeeaddress();
    employebasicdetails.setEmployeeID(employeeBasicDetails.getEmployeeID());
    employebasicdetails.setFirstName(employeeBasicDetails.getFirstName());
    employebasicdetails.setMiddleName(employeeBasicDetails.getMiddleName());
    employebasicdetails.setLastName(employeeBasicDetails.getLastName());
    employebasicdetails.setEmailID(employeeBasicDetails.getEmailID());
    employebasicdetails.setPhoneNumber(employeeBasicDetails.getPhoneNumber());
    tempAddress.setHouseName(employeeBasicDetails.getTemporaryEmployeeAddress().getHouseName());
    tempAddress.setHouseStreet(employeeBasicDetails.getTemporaryEmployeeAddress().getHouseStreet());
    tempAddress.setCityName(employeeBasicDetails.getTemporaryEmployeeAddress().getCityName());
    tempAddress.setStateName(employeeBasicDetails.getTemporaryEmployeeAddress().getStateName());
    tempAddress.setPinCode(employeeBasicDetails.getTemporaryEmployeeAddress().getPinCode());

    permAddress.setHouseName(employeeBasicDetails.getPermanentEmployeeAddress().getHouseName());
    permAddress.setHouseStreet(employeeBasicDetails.getPermanentEmployeeAddress().getHouseStreet());
    permAddress.setCityName(employeeBasicDetails.getPermanentEmployeeAddress().getCityName());
    permAddress.setStateName(employeeBasicDetails.getPermanentEmployeeAddress().getStateName());
    permAddress.setPinCode(employeeBasicDetails.getPermanentEmployeeAddress().getPinCode());

    employebasicdetails.setTemporaryEmployeeAddress(tempAddress);
    employebasicdetails.setPermanentEmployeeAddress(permAddress);
    return employebasicdetails;
}

    public EmployeeBasicDetails copy(Employebasicdetails employebasicdetails) {
        EmployeeBasicDetails employeeBasicDetails = new EmployeeBasicDetails();
        EmployeeAddress tempAddress = new EmployeeAddress();
        EmployeeAddress permAddress = new EmployeeAddress();

        employeeBasicDetails.setEmployeeID(employebasicdetails.getEmployeeID());
        employeeBasicDetails.setFirstName(employebasicdetails.getFirstName());
        employeeBasicDetails.setMiddleName(employebasicdetails.getMiddleName());
        employeeBasicDetails.setLastName(employebasicdetails.getLastName());
        employeeBasicDetails.setEmailID(employebasicdetails.getEmailID());
        employeeBasicDetails.setPhoneNumber(employebasicdetails.getPhoneNumber());

        tempAddress.setHouseName(employebasicdetails.getTemporaryEmployeeAddress().getHouseName());
        tempAddress.setHouseStreet(employebasicdetails.getTemporaryEmployeeAddress().getHouseStreet());
        tempAddress.setCityName(employebasicdetails.getTemporaryEmployeeAddress().getCityName());
        tempAddress.setStateName(employebasicdetails.getTemporaryEmployeeAddress().getStateName());
        tempAddress.setPinCode(employebasicdetails.getTemporaryEmployeeAddress().getPinCode());

        permAddress.setHouseName(employebasicdetails.getPermanentEmployeeAddress().getHouseName());
        permAddress.setHouseStreet(employebasicdetails.getPermanentEmployeeAddress().getHouseStreet());
        permAddress.setCityName(employebasicdetails.getPermanentEmployeeAddress().getCityName());
        permAddress.setStateName(employebasicdetails.getPermanentEmployeeAddress().getStateName());
        permAddress.setPinCode(employebasicdetails.getPermanentEmployeeAddress().getPinCode());

        employeeBasicDetails.setTemporaryEmployeeAddress(tempAddress);
        employeeBasicDetails.setPermanentEmployeeAddress(permAddress);

        return employeeBasicDetails;
    }

}