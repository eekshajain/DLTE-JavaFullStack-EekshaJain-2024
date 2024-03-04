package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CollectDetails {
    public static void main(String[] args) {
     Scanner scanner=new Scanner(System.in);
     int size;
    EnterDetails employeeDetailCollection=new EnterDetails();
    System.out.println("Enter number of employee details");
        size=scanner.nextInt();
        EmployeeName[] employeeNames=new EmployeeName[size];
        EmployeeAddress[] temporaryEmployeeAddress=new EmployeeAddress[size];
        EmployeeAddress[] permanentEmployeeAddress=new EmployeeAddress[size];
        EmployeeContactDetails[] employeeContactDetails=new EmployeeContactDetails[size];
        for(int index=0;index<size;index++)
        {
            try {
                System.out.println("Enter the details of " + (index + 1) + " employee");
                System.out.println("Enter your Employee ID");
                employeeNames[index] = employeeDetailCollection.getEmployeeID();
                System.out.println("Enter your Full Name:");
                employeeNames[index] = employeeDetailCollection.collectNames();
                System.out.println("Enter your contact details");
                employeeContactDetails[index] = employeeDetailCollection.collectContactDetails();
                System.out.println("Enter your Temporary Address:");
                temporaryEmployeeAddress[index] = employeeDetailCollection.collectTemporaryAddress();
                System.out.println("Enter your Permanent Address:");
                permanentEmployeeAddress[index] = employeeDetailCollection.collectPermanentAddress();
            }catch (InputMismatchException e){
                System.out.println("Invalid Input");
            }
        }
        System.out.println("What do you want to display");
        System.out.println("1.Display Names of Employee\n2.Display names and contact details\n3.Display names and address details\n4.Display all details\n5.Exit");
        int choice=scanner.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    employeeDetailCollection.displayName(employeeNames);
                    break;
                case 2:
                    employeeDetailCollection.displayName(employeeNames);
                    employeeDetailCollection.displayContactDetails(employeeContactDetails);
                    break;
                case 3:
                    employeeDetailCollection.displayName(employeeNames);
                    employeeDetailCollection.displayAddress(temporaryEmployeeAddress, permanentEmployeeAddress);
                    break;
                case 4:
                    employeeDetailCollection.displayName(employeeNames);
                    employeeDetailCollection.displayContactDetails(employeeContactDetails);
                    employeeDetailCollection.displayAddress(temporaryEmployeeAddress, permanentEmployeeAddress);
                    break;
                default:
                    scanner.close();
                    System.exit(0);

            }
        }

    }
}
