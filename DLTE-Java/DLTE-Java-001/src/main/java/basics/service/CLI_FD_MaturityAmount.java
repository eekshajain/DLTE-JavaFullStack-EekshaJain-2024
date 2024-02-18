package basics.service;

import java.util.Scanner;

public class CLI_FD_MaturityAmount {
    public static void main(String[] args) {
        double principalAmount, rateOfInterest, maturityFD=0.0,yearlyInterest;
        int tenureOfFD;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-----------Online FD Maturity Amount Calculator-----------");
        System.out.println("Enter Principal Amount deposited");
        principalAmount=scanner.nextDouble();
        System.out.println("Enter Rate of Interest");
        rateOfInterest=scanner.nextDouble();
        yearlyInterest=rateOfInterest/100;
        System.out.println("Enter Duration of FD");
        tenureOfFD=scanner.nextInt();
        maturityFD=principalAmount*Math.pow((1+yearlyInterest),tenureOfFD);
        System.out.println("Your Maturity Fixed Deposit Amount is "+String.format("%.2f",maturityFD)+" of Principal Amount "+principalAmount+" with the rate of interest "+rateOfInterest+" for the tenure of "+tenureOfFD+" years");

    }
}
