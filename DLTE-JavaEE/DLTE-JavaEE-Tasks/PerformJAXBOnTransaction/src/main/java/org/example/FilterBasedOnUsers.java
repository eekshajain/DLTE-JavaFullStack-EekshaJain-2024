package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FilterBasedOnUsers {
    public static void main(String[] args) {

        try {
            Scanner scanner=new Scanner(System.in);
            System.out.println("Enter user name:");
            String user=scanner.next();
         //   File file=new File("AllTransactions.xml");
            JAXBContext jaxbContext= JAXBContext.newInstance(TransactionWrapper.class);
            Unmarshaller unmarshaller =jaxbContext.createUnmarshaller();
           TransactionWrapper transactionWrapper = (TransactionWrapper) unmarshaller.unmarshal(new FileInputStream("AllTransactions.xml"));

            List<Transaction> transactions =transactionWrapper.getTransactions();
            for(Transaction transaction:transactions){
                if(transaction.getUser().equalsIgnoreCase(user)){
                    System.out.println("User:"+transaction.getUser());
                    System.out.println("Date:"+transaction.getDateOfTransaction());
                    System.out.println("Amount of transaction:"+transaction.getAmountOfTransaction());
                    System.out.println("Sent To:"+transaction.getSentTo());
                    System.out.println();
                }
            }
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
