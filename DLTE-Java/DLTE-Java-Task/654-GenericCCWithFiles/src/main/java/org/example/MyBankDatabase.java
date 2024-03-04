package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MyBankDatabase implements Activity{

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase myBankDatabase=new MyBankDatabase();
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        List<CreditCard> creditCardList=new ArrayList<>();
        do{
            CreditCard creditCard=new CreditCard();
            System.out.println("Enter Credit Card Holder");
            creditCard.setCreditCardHolder(scanner1.nextLine());
            System.out.println("Enter CreditCardCVV");
            creditCard.setCreditCardCVV(scanner.nextInt());
            System.out.println("Enter CreditCardExpiry");
            creditCard.setCreditCardExpiry(scanner.next());
            System.out.println("Enter CreditCardLimit");
            creditCard.setCreditCardLimit(scanner.nextInt());
            System.out.println("Enter CreditCardPin");
            creditCard.setCreditCardPin(scanner.nextInt());
            System.out.println("Enter DateOfBillGeneration");
            creditCard.setDateOfBillGeneration(scanner.next());
            System.out.println("Enter DateOfBillPayment");
            creditCard.setDateOfBillPayment(scanner.next());
            System.out.println("Enter CreditCardNumber");
            creditCard.setCreditCardNumber(scanner.nextLong());
            creditCardList.add(creditCard);
            myBankDatabase.create(creditCardList);
            System.out.println("Do you want to enter more details");
        }while(scanner.next().equalsIgnoreCase("yes"));
        myBankDatabase.read();
    }

    @Override
    public void create(List<CreditCard> creditCardArrayList) throws IOException, ClassNotFoundException {
        List<CreditCard> creditCard;
        File file=new File("OutputFile.txt");
        if(file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            creditCard = (List<CreditCard>) objectInputStream.readObject();//re read file
            creditCard.addAll(creditCardArrayList);//add new data into existing file
        }else{
            creditCard=creditCardArrayList;
        }
        FileOutputStream fileOutputStream=new FileOutputStream("OutputFile.txt");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(creditCard);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public void read() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream("OutputFile.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        List<CreditCard> creditCard= (List<CreditCard>) objectInputStream.readObject();
        System.out.println(creditCard);
        objectInputStream.close();
        fileInputStream.close();
    }
}
