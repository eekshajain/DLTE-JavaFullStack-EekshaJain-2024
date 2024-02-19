package basics.service;

import java.util.Scanner;

public class SIPCalculator {
    public static void main(String[] args) {
        double amountInvested=0,maturityAmount,rateOfInterest=0,compoundInterest=0;
        int numberOfPayments=0;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-----Welcome to MyBank-----");
        System.out.println("Enter Principal Amount");
        amountInvested=scanner.nextDouble();
        System.out.println("Enter Number of months amount paid");
        numberOfPayments=scanner.nextInt();
        System.out.println("Enter Rate Of interest");
        rateOfInterest=scanner.nextDouble();
        compoundInterest=rateOfInterest/(12*100);
        double periodicROI=Math.pow(1+compoundInterest,numberOfPayments);
        maturityAmount=amountInvested*((periodicROI-1)/compoundInterest)*(1+compoundInterest);
        System.out.println("Maturity amount after Systematic Investment Plan(SIP) "+maturityAmount);
    }
}
