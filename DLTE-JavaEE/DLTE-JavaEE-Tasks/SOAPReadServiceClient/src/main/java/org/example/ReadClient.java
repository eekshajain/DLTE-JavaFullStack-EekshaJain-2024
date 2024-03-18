package org.example;


import read.ReadService;
import read.ReadServiceService;
import read.Transaction;
import read.TransactionGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadClient {
    public static void main(String[] args) {
        ReadServiceService readServiceService=new ReadServiceService();
        ReadService readService=readServiceService.getReadServicePort();
        TransactionGroup transactionGroup=readService.findByDateAndUser(new Date("3/12/2024"),"shreyas12");
        for(Transaction transaction:transactionGroup.getTransactionList())
            System.out.println(transaction.getTransactionID()+" "+transaction.getAmount()+" "+transaction.getBalance());
    }
}
