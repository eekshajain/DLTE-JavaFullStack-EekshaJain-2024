package org.example;

import java.util.List;
import java.util.Scanner;

public class EnterDetails implements InputEmployeeDetails{

    Scanner scanner=new Scanner(System.in);
    Scanner scanner1=new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    Scanner scanner3=new Scanner(System.in);
    String firstName="",middleName="",lastName="";

   public EmployeeName collectEmployeeID(){
       EmployeeName employeeID=new EmployeeName();
       System.out.println("Enter Employee ID");
       employeeID.setEmployeeID(scanner1.nextInt());
       return employeeID;
   }
   public EmployeeName collectNames(){
       EmployeeName name=new EmployeeName();
       System.out.println("Enter Full Name");
       String nameExpression="^[a-zA-Z .']+$";
       System.out.println("Enter your First Name");
       //firstName=scanner.next();
       name.setFirstName(scanner.next());
//       Pattern patternFirstName=Pattern.compile(nameExpression);
//       Matcher matcherFName=patternFirstName.matcher(firstName);
       System.out.println("Enter your middle name");
      // middleName=scanner.next();
       name.setMiddleName(scanner.next());
//       Pattern patternMiddleName=Pattern.compile(nameExpression);
//       Matcher matcherMName=patternMiddleName.matcher(middleName);
       System.out.println("Enter your last name");
         name.setLastName(scanner.next());
//       Pattern patternLastName=Pattern.compile(nameExpression);
//       Matcher matcher=patternLastName.matcher(lastName);
     //return new EmployeeName(firstName,middleName,lastName);
   return name;
   }
 public EmployeeName collectBasic(){
     EmployeeName name=new EmployeeName();
     System.out.println("Enter your Employee ID");
     name.setEmployeeID(scanner1.nextInt());
     System.out.println("Enter your First Name");
     name.setFirstName(scanner.next());
     System.out.println("Enter your middle name");
     name.setMiddleName(scanner.next());
     System.out.println("Enter your last name");
     name.setLastName(scanner.next());
     return name;
 }
   public EmployeeContactDetails collectContactDetails(){
     EmployeeContactDetails  employeeContactDetails=new EmployeeContactDetails();
       System.out.println("Enter your contact details");
        System.out.println("Enter your phone number");
        employeeContactDetails.setPhoneNumber(scanner1.nextLong());
       System.out.println("Enter your official mail ID");
       employeeContactDetails.setEmailID(scanner.next());
       return employeeContactDetails;
   }

   public EmployeeAddress collectTemporaryAddress(){
       EmployeeAddress employeeAddress=new EmployeeAddress();
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
       return employeeAddress;
   }

   public EmployeeAddress collectPermanentAddress(){
      EmployeeAddress employeeAddress =new EmployeeAddress();
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
       return employeeAddress;
   }
   public EmployeeAddress collectAddress(){
       EmployeeAddress employeeAddress=new EmployeeAddress();
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
       return employeeAddress;
   }

}
