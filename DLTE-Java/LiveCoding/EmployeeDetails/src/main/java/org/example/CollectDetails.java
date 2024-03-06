package org.example;

import java.util.*;

public class CollectDetails {
    public static void main(String[] args) {
     Scanner scanner=new Scanner(System.in);
    EnterDetails employeeDetailCollection=new EnterDetails();
    DisplayDetails displayDetails=new DisplayDetails();
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        List<EmployeeName> employeeNames=new ArrayList<>();
        List<EmployeeAddress> temporaryEmployeeAddress=new ArrayList<>();
       List<EmployeeAddress> permanentEmployeeAddress=new ArrayList<>();
        List<EmployeeContactDetails> employeeContactDetails=new ArrayList<>();
        List<EmployeeAddress> addresses=new ArrayList<>();
        List<Object> all=new ArrayList<>();

        while (true) {
            System.out.println(resourceBundle.getString("menu.ask"));
            System.out.println(resourceBundle.getString("menu.display"));
            switch (scanner.nextInt()) {
                case 1:
                    do {
                        try {
                            employeeNames.add(employeeDetailCollection.collectBasic());
                            employeeContactDetails.add(employeeDetailCollection.collectContactDetails());
                            temporaryEmployeeAddress.add(employeeDetailCollection.collectTemporaryAddress());
                          //  permanentEmployeeAddress.add(employeeDetailCollection.collectPermanentAddress());
                         //   String data=employeeNames+" "+employeeContactDetails+" "+temporaryEmployeeAddress+" "+permanentEmployeeAddress;
//                            String data=employeeNames+" "+employeeContactDetails+" "+temporaryEmployeeAddress;
//                            // System.out.println(data);
//                            all.add(data);
//                            System.out.println(all);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input");
                        }
                        System.out.println(resourceBundle.getString("need.more.details"));
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    break;
                case 2:
                    System.out.println("Enter the employee ID");
                    displayDetails.display(employeeNames, employeeContactDetails, temporaryEmployeeAddress, scanner.nextInt());
                   // displayDetails.displayAll(employeeNames, employeeContactDetails, temporaryEmployeeAddress, permanentEmployeeAddress);
                    break;
                default:
                    System.exit(0);
            }
        }
//        System.out.println("What do you want to display");
//        while (true) {
//            System.out.println("1.Display Names of Employee\n2.Display names and contact details\n3.Display names and address details\n4.Display all details\n5.Exit");
//            int choice=scanner.nextInt();
//                switch (choice) {
//                case 1:
//                    employeeDetailCollection.displayName(employeeNames);
//                    break;
//                case 2:
//                   employeeDetailCollection.displayName(employeeNames);
//                    employeeDetailCollection.displayContactDetails(employeeContactDetails);
//                    break;
//                case 3:
//                    employeeDetailCollection.displayName(employeeNames);
//                    employeeDetailCollection.displayAddress(temporaryEmployeeAddress, permanentEmployeeAddress);
//                    break;
//                case 4:
//                    employeeDetailCollection.displayName(employeeNames);
//                    employeeDetailCollection.displayContactDetails(employeeContactDetails);
//                    employeeDetailCollection.displayAddress(temporaryEmployeeAddress, permanentEmployeeAddress);
//                    break;
//                default:
//                    scanner.close();
//                    System.exit(0);
//
//            }
//        }
    }
}
