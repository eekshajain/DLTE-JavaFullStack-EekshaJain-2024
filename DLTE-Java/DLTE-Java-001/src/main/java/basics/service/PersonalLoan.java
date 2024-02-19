package basics.service;

import java.util.Scanner;

public class PersonalLoan {
    public static void main(String[] args) {
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="",borrowerVoterID="";
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
        System.out.println("Enter your Voter ID");
        borrowerVoterID=scanner.next();
        System.out.println("Mention Mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println();
        System.out.println("Enter your address");
        borrowerAddress=scanner.nextLine();
        System.out.println("Enter your Email ID");
        borrowerEmail=scanner.next();
        System.out.println("Dear"+borrowerName+" Thank you for applying for loan!Further details will be sent to "+borrowerEmail+" or will be sent SMS to "+mobileNumber);
    }
}
