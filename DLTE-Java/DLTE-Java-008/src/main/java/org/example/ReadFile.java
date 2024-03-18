package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        int ch;
        File file=new File("OutputFile.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        System.out.println(fileInputStream.available());
//        System.out.println(fileInputStream.getFD());
//        while ( (ch=fileInputStream.read())!=-1){
//            System.out.print((char)ch);// read bytes of data
//        }
        System.out.println("\nRead from byte file");
        byte[] readFile =new byte[fileInputStream.available()];
      //  String
        fileInputStream.read(readFile);
//        for (int i=0;i<readFile.length;i++)
//            System.out.println(i+" ");
        String data=new String(readFile);
        System.out.println(data);
        fileInputStream.close();
    }
}
