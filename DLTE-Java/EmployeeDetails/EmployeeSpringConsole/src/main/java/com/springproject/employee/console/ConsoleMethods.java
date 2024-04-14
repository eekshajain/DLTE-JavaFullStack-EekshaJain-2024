package com.springboot.webservices.console;

import com.springboot.webservices.configuration.SoapConfiguration;
import com.springboot.webservices.controller.EmployeeController;
import com.springboot.webservices.exceptions.EmployeeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;
import services.employee.*;
import validate.data.ValidationOfData;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ConsoleMethods {
//    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//    WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);
    private static final Logger logger = LoggerFactory.getLogger(ConsoleMethods.class);    //static Logger logger= LoggerFactory.getLogger("App.class");//import from org.slf4j
    Scanner scanner=new Scanner(System.in);
    Scanner scanner1=new Scanner(System.in);
    Scanner scanner2=new Scanner(System.in);
    Scanner scanner3=new Scanner(System.in);
    EmployeeController employeeController=new EmployeeController();
    ResourceBundle resourceBundle=ResourceBundle.getBundle("employee");
    public void callCollectDetails( ){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapConfiguration.class);
        WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);
        Employebasicdetails employeeBasicDetails1=new Employebasicdetails();
        Employeeaddress tempEmployeeAddress1=new Employeeaddress();
        Employeeaddress permEmployeeAddress1=new Employeeaddress();
        ValidationOfData validationData=new ValidationOfData();
        ResourceBundle resourceBundle=ResourceBundle.getBundle("employee");
        logger.info("Enter data to employee list");
        do {
            System.out.println(resourceBundle.getString("employee.id"));
            try {
                EmployeeExistsRequest employeeExistsRequest=new EmployeeExistsRequest();
                int empID = scanner.nextInt();
                employeeExistsRequest.setEmployeeId(empID);
                EmployeeExistsResponse employeeExistsResponse=employeeController.doesEmployeeExist(employeeExistsRequest);
                if (employeeExistsResponse.getServiceStatus().getStatus()== HttpServletResponse.SC_OK) {
                    throw new EmployeeException("employee.exists");
                } else {
                    employeeBasicDetails1.setEmployeeID(empID);
                }
            } catch (EmployeeException e) {
                System.out.println(e.getErrorMessage());
                break;
            } catch (InputMismatchException e) {
                System.out.println(resourceBundle.getString("number.only"));
                break;
            }
            System.out.println(resourceBundle.getString("full.name"));
            System.out.println(resourceBundle.getString("first.name"));
            employeeBasicDetails1.setFirstName(scanner.next());
            System.out.println(resourceBundle.getString("middle.name"));
            employeeBasicDetails1.setMiddleName(scanner.next());
            System.out.println(resourceBundle.getString("last.name"));
            employeeBasicDetails1.setLastName(scanner.next());
            System.out.println(resourceBundle.getString("contact.details"));
            while (true) {
                try {
                    System.out.println(resourceBundle.getString("phone.number"));
                    long phoneNumber = scanner1.nextLong();
                    if (validationData.isPhoneNumberValid(phoneNumber)) {
                        employeeBasicDetails1.setPhoneNumber(phoneNumber);
                        break;
                    } else {
                        throw new EmployeeException("invalid.phone.number");
                    }
                } catch (EmployeeException e) {
                    System.out.println(e.getErrorMessage());
                } catch (InputMismatchException e) {
                    System.out.println(resourceBundle.getString("number.only"));
                    scanner1.nextLine(); // Consume the invalid input
                }
            }
            while (true) {
                try {
                    System.out.println(resourceBundle.getString("email.id"));
                    String mail = scanner.next();
                    if (validationData.isEmailValid(mail)) {
                        employeeBasicDetails1.setEmailID(mail);
                        break;
                    } else throw new EmployeeException("email.validation");
                } catch (EmployeeException e) {
                    System.out.println(e.getErrorMessage());
                }
            }
            System.out.println(resourceBundle.getString("temporary.address"));
            System.out.println(resourceBundle.getString("house.name"));
            tempEmployeeAddress1.setHouseName(scanner2.nextLine());
            System.out.println(resourceBundle.getString("street.name"));
            tempEmployeeAddress1.setHouseStreet(scanner2.nextLine());
            System.out.println(resourceBundle.getString("city.name"));
            tempEmployeeAddress1.setCityName(scanner2.nextLine());
            System.out.println(resourceBundle.getString("state.name"));
            tempEmployeeAddress1.setStateName(scanner2.nextLine());
            while (true) {
                try {
                    System.out.println(resourceBundle.getString("pin.code"));
                    int pincode = scanner1.nextInt();
                    if (validationData.isPinCodeValid(pincode)) {
                        tempEmployeeAddress1.setPinCode(pincode);
                        break;
                    } else throw new EmployeeException("invalid.pincode");
                } catch (EmployeeException e) {
                    System.out.println(e.getErrorMessage());
                } catch (InputMismatchException e) {
                    System.out.println(resourceBundle.getString("number.only"));
                    scanner1.nextLine();
                }
            }
            System.out.println(resourceBundle.getString("permanent.address"));
            System.out.println(resourceBundle.getString("house.name"));
            permEmployeeAddress1.setHouseName(scanner3.nextLine());
            System.out.println(resourceBundle.getString("street.name"));
            permEmployeeAddress1.setHouseStreet(scanner3.nextLine());
            System.out.println(resourceBundle.getString("city.name"));
            permEmployeeAddress1.setCityName(scanner3.nextLine());
            System.out.println(resourceBundle.getString("state.name"));
            permEmployeeAddress1.setStateName(scanner3.nextLine());
            while (true) {
                try {
                    System.out.println(resourceBundle.getString("pin.code"));
                    int pincode = scanner1.nextInt();
                    if (validationData.isPinCodeValid(pincode)) {
                        permEmployeeAddress1.setPinCode(pincode);
                        break;
                    }
                } catch (EmployeeException e) {
                    System.out.println(resourceBundle.getString("invalid.pincode"));
                } catch (InputMismatchException e) {
                    System.out.println(resourceBundle.getString("number.only"));
                    scanner1.nextLine();
                }
            }
            logger.info("Data to be added to array list");
            employeeBasicDetails1.setTemporaryEmployeeAddress(tempEmployeeAddress1);
            employeeBasicDetails1.setPermanentEmployeeAddress(permEmployeeAddress1);
            // employee = new soapdao.implementation.Employee(employeeBasicDetails, tempEmployeeAddress, permEmployeeAddress);
            try {
                NewEmployeeRequest newEmployeeRequest=new NewEmployeeRequest();
                newEmployeeRequest.setEmployeeBasic(employeeBasicDetails1);
                NewEmployeeResponse newEmployeeResponse=employeeController.addNewEmployee(newEmployeeRequest);
                if(newEmployeeResponse.getServiceStatus().getStatus()==HttpServletResponse.SC_OK) System.out.println("Employee Details added successfully!");
                else System.out.println("Failed to add employee details!");
            }catch(EmployeeException employeeExceptions){
                System.out.println(employeeExceptions.getMessage());
            }
            System.out.println("Do you want to add more?");
        } while (scanner.next().equalsIgnoreCase("yes"));
        context.close();
    }

    public void callDisplayAll(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapConfiguration.class);
        WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);
        logger.info("Displaying all details");
        //System.out.println(employeeDetails.displayAll());
