package basics.service;

import java.util.Date;
import java.util.Scanner;

public class LoanProduct implements MyBank {
    int sizeLoan=0;
Scanner scanner =new Scanner(System.in);
Scanner scanner1=new Scanner(System.in);
   static Loan[] loans1 = new Loan[10];
    @Override
    public void addLoan(int size) {
        for(int index=0;index<size;index++) {
            System.out.println("Enter data of "+(index+1)+" users");
            System.out.println("Enter Loan Number");
            Long loanNumber = scanner.nextLong();
            System.out.println("Enter loan amount");
            Integer loanAmount = scanner.nextInt();
            System.out.println("Enter loan Date");
            String loanDate = scanner.next();
            System.out.println("Enter loan status");
            String loanStatus=scanner.next();
            System.out.println("Enter borrower name");
            String borrowerName = scanner1.nextLine();
            System.out.println("enter borrower contact number");
            Long borrowerContact = scanner.nextLong();
            loans1[index]=new Loan(loanNumber,loanAmount,loanStatus,loanDate,borrowerName,borrowerContact);
        }
    }

    @Override
    public void availableLoan() {
        for(Loan each:loans1){
            if(each.getLoanStatus().equalsIgnoreCase("open"))
                System.out.println(each.toString());
        }
    }

    @Override
    public void closedLoan() {
        for(Loan each:loans1){
            if(each.getLoanStatus().equalsIgnoreCase("closed"))
                System.out.println(each.toString());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        LoanProduct loans =new LoanProduct();
        choice=scanner.nextInt();
        System.out.println("Enter choice\n1.Add new loan\n2.Check available loan\n3.Check closed loan");
        switch (choice){
            case 1:
                System.out.println("Enter number of loans");
                int size=scanner.nextInt();
                loans.addLoan(size);
                for(Loan each:loans1){
                    System.out.println(each.toString());
                }
                break;
            case 2:loans.availableLoan();
            case 3:loans.closedLoan();
        }
    }

}
