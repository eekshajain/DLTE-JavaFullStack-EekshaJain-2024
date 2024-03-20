package org.consoleEnv;



//import com.sun.org.slf4j.Logger;


import employee.implementation.EmployeeExceptions;
import employee.implementation.ReadAndDisplayUsingDatabase;
import employee.interfaces.Employee;
import employee.interfaces.EmployeeAddress;
import employee.interfaces.EmployeeBasicDetails;
import employee.interfaces.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import validate.data.ValidationOfData;

import java.sql.SQLException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App
{
    static Logger logger= LoggerFactory.getLogger("App.class");//import from org.slf4j
    public static void main( String[] args )
    {
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        Scanner scanner2=new Scanner(System.in);
        Scanner scanner3=new Scanner(System.in);
        InputEmployeeDetails employeeDetails=null;
      //  EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        EmployeeBasicDetailsConsole employeeBasicDetails1=new EmployeeBasicDetailsConsole();
        EmployeeAddressConsole tempEmployeeAddress1=new EmployeeAddressConsole();
        EmployeeAddressConsole permEmployeeAddress1=new EmployeeAddressConsole();
        List<Employee> employeeArrayList=new ArrayList<>();
        ValidationOfData validationData=new ValidationOfData();
        //System.setProperty("logbackConfiguration","C:\\DLTE-JavaFullStack-EekshaJain-2024\\DLTE-Java\\EmployeeDetails\\logback.xml");//or else add log file in resource file
        try {
                employeeDetails = new ReadAndDisplayUsingDatabase();
        while (true) {
            System.out.println(resourceBundle.getString("menu.display"));
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    logger.info("Enter data to employee list");
                    logger.isEnabledForLevel(Level.INFO);
                    do {
                        Employee employee = new Employee();
                        System.out.println(resourceBundle.getString("employee.id"));
                        try {
                            int empID = scanner.nextInt();
                            if (employeeDetails.doesEmployeeExists(empID)) {
                                throw new EmployeeException();
                            } else {
                                employeeBasicDetails1.setEmployeeID(empID);
                            }
                        } catch (EmployeeException e) {
                            System.out.println(resourceBundle.getString("employee.exists"));
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
                                    throw new EmployeeException();
                                }
                            } catch (EmployeeException e) {
                                System.out.println(resourceBundle.getString("invalid.phone.number"));
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
                                } else throw new EmployeeException();
                            } catch (EmployeeException e) {
                                System.out.println(resourceBundle.getString("email.validation"));
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
                                } else throw new EmployeeException();
                            } catch (EmployeeException e) {
                                System.out.println(resourceBundle.getString("invalid.pincode"));
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
                        EmployeeBasicDetails employeeBasicDetails;
                        employeeBasicDetails = translateEmployeeBasic(employeeBasicDetails1);
                        EmployeeAddress tempEmployeeAddress;
                        tempEmployeeAddress = translateEmployeeAddress(tempEmployeeAddress1);
                        EmployeeAddress permEmployeeAddress;
                        permEmployeeAddress = translateEmployeeAddress(permEmployeeAddress1);
                        employee = new Employee(employeeBasicDetails, tempEmployeeAddress, permEmployeeAddress);
                        try {
                            if(employeeDetails.saveAll(employee)) System.out.println("Employee Details added successfully!");
                            else System.out.println("Failed to add employee details!");
                        }catch(EmployeeExceptions employeeExceptions){
                            System.out.println(employeeExceptions.getMessage());
                        }
                        System.out.println("Do you want to add more?");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    break;
                case 2:
                    int employeeId;
                    System.out.println(resourceBundle.getString("employee.id"));
                    employeeId = scanner.nextInt();
                    try {
                        if (employeeDetails.doesEmployeeExists(employeeId)) {
                            System.out.println(employeeDetails.displayRequired(employeeId));
                        } else throw new EmployeeException();
                    }catch(EmployeeException e){
                        logger.error("Employee with employee ID "+employeeId+"does not exist");
                        System.out.println(resourceBundle.getString("employee.doesNotExists"));
                    }
                    break;
                case 3:
                    logger.info("Displaying all details");
                    //System.out.println(employeeDetails.displayAll());
                    List<Employee> employee=employeeDetails.displayAll();
                    for(Employee employee1:employee) {
                        EmployeeConsole employeeConsole;
                        employeeConsole = translateBack(employee1);
                        System.out.println(employeeConsole);
                    }
                    break;
                case 4:
                    logger.info("Display required details based on Temporary pincode");
                    System.out.println("Enter Temporary Pincode");
                    System.out.println(employeeDetails.displayBasedOnPinCode(scanner.nextInt()));
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println(resourceBundle.getString("invalid.choice"));
                    continue;
            }
        }
            }catch(EmployeeExceptions employeeExceptions){
            System.out.println(employeeExceptions.getErrorMessage());
        } catch (InputMismatchException e){
                System.out.println(resourceBundle.getString("number.only"));
                scanner.next();
            } catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        finally {
                scanner.close();
                scanner1.close();
                scanner2.close();
                scanner3.close();
                if(employeeDetails!=null) employeeDetails.close();
            }
    }

    private static EmployeeConsole translateBack(Employee employee) {
       EmployeeBasicDetailsConsole employeeBasicDetailsConsole=new EmployeeBasicDetailsConsole();
       EmployeeAddressConsole tempAddress=new EmployeeAddressConsole();
       EmployeeAddressConsole permAddress=new EmployeeAddressConsole();
       employeeBasicDetailsConsole.setFirstName(employee.getEmployeeBasicDetails().getFirstName());
       employeeBasicDetailsConsole.setMiddleName(employee.getEmployeeBasicDetails().getMiddleName());
       employeeBasicDetailsConsole.setLastName(employee.getEmployeeBasicDetails().getLastName());
       employeeBasicDetailsConsole.setEmployeeID(employee.getEmployeeBasicDetails().getEmployeeID());
       employeeBasicDetailsConsole.setEmailID(employee.getEmployeeBasicDetails().getEmailID());
       employeeBasicDetailsConsole.setPhoneNumber(employee.getEmployeeBasicDetails().getPhoneNumber());

       tempAddress.setHouseName(employee.getTemporaryEmployeeAddress().getHouseName());
       tempAddress.setHouseStreet(employee.getTemporaryEmployeeAddress().getHouseName());
       tempAddress.setCityName(employee.getTemporaryEmployeeAddress().getCityName());
       tempAddress.setStateName(employee.getTemporaryEmployeeAddress().getStateName());
       tempAddress.setPinCode(employee.getTemporaryEmployeeAddress().getPinCode());

       permAddress.setHouseName(employee.getPermanentEmployeeAddress().getHouseName());
       permAddress.setHouseStreet(employee.getPermanentEmployeeAddress().getHouseStreet());
       permAddress.setCityName(employee.getPermanentEmployeeAddress().getCityName());
       permAddress.setStateName(employee.getPermanentEmployeeAddress().getStateName());
       permAddress.setPinCode(employee.getPermanentEmployeeAddress().getPinCode());
       return new EmployeeConsole(employeeBasicDetailsConsole,tempAddress,permAddress);
    }

    private static EmployeeAddress translateEmployeeAddress(EmployeeAddressConsole address) {
        EmployeeAddress employeeAddress =new EmployeeAddress();
        employeeAddress.setHouseName(address.getHouseName());
        employeeAddress.setHouseStreet(address.getHouseStreet());
        employeeAddress.setCityName(address.getCityName());
        employeeAddress.setStateName(address.getStateName());
        employeeAddress.setPinCode(address.getPinCode());
        return employeeAddress;
    }

    private static EmployeeBasicDetails translateEmployeeBasic(EmployeeBasicDetailsConsole employeeBasicDetails1) {
       EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
        employeeBasicDetails.setEmployeeID(employeeBasicDetails1.getEmployeeID());
        employeeBasicDetails.setFirstName(employeeBasicDetails1.getFirstName());
        employeeBasicDetails.setMiddleName(employeeBasicDetails1.getMiddleName());
        employeeBasicDetails.setLastName(employeeBasicDetails1.getLastName());
        employeeBasicDetails.setEmailID(employeeBasicDetails1.getEmailID());
        employeeBasicDetails.setPhoneNumber(employeeBasicDetails1.getPhoneNumber());
        return employeeBasicDetails;
    }
}