//        List<soapdao.implementation.Employee> employee= (List<Employee>) webServiceDAO.callFindAll();
        DisplayAllRequest displayAllRequest=new DisplayAllRequest();
        DisplayAllResponse displayAllResponse= (DisplayAllResponse) webServiceTemplate.marshalSendAndReceive(displayAllRequest);
        ServiceStatus serviceStatus=new ServiceStatus();
        System.out.println(serviceStatus.getMessage());
        List<Employebasicdetails> employebasicdetails=displayAllResponse.getEmployee();
        for(Employebasicdetails employebasicdetails1:employebasicdetails){
            System.out.println(employebasicdetails1);
            System.out.println();
        }
        context.close();
        }
    public void callDisplayRequiredPincode(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapConfiguration.class);
        WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);
        logger.info("Display required details based on Temporary pincode");
        System.out.println("Enter Pincode");
        int pincode=scanner.nextInt();
        DisplayBasedOnPincodeRequest displayBasedOnPincodeRequest=new DisplayBasedOnPincodeRequest();
        displayBasedOnPincodeRequest.setPinCode(pincode);
        DisplayBasedOnPincodeResponse displayBasedOnPincodeResponse= (DisplayBasedOnPincodeResponse) webServiceTemplate.marshalSendAndReceive(displayBasedOnPincodeRequest);
        List<Employebasicdetails> employebasicdetails=displayBasedOnPincodeResponse.getEmployee();
        // List<soapdao.implementation.Employee> employeePincode= (List<Employee>) webServiceDAO.callFilterBasedOnPincode(pincode);
        if(employebasicdetails.isEmpty()){
            System.out.println("No employees found for the given pincode.");
        }else {
            System.out.println(employebasicdetails.size());
            for (Employebasicdetails employee : employebasicdetails) {
                System.out.println(employee);
                System.out.println();
            }
        }
    }

        public void callDisplayRequired(){
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SoapConfiguration.class);
            WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);
        int employeeId;
        System.out.println(resourceBundle.getString("employee.id"));
        employeeId = scanner.nextInt();
        try {
            EmployeeExistsRequest employeeExistsRequest=new EmployeeExistsRequest();
            employeeExistsRequest.setEmployeeId(employeeId);
            EmployeeExistsResponse employeeExistsResponse= (EmployeeExistsResponse) webServiceTemplate.marshalSendAndReceive(employeeExistsRequest);
           boolean employebasicdetails=employeeExistsResponse.isEmployeeExists();
            if (employebasicdetails) {
                logger.info("Displaying the info of particular ID:"+employeeId);
              //  System.out.println(webServiceDAO.callFilterBasedOnID(employeeId));
               DisplayBasedOnIdRequest displayBasedOnIdRequest=new DisplayBasedOnIdRequest();
               displayBasedOnIdRequest.setEmployeeID(employeeId);
               DisplayBasedOnIdResponse displayBasedOnIdResponse= (DisplayBasedOnIdResponse) webServiceTemplate.marshalSendAndReceive(displayBasedOnIdRequest);
                Employebasicdetails employebasicdetails1=displayBasedOnIdResponse.getEmployee();
                System.out.println(employebasicdetails1);
            } else throw new EmployeeException("employee.doesNotExists");
        }catch(EmployeeException e){
            logger.error("Employee with employee ID "+employeeId+"does not exist");
            System.out.println(e.getMessage());
        }
    }

}
