package org.example;

import java.util.Scanner;

public class CollectDetails {
    public static void main(String[] args) {
     Scanner scanner=new Scanner(System.in);
     int size;
    EnterDetails employeeDetailCollection=new EnterDetails();
    System.out.println("Enter number of employee details");
        size=scanner.nextInt();
        EmployeeName[] employeeNames=new EmployeeName[size];
        EmployeeAddress[] employeeAddresses=new EmployeeAddress[size];
        for(int index=0;index<size;index++)
        {
          System.out.println("Enter the details of "+(index+1)+" employee");

         // EmployeeAddress[] employeeAddresses=new EmployeeAddress[size];
          System.out.println("Enter your Full Name:");
         employeeNames[index]=employeeDetailCollection.collectNames();
//          System.out.println("Enter your Temporary Address:");
//          employeeDetailCollection.collectTemporaryAddress(employeeAddresses[index]);
//          System.out.println("Enter your Permanent Address:");
//          employeeDetailCollection.collectPermanentAddress(employeeAddresses[index]);
        }
//      employeeDetailCollection.displayName(employeeNames);
  //employeeDetailCollection.collectDetails(employeeNames,employeeAddresses);
  employeeDetailCollection.displayName(employeeNames);
  employeeDetailCollection.displayAddress(employeeAddresses);

    }
}
