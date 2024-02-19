package basics.service;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarLoanDataValidation {
    public static void main(String[] args) {
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncomeType="",aadhaar="",mobileNumber="";
        System.out.println("----------Welcome to MyBank-----------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Fill your Name");
        borrowerName=scanner.nextLine();
        String nameExpression="^[a-zA-Z .']+$";
        Pattern patternName=Pattern.compile(nameExpression);
        Matcher matcherName =patternName.matcher(borrowerName);
        if(matcherName.matches())
            System.out.println("Valid name");
        else
            System.out.println("Invalid name");
        System.out.println("Fill your Aadhaar");
        aadhaar=scanner.next();
        String aadharexpression="[1-9]{1}[0-9]{11}";
        Pattern patternAdhar=Pattern.compile(aadharexpression);
        Matcher matcherAdhar=patternAdhar.matcher(aadhaar);
        if(matcherAdhar.matches())
            System.out.println("Valid adhaar number");
        else
            System.out.println("Invalid adhaar number");
        System.out.println("Fill your Pan details");
        borrowerPan=scanner.next();
        String panCardExpression="[A-Z]{5}[0-9]{4}[A-Z]";
        Pattern patternPAN=Pattern.compile(panCardExpression);
        Matcher matcherPAN =patternPAN.matcher(borrowerPan);
        if(matcherPAN.matches())
            System.out.println("Valid name");
        else
            System.out.println("Invalid name");
//        System.out.println("Fill your NameLet us know our Income Type");
//        borrowerIncomeType=scanner.next();
        System.out.println("Mention Mobile number");
        mobileNumber=scanner.next();
        String mobileExpression="[6-9]{1}[0-9]{9}";
        Pattern patternPhone=Pattern.compile(mobileExpression);
        Matcher matcherPhone = patternPhone.matcher(mobileNumber);
        if(matcherPhone.matches())
            System.out.println("Valid Phone number");
        else
            System.out.println("Invalid Phone number");
        System.out.println("Enter your Email ID");
        borrowerEmail=scanner.next();
        int _atPos=borrowerEmail.indexOf('@');
        int _dotPos=borrowerEmail.indexOf('.');
        if(_dotPos-_atPos>=3)
            System.out.println("Valid Email");
        else
            System.out.println("Invalid Email");
//        System.out.println("Dear"+borrowerName+" Thank you for applying for loan!Further details will be sent to "+borrowerEmail+" or will be sent SMS to "+mobileNumber);
    }
}
