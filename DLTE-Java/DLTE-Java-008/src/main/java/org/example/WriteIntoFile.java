package org.example;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.Date;

public class WriteIntoFile {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream("OutputFile.txt");
        String text="Writing from string";
        fileOutputStream.write(text.getBytes());//from string
        DataInputStream dataInputStream=new DataInputStream(System.in);
        String textFromUser=dataInputStream.readLine();
        fileOutputStream.write(textFromUser.getBytes());//from user
        fileOutputStream.close();
    }
}
