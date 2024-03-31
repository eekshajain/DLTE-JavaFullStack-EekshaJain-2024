package org.example;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        LoanProduct loanProduct=new LoanProduct();
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.println("What do you want to do?\n1.Add loans\n2.Display Available loans\n3.Display Closed Loans\n4.Exit");
            switch (scanner.nextInt()){
                case 1: loanProduct.addLoan();
                        break;
                case 2: loanProduct.displayLoans(loanProduct.availableLoan());
                         break;
                case 3: loanProduct.displayLoans(loanProduct.closedLoan());
                          break;
                case 4:scanner.close();
                      System.out.println("Thank you for visiting!");
                      System.exit(0);
            }
        }
    }
}
