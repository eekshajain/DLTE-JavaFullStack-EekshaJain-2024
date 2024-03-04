package org.example;

import java.io.*;
import java.util.List;

public class ReadObjectFile {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream("ObjectOutputFile.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
//        Transaction transaction=(Transaction) objectInputStream.readObject();
//        System.out.println(transaction.getSentTo());
        List<Transaction> transactions= (List<Transaction>) objectInputStream.readObject();
       transactions.forEach(System.out::println);
    }
}
