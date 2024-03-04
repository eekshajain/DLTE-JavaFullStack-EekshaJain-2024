package org.example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WriteObject {
    public static void main(String[] args) throws IOException {
        List<Transaction> transactionArrayList=new ArrayList<>();
        List<Transaction> transactionArrayList1=new ArrayList<>();
        transactionArrayList.add(new Transaction(new Date("2/12/2024"),2330,"Vandana","Friend"));
        transactionArrayList.add(new Transaction(new Date("2/10/2024"),2330,"Vani","Friend"));
        FileOutputStream fileOutputStream=new FileOutputStream("ObjectOutputFile.txt");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(transactionArrayList);
       transactionArrayList1.add(new Transaction(new Date("2/10/2024"),2330,"Vanitha","Friend"));
        objectOutputStream.writeObject(transactionArrayList1);
    }
}
