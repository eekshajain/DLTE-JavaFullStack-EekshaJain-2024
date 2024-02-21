package basics.service;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;
        Long cardNumber=0L;
        Integer amount,billAmount;
        String billName,billType;
        Integer pin;
       GPay gPay=new GPay(542514689L,56000,"Anu",542514689L,1234,4567,"AnuP");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        System.out.println("Enter the choice\n1.Withdraw\n2.Pay Bill");
        choice=scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter amount to be withdrawn");
                amount=scanner.nextInt();
                gPay.withdraw(amount);
                break;
            case 2:
                System.out.println("Enter your name");
                billName=scanner1.nextLine();
                System.out.println("Enter bill amount");
                billAmount=scanner.nextInt();
                System.out.println("Enter bill type");
                billType=scanner.next();
                System.out.println("Enter UPI PIN");
                pin=scanner.nextInt();
                gPay.payBill(billName,billAmount,billType,pin);
        }

    }
}
