package org.example;

import java.util.List;
import java.util.Scanner;

public class EnterDetails {

    Scanner scanner=new Scanner(System.in);
    Scanner scanner1=new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    Scanner scanner3=new Scanner(System.in);
    String firstName="",middleName="",lastName="";

   public EmployeeName getEmployeeID(){
       EmployeeName employeeID=new EmployeeName();
       employeeID.setEmployeeID(scanner3.nextInt());
       return employeeID;
   }
   public EmployeeName collectNames(){
       String nameExpression="^[a-zA-Z .']+$";
       System.out.println("Enter your First Name");
       firstName=scanner.next();
//       Pattern patternFirstName=Pattern.compile(nameExpression);
//       Matcher matcherFName=patternFirstName.matcher(firstName);
       System.out.println("Enter your middle name");
       middleName=scanner.next();
//       Pattern patternMiddleName=Pattern.compile(nameExpression);
//       Matcher matcherMName=patternMiddleName.matcher(middleName);
       System.out.println("Enter your last name");
       lastName=scanner.next();
//       Pattern patternLastName=Pattern.compile(nameExpression);
//       Matcher matcher=patternLastName.matcher(lastName);
     return new EmployeeName(firstName,middleName,lastName);
   }

   public EmployeeContactDetails collectContactDetails(){
     EmployeeContactDetails  employeeContactDetails=new EmployeeContactDetails();
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

    public void displayName(List<EmployeeName> employeeName){
        for(int index=0;index<employeeName.size();index++){
            EmployeeName employee=employeeName.get(index);
            if(employee.getFirstName()!=null) {
                if(employee.getMiddleName()!=null)
                    if(employee.getLastName()!=null)
                System.out.println("Name:" + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
            }
        }

    }
    public void displayContactDetails(List<EmployeeContactDetails> employeeContactDetails){
//       for (int index=0;index<employeeContactDetails.length;index++)
//           System.out.println("Phone Number:"+employeeContactDetails[index].getPhoneNumber()+"\n"+"Email Address:"+employeeContactDetails[index].getEmailID());
       for(EmployeeContactDetails each:employeeContactDetails){
           System.out.println("Phone Number:"+each.getPhoneNumber()+"\n"+"Email Address:"+each.getEmailID());
       }

    }
    public void displayAddress(List<EmployeeAddress> tempEmployeeAddress, List<EmployeeAddress> permEmployeeAddress){
        for(int index=0;index<tempEmployeeAddress.size();index++){
            EmployeeAddress tempAddress=tempEmployeeAddress.get(index);
            EmployeeAddress permAddress=permEmployeeAddress.get(index);
            System.out.println("Temporary address:"+tempAddress.getTemporaryHouseName()+","+tempAddress.getTemporaryHouseStreet()+","+tempAddress.getTemporaryCityName()+","+tempAddress.getTemporaryStateName()+"-"+tempAddress.getTemporaryPinCode());
            System.out.println("Permanent address:"+permAddress.getPermanentHouseName()+","+permAddress.getPermanentHouseStreet()+","+permAddress.getPermanentCityName()+","+permAddress.getPermanentStateName()+"-"+permAddress.getPermanentPinCode());
        }

    }


}
