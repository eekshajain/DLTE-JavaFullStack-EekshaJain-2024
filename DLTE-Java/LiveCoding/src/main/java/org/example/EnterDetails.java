package org.example;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnterDetails {

//    String firstName="";
//    String middleName="";
//    String lastName="";
//    int employeeID;
//    String officeMailID="";
    Scanner scanner=new Scanner(System.in);
    Scanner scanner1=new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    Scanner scanner3=new Scanner(System.in);
    String firstName="",middleName="",lastName="";
   // EmployeeName[] employeeName=new EmployeeName[20];
//   public void collectDetails(EmployeeName employeeName[],EmployeeAddress employeeAddress[]){
//     //  EmployeeName[] employeeName=new EmployeeName[size];
//
//       for(int index=0;index<employeeName.length;index++) {
//           System.out.println("Enter the details of "+(index+1)+" employee");
//           System.out.println("Enter your Full Name");
//           System.out.println("Enter your First Name");
//           firstName=scanner.next();
//           System.out.println("Enter your middle name");
//           middleName=scanner.next();
//           System.out.println("Enter your last name");
//            lastName=scanner.next();
//           employeeName[index]=new EmployeeName(firstName,middleName,lastName);
//          employeeAddress[index]=new EmployeeAddress();
//           System.out.println("Enter your Temporary Address:");
//           System.out.println("Enter your House name");
//           employeeAddress[index].setTemporaryHouseName(scanner1.nextLine());
//           System.out.println("Enter your Street name");
//           employeeAddress[index].setTemporaryHouseStreet(scanner1.nextLine());
//           System.out.println("Enter your City name");
//           employeeAddress[index].setTemporaryCityName(scanner1.nextLine());
//           System.out.println("Enter your State name");
//           employeeAddress[index].setTemporaryStateName(scanner1.nextLine());
//           System.out.println("Enter your city pincode");
//           employeeAddress[index].setTemporaryPinCode(scanner1.nextInt());
//           System.out.println("Enter your Permanent Address:");
//           System.out.println("Enter your House name");
//           employeeAddress[index].setPermanentHouseName(scanner2.nextLine());
//           System.out.println("Enter your Street name");
//           employeeAddress[index].setPermanentHouseStreet(scanner2.nextLine());
//           System.out.println("Enter your City name");
//           employeeAddress[index].setPermanentCityName(scanner2.nextLine());
//           System.out.println("Enter your State name");
//           employeeAddress[index].setPermanentStateName(scanner2.nextLine());
//           System.out.println("Enter your city pincode");
//           employeeAddress[index].setPermanentPinCode(scanner2.nextInt());
//       }
//
//       }
   public EmployeeName getEmployeeID(){
       EmployeeName employeeID=new EmployeeName();
       employeeID.setEmployeeID(scanner3.nextInt());
       return employeeID;
   }
   public EmployeeName collectNames(){
       String nameExpression="^[a-zA-Z .']+$";
       System.out.println("Enter your First Name");
       firstName=scanner.next();
       Pattern patternFirstName=Pattern.compile(nameExpression);
       Matcher matcherFName=patternFirstName.matcher(firstName);
       System.out.println("Enter your middle name");
       middleName=scanner.next();
       Pattern patternMiddleName=Pattern.compile(nameExpression);
       Matcher matcherMName=patternMiddleName.matcher(firstName);
       System.out.println("Enter your last name");
       lastName=scanner.next();
       Pattern patternLastName=Pattern.compile(nameExpression);
       Matcher matcher=patternLastName.matcher(firstName);
     return new EmployeeName(firstName,middleName,lastName);
   }

   public EmployeeContactDetails collectContactDetails(){
       EmployeeContactDetails employeeContactDetails=new EmployeeContactDetails();
        System.out.println("Enter your phone number");
        employeeContactDetails.setPhoneNumber(scanner3.nextLong());
       System.out.println("Enter your official mail ID");
       employeeContactDetails.setEmailID(scanner3.next());
       return employeeContactDetails;
   }

   public EmployeeAddress collectTemporaryAddress(){
       EmployeeAddress employeeAddress=new EmployeeAddress();
       System.out.println("Enter your House name");
       employeeAddress.setTemporaryHouseName(scanner1.nextLine());
       System.out.println("Enter your Street name");
       employeeAddress.setTemporaryHouseStreet(scanner1.nextLine());
       System.out.println("Enter your City name");
       employeeAddress.setTemporaryCityName(scanner1.nextLine());
       System.out.println("Enter your State name");
       employeeAddress.setTemporaryStateName(scanner1.nextLine());
       System.out.println("Enter your city pincode");
       employeeAddress.setTemporaryPinCode(scanner1.nextInt());
       return employeeAddress;
   }

   public EmployeeAddress collectPermanentAddress(){
      EmployeeAddress employeeAddress =new EmployeeAddress();
       System.out.println("Enter your House name");
       employeeAddress.setPermanentHouseName(scanner2.nextLine());
       System.out.println("Enter your Street name");
       employeeAddress.setPermanentHouseStreet(scanner2.nextLine());
       System.out.println("Enter your City name");
       employeeAddress.setPermanentCityName(scanner2.nextLine());
       System.out.println("Enter your State name");
       employeeAddress.setPermanentStateName(scanner2.nextLine());
       System.out.println("Enter your city pincode");
       employeeAddress.setPermanentPinCode(scanner2.nextInt());
       return employeeAddress;
   }

    public void displayName(EmployeeName employeeName[]){
        for(int index=0;index<employeeName.length;index++){
            System.out.println("Name:"+employeeName[index].getFirstName()+" "+employeeName[index].getMiddleName()+" "+employeeName[index].getLastName());
        }
    }
    public void displayContactDetails(EmployeeContactDetails employeeContactDetails[]){
       for (int index=0;index<employeeContactDetails.length;index++)
           System.out.println("Phone Number:"+employeeContactDetails[index].getPhoneNumber()+"\n"+"Email Address:"+employeeContactDetails[index].getEmailID());
    }
    public void displayAddress(EmployeeAddress tempEmployeeAddress[],EmployeeAddress permEmployeeAddress[]){
        for(int index=0;index<tempEmployeeAddress.length;index++){
            System.out.println("Temporary address:"+tempEmployeeAddress[index].getTemporaryHouseName()+","+tempEmployeeAddress[index].getTemporaryHouseStreet()+","+tempEmployeeAddress[index].getTemporaryCityName()+","+tempEmployeeAddress[index].getTemporaryStateName()+"-"+tempEmployeeAddress[index].getTemporaryPinCode());
            System.out.println("Permanent address:"+permEmployeeAddress[index].getPermanentHouseName()+","+permEmployeeAddress[index].getPermanentHouseStreet()+","+permEmployeeAddress[index].getPermanentCityName()+","+permEmployeeAddress[index].getPermanentStateName()+"-"+permEmployeeAddress[index].getPermanentPinCode());
        }
    }


}
