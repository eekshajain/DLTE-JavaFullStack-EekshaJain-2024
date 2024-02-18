package basics.service;

import java.util.Scanner;

//CLI for car loan
/*
Personal details: Name,aadhar,pan,address,mobile,email
Income: salaried,self-employed:ITR
 */
public class Interaction {
    public static void main(String[] args) {
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="";
        Long mobileNumber=0L,aadhaar=0L;
        System.out.println("----------Welcome to MyBank-----------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fill your Name");
        borrowerName=scanner.nextLine();
        System.out.println("Fill your Aadhaar");
        aadhaar=scanner.nextLong();
        System.out.println("Fill your Pan details");
        borrowerPan=scanner.next();
        System.out.println("Fill your NameLet us know our Income Type");
        borrowerIncomeType=scanner.next();
        System.out.println("Mention Mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter your Email ID");
        borrowerEmail=scanner.next();
        System.out.println("Dear"+borrowerName+" Thank you for applying for loan!Further details will be sent to "+borrowerEmail+" or will be sent SMS to "+mobileNumber);
    }
}
