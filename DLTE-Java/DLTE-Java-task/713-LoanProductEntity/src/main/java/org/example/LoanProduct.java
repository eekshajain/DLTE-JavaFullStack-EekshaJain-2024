package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class LoanProduct implements MyBank{
    Scanner scanner =new Scanner(System.in);
    Scanner scanner1=new Scanner(System.in);
    @Override
    public void addLoan() throws IOException, ClassNotFoundException {
        Loan loan=new Loan();
        System.out.println("Enter Loan Number");
        loan.setLoanNumber(scanner.nextLong());
        System.out.println("Enter loan amount");
        loan.setLoanAmount(scanner.nextInt());
        System.out.println("Enter loan Date");
        loan.setLoanDate(scanner.next());
        System.out.println("Enter loan status");
        loan.setLoanStatus(scanner.next());
        System.out.println("Enter borrower name");
         loan.setBorrowerName(scanner1.nextLine());
        System.out.println("enter borrower contact number");
        loan.setBorrowerContact(scanner.nextLong());
        loans.add(loan);// take input and store in arraylist
        File file=new File("OutputFile.txt");
        if(file.exists()){
            FileInputStream fileInputStream=new FileInputStream(file);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            loans.addAll((List<Loan>) objectInputStream.readObject());
        }
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(loans);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    @Override
    public List<Loan> availableLoan() throws IOException, ClassNotFoundException {
        List<Loan> tempLoan = null;
        List<Loan> finalLoan=new ArrayList<>();
        File file=new File("OutputFile.txt");
        if(file.exists()){
            FileInputStream fileInputStream=new FileInputStream(file);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            tempLoan= (List<Loan>) objectInputStream.readObject();//re read file
            //add new data into existing file
            for(Loan each:tempLoan){
                if(each.getLoanStatus().equalsIgnoreCase("open")){
                    finalLoan.add(each);
                }
            }
        }
        //System.out.println(finalLoan);
        return finalLoan;
    }


    @Override
    public List<Loan> closedLoan() throws IOException, ClassNotFoundException {
        List<Loan> tempLoan = null;
        List<Loan> finalLoan=new ArrayList<>();
        File file=new File("OutputFile.txt");
        if(file.exists()){
            FileInputStream fileInputStream=new FileInputStream(file);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            tempLoan= (List<Loan>) objectInputStream.readObject();//re read file
            //add new data into existing file
            for(Loan each:tempLoan){
                if(each.getLoanStatus().equalsIgnoreCase("closed")){
                    finalLoan.add(each);
                }
            }
        }
         return  finalLoan;
    }

    public void displayLoans(List<Loan> loans1){
        if(loans1.size()>0){
            for(Loan each:loans1){
                System.out.println(each);
            }
        }
        else{
            System.out.println("No loans");
        }
    }
}
