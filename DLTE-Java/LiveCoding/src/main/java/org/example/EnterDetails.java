package org.example;

import java.util.Scanner;

public class EnterDetails {

//    String firstName="";
//    String middleName="";
//    String lastName="";
//    int employeeID;
//    String officeMailID="";
    Scanner scanner=new Scanner(System.in);
    Scanner scanner1=new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    String fname="",mname="",lname="";
   // EmployeeName[] employeeName=new EmployeeName[20];
   public void collectDetails(EmployeeName employeeName[],EmployeeAddress employeeAddress[]){
     //  EmployeeName[] employeeName=new EmployeeName[size];

       for(int index=0;index<employeeName.length;index++) {
           System.out.println("Enter the details of "+(index+1)+" employee");
           System.out.println("Enter your Full Name");
           System.out.println("Enter your First Name");
           fname=scanner.next();
           System.out.println("Enter your middle name");
           mname=scanner.next();
           System.out.println("Enter your last name");
            lname=scanner.next();
           employeeName[index]=new EmployeeName(fname,mname,lname);
          employeeAddress[index]=new EmployeeAddress();
           System.out.println("Enter your Temporary Address:");
           System.out.println("Enter your House name");
           employeeAddress[index].setTemporaryHouseName(scanner1.nextLine());
           System.out.println("Enter your Street name");
           employeeAddress[index].setTemporaryHouseStreet(scanner1.nextLine());
           System.out.println("Enter your City name");
           employeeAddress[index].setTemporaryCityName(scanner1.nextLine());
           System.out.println("Enter your State name");
           employeeAddress[index].setTemporaryStateName(scanner1.nextLine());
           System.out.println("Enter your city pincode");
           employeeAddress[index].setTemporaryPinCode(scanner1.nextInt());
           System.out.println("Enter your Permanent Address:");
           System.out.println("Enter your House name");
           employeeAddress[index].setPermanentHouseName(scanner2.nextLine());
           System.out.println("Enter your Street name");
           employeeAddress[index].setPermanentHouseStreet(scanner2.nextLine());
           System.out.println("Enter your City name");
           employeeAddress[index].setPermanentCityName(scanner2.nextLine());
           System.out.println("Enter your State name");
           employeeAddress[index].setPermanentStateName(scanner2.nextLine());
           System.out.println("Enter your city pincode");
           employeeAddress[index].setPermanentPinCode(scanner2.nextInt());
       }

       }

   public EmployeeName collectNames(){
       System.out.println("Enter your First Name");
       fname=scanner.next();
       System.out.println("Enter your middle name");
       mname=scanner.next();
       System.out.println("Enter your last name");
       lname=scanner.next();
     return new EmployeeName(fname,mname,lname);
   }

   public void collectTemporaryAddress(EmployeeAddress employeeAddress){
       employeeAddress=new EmployeeAddress();
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
   }

   public void collectPermanentAddress(EmployeeAddress employeeAddress){
       employeeAddress =new EmployeeAddress();
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
   }
    public void displayName(EmployeeName employeeName[]){
        System.out.println("display");
       for(EmployeeName each:employeeName){
           if(each!=null)
               System.out.println(each.toString());
       }

    }
    public void displayAddress(EmployeeAddress employeeAddress[]){
       for(EmployeeAddress each:employeeAddress){
           if(each!=null)
               System.out.println(each.toString());
       }
    }
    public void displayCompleteDetails(EmployeeName employeeName[],EmployeeAddress employeeAddress[]){
       for(int index=0;index<employeeName.length;index++){
           System.out.println("Name:"+employeeName[index].getFirstName()+" "+employeeName[index].getMiddleName()+" "+employeeName[index].getLastName());
           System.out.println("Temporary address:");
       }
    }

}
