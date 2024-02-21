package basics.service;

import java.util.Scanner;

public class Banking {
    public static void main(String[] args) {
        String name="",emailAddress="",address="",IFSCCode="",password="",confirmPassword="";
        int customerID;
        Long mobile=0L,accNumber=0L;
        int bankType;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Welcome to MyBank");
        System.out.println("Enter\n1.To create NetBanking\n2.To create MobileBanking");
        bankType=scanner.nextInt();
        switch (bankType){
            case 1:
                System.out.println("Enter your name");
                name=scanner.nextLine();
                System.out.println("Enter Email address");
                emailAddress=scanner.next();
                System.out.println("Enter Customer ID");
                customerID=scanner.nextInt();
                System.out.println("Enter password");
                password=scanner.next();
                System.out.println("Re enter password");
                confirmPassword=scanner.next();
                if(password.equals(confirmPassword))
                System.out.println("Dear customer thank you for registering for Net Banking");
                else
                    System.out.println("Wrong password");
            case 2:
                System.out.println("Enter your name");
                name=scanner.nextLine();
                System.out.println("Enter address");
                address=scanner.nextLine();
                System.out.println("Enter Email address");
                emailAddress=scanner.next();
                System.out.println("Enter Mobile number");
                mobile=scanner.nextLong();
                System.out.println("Enter Account number");
                accNumber=scanner.nextLong();
                System.out.println("Enter bank IFSC code");
                IFSCCode=scanner.next();
                System.out.println("Enter password");
                password=scanner.next();
                System.out.println("Re enter password");
                confirmPassword=scanner.next();
                if(password.equals(confirmPassword))
                    System.out.println("Dear customer thank you for registering for Mobile Banking");
                else
                    System.out.println("Wrong password");
        }
    }
}
