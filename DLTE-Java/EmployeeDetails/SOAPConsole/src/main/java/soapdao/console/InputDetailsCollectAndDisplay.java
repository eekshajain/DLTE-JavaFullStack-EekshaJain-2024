package soapdao.console;


import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import soapdao.EmployeeException;
import soapdao.implementation.*;
import validate.data.ValidationOfData;

import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InputDetailsCollectAndDisplay {
    WebServiceDAOService webServiceDAOService=new WebServiceDAOService();
    WebServiceDAO webServiceDAO;
    private static final Logger logger = LoggerFactory.getLogger(InputDetailsCollectAndDisplay.class);    //static Logger logger= LoggerFactory.getLogger("App.class");//import from org.slf4j
    Scanner scanner=new Scanner(System.in);
    Scanner scanner1=new Scanner(System.in);
    Scanner scanner2=new Scanner(System.in);
    Scanner scanner3=new Scanner(System.in);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    public InputDetailsCollectAndDisplay() {
        webServiceDAO = new WebServiceDAOService().getWebServiceDAOPort();
        resourceBundle = ResourceBundle.getBundle("application");
        scanner = new Scanner(System.in);
    }
    //to collect details
    public void callCollectDetails( ){
       soapdao.entity.EmployeeBasicDetails employeeBasicDetails1=new soapdao.entity.EmployeeBasicDetails();
        soapdao.entity.EmployeeAddress tempEmployeeAddress1=new soapdao.entity.EmployeeAddress();
        soapdao.entity.EmployeeAddress permEmployeeAddress1=new soapdao.entity.EmployeeAddress();
        ValidationOfData validationData=new ValidationOfData();
        logger.info("Enter data to employee list");
        logger.isEnabledForLevel(Level.INFO);
        do {
            Employee employee = new Employee();
            System.out.println(resourceBundle.getString("employee.id"));
            try {
                int empID = scanner.nextInt();
                if (webServiceDAO.callEmployeeExists(empID)) {
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
            soapdao.implementation.EmployeeBasicDetails employeeBasicDetails;
            employeeBasicDetails = translateEmployeeBasic(employeeBasicDetails1);
            soapdao.implementation.EmployeeAddress tempEmployeeAddress;
            tempEmployeeAddress = translateEmployeeAddress(tempEmployeeAddress1);
            soapdao.implementation.EmployeeAddress permEmployeeAddress;
            permEmployeeAddress = translateEmployeeAddress(permEmployeeAddress1);
            employee = new soapdao.implementation.Employee(employeeBasicDetails, tempEmployeeAddress, permEmployeeAddress);
            try {
                if(webServiceDAO.callSaveAll(employee) != null) System.out.println("Employee Details added successfully!");
                else System.out.println("Failed to add employee details!");
            }catch(EmployeeException employeeExceptions){
                System.out.println(employeeExceptions.getMessage());
            }
            System.out.println("Do you want to add more?");
        } while (scanner.next().equalsIgnoreCase("yes"));
    }

    //to display based on employee id
    public void callDisplayRequired(){
        int employeeId;
        System.out.println(resourceBundle.getString("employee.id"));
        employeeId = scanner.nextInt();
        try {
            if (webServiceDAO.callEmployeeExists(employeeId)) {
                logger.info("Displaying the info of particular ID:"+employeeId);
              //  System.out.println(webServiceDAO.callFilterBasedOnID(employeeId));
                soapdao.implementation.Employee employee=webServiceDAO.callFilterBasedOnID(employeeId);
                soapdao.entity.Employee employeeConsole = translateBack(employee);
                System.out.println(employeeConsole);
            } else throw new EmployeeException("employee.doesNotExists");
        }catch(EmployeeException e){
            logger.error("Employee with employee ID "+employeeId+"does not exist");
            System.out.println(e.getMessage());
        }
    }

    public void callDisplayAll(){
        logger.info("Displaying all details");
        //System.out.println(employeeDetails.displayAll());
//        List<soapdao.implementation.Employee> employee= (List<Employee>) webServiceDAO.callFindAll();
        soapdao.implementation.GroupOfEmployees employeesGroup = webServiceDAO.callFindAll();
        List<soapdao.implementation.Employee> employeeList = employeesGroup.getEmployeesArrayList();
        for(soapdao.implementation.Employee employee1:employeeList) {
            soapdao.entity.Employee employeeConsole;
            employeeConsole = translateBack(employee1);
            System.out.println(employeeConsole);
        }
    }

    public void callDisplayRequiredPincode(){
        logger.info("Display required details based on Temporary pincode");
        System.out.println("Enter Pincode");
        int pincode=scanner.nextInt();
        soapdao.implementation.GroupOfEmployees employeesGroup = webServiceDAO.callFilterBasedOnPincode(pincode);
        List<soapdao.implementation.Employee> employeePincode = employeesGroup.getEmployeesArrayList();
       // List<soapdao.implementation.Employee> employeePincode= (List<Employee>) webServiceDAO.callFilterBasedOnPincode(pincode);
        if(employeePincode.isEmpty()){
            System.out.println("No employees found for the given pincode.");
        }else {
            System.out.println(employeePincode.size());
            for (Employee employee : employeePincode) {
                soapdao.entity.Employee employeeConsole = translateBack(employee);
                System.out.println(employeeConsole);
            }
        }

    }

    public void displayBasedOnPincode(){
        System.out.println("Enter Pincode");
        int pincode=scanner.nextInt();
        soapdao.implementation.GroupOfEmployees employeesGroup = webServiceDAO.callFindAll();
        List<soapdao.implementation.Employee> employeeList = employeesGroup.getEmployeesArrayList();
        DisplayBasedOnPincode displayBasedOnPincode=((pincode1 -> {
            for(Employee employee:employeeList){
                if(employee.getPermanentEmployeeAddress().getPinCode()==pincode1 || employee.getPermanentEmployeeAddress().getPinCode()==pincode1){
                    soapdao.entity.Employee employeeConsole = translateBack(employee);
                    System.out.println(employeeConsole);
                }
            }
        }));
        displayBasedOnPincode.filterPincode(pincode);

    }

    public static soapdao.entity.Employee translateBack(Employee employee) {
        soapdao.entity.EmployeeBasicDetails employeeBasicDetailsConsole= new soapdao.entity.EmployeeBasicDetails();
        soapdao.entity.EmployeeAddress tempAddress= new soapdao.entity.EmployeeAddress();
        soapdao.entity.EmployeeAddress permAddress= new soapdao.entity.EmployeeAddress();
        employeeBasicDetailsConsole.setFirstName(employee.getEmployeeBasicDetails().getFirstName());
        employeeBasicDetailsConsole.setMiddleName(employee.getEmployeeBasicDetails().getMiddleName());
        employeeBasicDetailsConsole.setLastName(employee.getEmployeeBasicDetails().getLastName());
        employeeBasicDetailsConsole.setEmployeeID(employee.getEmployeeBasicDetails().getEmployeeID());
        employeeBasicDetailsConsole.setEmailID(employee.getEmployeeBasicDetails().getEmailID());
        employeeBasicDetailsConsole.setPhoneNumber(employee.getEmployeeBasicDetails().getPhoneNumber());

        tempAddress.setHouseName(employee.getTemporaryEmployeeAddress().getHouseName());
        tempAddress.setHouseStreet(employee.getTemporaryEmployeeAddress().getHouseStreet());
        tempAddress.setCityName(employee.getTemporaryEmployeeAddress().getCityName());
        tempAddress.setStateName(employee.getTemporaryEmployeeAddress().getStateName());
        tempAddress.setPinCode(employee.getTemporaryEmployeeAddress().getPinCode());

        permAddress.setHouseName(employee.getPermanentEmployeeAddress().getHouseName());
        permAddress.setHouseStreet(employee.getPermanentEmployeeAddress().getHouseStreet());
        permAddress.setCityName(employee.getPermanentEmployeeAddress().getCityName());
        permAddress.setStateName(employee.getPermanentEmployeeAddress().getStateName());
        permAddress.setPinCode(employee.getPermanentEmployeeAddress().getPinCode());
        soapdao.entity.Employee employee1=new  soapdao.entity.Employee();
        employee1.setEmployeeBasicDetails(employeeBasicDetailsConsole);
        employee1.setTemporaryEmployeeAddress(tempAddress);
        employee1.setTemporaryEmployeeAddress(permAddress);
        //return employee1;
        return new soapdao.entity.Employee(employeeBasicDetailsConsole,tempAddress,permAddress);

    }
    public static soapdao.implementation.EmployeeAddress translateEmployeeAddress(soapdao.entity.EmployeeAddress address) {
        soapdao.implementation.EmployeeAddress employeeAddress =new soapdao.implementation.EmployeeAddress();
        employeeAddress.setHouseName(address.getHouseName());
        employeeAddress.setHouseStreet(address.getHouseStreet());
        employeeAddress.setCityName(address.getCityName());
        employeeAddress.setStateName(address.getStateName());
        employeeAddress.setPinCode(address.getPinCode());
        return employeeAddress;
    }

    public static soapdao.implementation.EmployeeBasicDetails translateEmployeeBasic(soapdao.entity.EmployeeBasicDetails employeeBasicDetails1) {
        soapdao.implementation.EmployeeBasicDetails employeeBasicDetails=new soapdao.implementation.EmployeeBasicDetails();
        employeeBasicDetails.setEmployeeID(employeeBasicDetails1.getEmployeeID());
        employeeBasicDetails.setFirstName(employeeBasicDetails1.getFirstName());
        employeeBasicDetails.setMiddleName(employeeBasicDetails1.getMiddleName());
        employeeBasicDetails.setLastName(employeeBasicDetails1.getLastName());
        employeeBasicDetails.setEmailID(employeeBasicDetails1.getEmailID());
        employeeBasicDetails.setPhoneNumber(employeeBasicDetails1.getPhoneNumber());
        return employeeBasicDetails;
    }
}
