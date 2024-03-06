package org.consoleEnv;

import org.files.ReadAndDisplay;
import org.middleware.Employee;
import org.middleware.EmployeeAddress;
import org.middleware.EmployeeBasicDetails;
import org.middleware.InputEmployeeDetails;

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
        while (true){
            System.out.println(resourceBundle.getString("menu.display"));
            switch (scanner.nextInt()){
                case 1:
                    do{
                        System.out.println("Enter Employee ID");
                        employeeBasicDetails.setEmployeeID(scanner.nextInt());
                        System.out.println("Enter Your Full Name");
                        System.out.println("Enter your First Name");
                        employeeBasicDetails.setFirstName(scanner.next());
                        System.out.println("Enter your middle name");
                        employeeBasicDetails.setMiddleName(scanner.next());
                        System.out.println("Enter your last name");
                        employeeBasicDetails.setLastName(scanner.next());
                        System.out.println("Enter your contact details");
                        System.out.println("Enter your phone number");
                        employeeBasicDetails.setPhoneNumber(scanner1.nextLong());
                        System.out.println("Enter your official mail ID");
                        employeeBasicDetails.setEmailID(scanner.next());
                        System.out.println("Enter your Temporary Address:");
                        System.out.println("Enter your House name");
                        employeeAddress.setTemporaryHouseName(scanner2.nextLine());
                        System.out.println("Enter your Street name");
                        employeeAddress.setTemporaryHouseStreet(scanner2.nextLine());
                        System.out.println("Enter your City name");
                        employeeAddress.setTemporaryCityName(scanner2.nextLine());
                        System.out.println("Enter your State name");
                        employeeAddress.setTemporaryStateName(scanner2.nextLine());
                        System.out.println("Enter your city pincode");
                        employeeAddress.setTemporaryPinCode(scanner1.nextInt());
                        System.out.println("Enter your Permanent Address:");
                        System.out.println("Enter your House name");
                        employeeAddress.setPermanentHouseName(scanner3.nextLine());
                        System.out.println("Enter your Street name");
                        employeeAddress.setPermanentHouseStreet(scanner3.nextLine());
                        System.out.println("Enter your City name");
                        employeeAddress.setPermanentCityName(scanner3.nextLine());
                        System.out.println("Enter your State name");
                        employeeAddress.setPermanentStateName(scanner3.nextLine());
                        System.out.println("Enter your city pincode");
                        employeeAddress.setPermanentPinCode(scanner1.nextInt());
                        employeeArrayList.add(new Employee(employeeBasicDetails,employeeAddress));
                        employeeDetails.saveAll(employeeArrayList);
                        System.out.println("Do you want to add more?");
                    }while (scanner.next().equalsIgnoreCase("yes"));
                    break;
            case 2:  System.out.println("Enter employee id");
                System.out.println(employeeDetails.displayRequired(scanner.nextInt()));
                 break;
                case 3:
                    System.out.println(employeeDetails.displayAll());
                    break;
                case 4:
                    System.out.println("Enter Temporary Pincode");
                    System.out.println(employeeDetails.displayBasedOnPinCode(scanner.nextInt()));
                    break;
            }
        }

    }
}
