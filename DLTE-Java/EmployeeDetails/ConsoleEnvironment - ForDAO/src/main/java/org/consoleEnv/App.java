package org.consoleEnv;



//import com.sun.org.slf4j.Logger;
import org.example.ReadAndDisplayUsingDatabase;
import org.middleware.Employee;
import org.middleware.EmployeeAddress;
import org.middleware.EmployeeBasicDetails;
import org.middleware.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import validate.data.ValidationOfData;

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
        InputEmployeeDetails employeeDetails=new ReadAndDisplayUsingDatabase();
      //  EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
        EmployeeAddress tempEmployeeAddress=new EmployeeAddress();
        EmployeeAddress permEmployeeAddress=new EmployeeAddress();
        List<Employee> employeeArrayList=new ArrayList<>();
        ValidationOfData validationData=new ValidationOfData();
        //System.setProperty("logbackConfiguration","C:\\DLTE-JavaFullStack-EekshaJain-2024\\DLTE-Java\\EmployeeDetails\\logback.xml");//or else add log file in resource file
        while (true){
            System.out.println(resourceBundle.getString("menu.display"));
            int choice=scanner.nextInt();
            try {
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
                                    employeeBasicDetails.setEmployeeID(empID);
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
                            employeeBasicDetails.setFirstName(scanner.next());
                            System.out.println(resourceBundle.getString("middle.name"));
                            employeeBasicDetails.setMiddleName(scanner.next());
                            System.out.println(resourceBundle.getString("last.name"));
                            employeeBasicDetails.setLastName(scanner.next());
                            System.out.println(resourceBundle.getString("contact.details"));
                            while (true) {
                                try {
                                    System.out.println(resourceBundle.getString("phone.number"));
                                    long phoneNumber = scanner1.nextLong();
                                    if (validationData.isPhoneNumberValid(phoneNumber)) {
                                        employeeBasicDetails.setPhoneNumber(phoneNumber);
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
                                        employeeBasicDetails.setEmailID(mail);
                                        break;
                                    } else throw new EmployeeException();
                                } catch (EmployeeException e) {
                                    System.out.println(resourceBundle.getString("email.validation"));
                                }
                            }
                            System.out.println(resourceBundle.getString("temporary.address"));
                            System.out.println(resourceBundle.getString("house.name"));
                            tempEmployeeAddress.setHouseName(scanner2.nextLine());
                            System.out.println(resourceBundle.getString("street.name"));
                            tempEmployeeAddress.setHouseStreet(scanner2.nextLine());
                            System.out.println(resourceBundle.getString("city.name"));
                            tempEmployeeAddress.setCityName(scanner2.nextLine());
                            System.out.println(resourceBundle.getString("state.name"));
                            tempEmployeeAddress.setStateName(scanner2.nextLine());
                            while (true) {
                                try {
                                    System.out.println(resourceBundle.getString("pin.code"));
                                    int pincode = scanner1.nextInt();
                                    if (validationData.isPinCodeValid(pincode)) {
                                        tempEmployeeAddress.setPinCode(pincode);
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
                            permEmployeeAddress.setHouseName(scanner3.nextLine());
                            System.out.println(resourceBundle.getString("street.name"));
                            permEmployeeAddress.setHouseStreet(scanner3.nextLine());
                            System.out.println(resourceBundle.getString("city.name"));
                            permEmployeeAddress.setCityName(scanner3.nextLine());
                            System.out.println(resourceBundle.getString("state.name"));
                            permEmployeeAddress.setStateName(scanner3.nextLine());
                            while (true) {
                                try {
                                    System.out.println(resourceBundle.getString("pin.code"));
                                    int pincode = scanner1.nextInt();
                                    if (validationData.isPinCodeValid(pincode)) {
                                        permEmployeeAddress.setPinCode(pincode);
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
                            employee = new Employee(employeeBasicDetails, tempEmployeeAddress, permEmployeeAddress);
                            employeeDetails.saveAll(employee);
                            System.out.println("Do you want to add more?");
                        } while (scanner.next().equalsIgnoreCase("yes"));
                        break;
                    case 2:
                        logger.info("Display required details based on Employee ID");
                        System.out.println(resourceBundle.getString("employee.id"));
                        System.out.println(employeeDetails.displayRequired(scanner.nextInt()));
                        break;
                    case 3:
                        logger.info("Displaying all details");
                        System.out.println(employeeDetails.displayAll());
                        break;
                    case 4:
                        logger.info("Display required details based on Temporary pincode");
                        System.out.println("Enter Temporary Pincode");
                        System.out.println(employeeDetails.displayBasedOnPinCode(scanner.nextInt()));
                        break;
                }
            }catch (InputMismatchException e){
                System.out.println(resourceBundle.getString("number.only"));
            }
        }

    }
}
