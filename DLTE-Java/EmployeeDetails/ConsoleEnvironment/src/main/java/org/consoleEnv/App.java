package org.consoleEnv;



//import com.sun.org.slf4j.Logger;
import org.files.ReadAndDisplay;
import org.middleware.Employee;
import org.middleware.EmployeeAddress;
import org.middleware.EmployeeBasicDetails;
import org.middleware.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

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
        InputEmployeeDetails employeeDetails=new ReadAndDisplay();
        EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        EmployeeAddress employeeAddress=new EmployeeAddress();
        List<Employee> employeeArrayList=new ArrayList<>();
        //System.setProperty("logbackConfiguration","C:\\DLTE-JavaFullStack-EekshaJain-2024\\DLTE-Java\\EmployeeDetails\\logback.xml");//or else add log file in resource file
        while (true){
            System.out.println(resourceBundle.getString("menu.display"));
            switch (scanner.nextInt()){
                case 1:logger.info("Enter data to employee list");
                logger.isEnabledForLevel(Level.INFO);
                    do{
                        System.out.println(resourceBundle.getString("employee.id"));
                        try{
                        int empID=scanner.nextInt();
                        if(employeeDetails.doesEmployeeExists(empID)){
                            throw new EmployeeException();
                        }else{
                            employeeBasicDetails.setEmployeeID(empID);
                        }
                        }catch (EmployeeException e){
                            System.out.println(resourceBundle.getString("employee.exists"));
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
                        System.out.println(resourceBundle.getString("phone.number"));
                        employeeBasicDetails.setPhoneNumber(scanner1.nextLong());
                        System.out.println(resourceBundle.getString("email.id"));
                        employeeBasicDetails.setEmailID(scanner.next());
                        System.out.println(resourceBundle.getString("temporary.address"));
                        System.out.println(resourceBundle.getString("house.name"));
                        employeeAddress.setTemporaryHouseName(scanner2.nextLine());
                        System.out.println(resourceBundle.getString("street.name"));
                        employeeAddress.setTemporaryHouseStreet(scanner2.nextLine());
                        System.out.println(resourceBundle.getString("city.name"));
                        employeeAddress.setTemporaryCityName(scanner2.nextLine());
                        System.out.println(resourceBundle.getString("state.name"));
                        employeeAddress.setTemporaryStateName(scanner2.nextLine());
                        System.out.println(resourceBundle.getString("pin.code"));
                        employeeAddress.setTemporaryPinCode(scanner1.nextInt());
                        System.out.println(resourceBundle.getString("permanent.address"));
                        System.out.println(resourceBundle.getString("house.name"));
                        employeeAddress.setPermanentHouseName(scanner3.nextLine());
                        System.out.println(resourceBundle.getString("street.name"));
                        employeeAddress.setPermanentHouseStreet(scanner3.nextLine());
                        System.out.println(resourceBundle.getString("city.name"));
                        employeeAddress.setPermanentCityName(scanner3.nextLine());
                        System.out.println(resourceBundle.getString("state.name"));
                        employeeAddress.setPermanentStateName(scanner3.nextLine());
                        System.out.println(resourceBundle.getString("pin.code"));
                        employeeAddress.setPermanentPinCode(scanner1.nextInt());
                        logger.info("Data to be added to array list");
                        employeeArrayList.add(new Employee(employeeBasicDetails,employeeAddress));
                        employeeDetails.saveAll(employeeArrayList);
                        System.out.println("Do you want to add more?");
                    }while (scanner.next().equalsIgnoreCase("yes"));
                    break;
            case 2:  logger.info("Display required details based on Employee ID");
                System.out.println(resourceBundle.getString("employee.id"));
                System.out.println(employeeDetails.displayRequired(scanner.nextInt()));
                 break;
                case 3:logger.info("Displaying all details");
                    System.out.println(employeeDetails.displayAll());
                    break;
                case 4:logger.info("Display required details based on Temporary pincode");
                    System.out.println("Enter Temporary Pincode");
                    System.out.println(employeeDetails.displayBasedOnPinCode(scanner.nextInt()));
                    break;
            }
        }

    }
}
