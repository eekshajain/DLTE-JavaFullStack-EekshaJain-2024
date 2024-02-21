package basics.service;

import java.util.Scanner;

public class PersonalLoan {
    public static void main(String[] args) {
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="",borrowerVoterID="";
        Long mobileNumber=0L,aadhaar=0L;
        Integer borrowerSalary,loanAmount=0;
        System.out.println("----------Welcome to MyBank-----------");
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 =new Scanner(System.in);
        System.out.println("Fill your Name");
        borrowerName=scanner.nextLine();
        System.out.println("Fill your Aadhaar");
        aadhaar=scanner.nextLong();
        System.out.println("Fill your Pan details");
        borrowerPan=scanner.next();
        System.out.println("Let us know our Income Type");
        borrowerIncomeType=scanner.next();
        System.out.println("Enter your Monthly Salary");
        borrowerSalary=scanner.nextInt();
        if(borrowerSalary>=8000 && borrowerSalary<15000){
            loanAmount=250000;
        }else if(borrowerSalary>=15000 && borrowerSalary<25000){
            loanAmount=400000;
        }else if(borrowerSalary>=25000 && borrowerSalary<40000){
            loanAmount=650000;
        }else if(borrowerSalary>=40000 && borrowerSalary<60000){
            loanAmount=900000;
        }else if(borrowerSalary>=60000 && borrowerSalary<70000){
            loanAmount=1400000;
        }
        System.out.println("Enter your Voter ID");
        borrowerVoterID=scanner.next();
        System.out.println("Mention Mobile number");
        mobileNumber=scanner.nextLong();
        //System.out.println();
        System.out.println("Enter your address");
        borrowerAddress=scanner1.nextLine();
        System.out.println("Enter your Email ID");
        borrowerEmail=scanner1.next();

        System.out.println("Dear "+borrowerName+" Thank you for applying for Personal loan!"+"You are eligible to apply for loan upto "+loanAmount+" lakhs."+"Further details will be sent to "+borrowerEmail+" or will be sent SMS to "+mobileNumber);
    }
}
