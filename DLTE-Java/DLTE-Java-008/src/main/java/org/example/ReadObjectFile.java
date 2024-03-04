package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadObjectFile {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream("ObjectOutputFile.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
//        Transaction transaction=(Transaction) objectInputStream.readObject();
//        System.out.println(transaction.getSentTo());
        ArrayList<Transaction> transactions= (ArrayList<Transaction>) objectInputStream.readObject();
       transactions.forEach(System.out::println);
    }
}
