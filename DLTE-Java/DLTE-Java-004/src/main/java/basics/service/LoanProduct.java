package basics.service;

import java.util.Date;
import java.util.Scanner;

public class LoanProduct implements MyBank {
Scanner scanner =new Scanner(System.in);
Scanner scanner1=new Scanner(System.in);
   Loan[] loans = new Loan[10];
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
            loans[index]=new Loan(loanNumber,loanAmount,loanStatus,loanDate,borrowerName,borrowerContact);
        }
    }

    @Override
    public void availableLoan() {
        for(Loan each:loans){
            if(each.getLoanStatus().equalsIgnoreCase("open"))
                System.out.println(each.toString());
        }
    }

    @Override
    public void closedLoan() {
        for(Loan each:loans){
            if(each.getLoanStatus().equalsIgnoreCase("closed"))
                System.out.println(each.toString());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        LoanProduct loans1 =new LoanProduct();
        choice=scanner.nextInt();
        while(true){
        System.out.println("Enter choice\n1.Add new loan\n2.Check available loan\n3.Check closed loan");
        switch (choice) {
            case 1:
                System.out.println("Enter number of loans");
                int size = scanner.nextInt();
                loans1.addLoan(size);
                System.out.println("Loan added successfully!");
                break;
            case 2:
                loans1.availableLoan();
            case 3:
                loans1.closedLoan();
            default:
                scanner.close();
                System.exit(0);
        }
        }
    }

}
