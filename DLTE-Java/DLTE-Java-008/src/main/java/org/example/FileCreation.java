package org.example;

import java.io.File;
import java.io.IOException;

public class FileCreation {
    public static void main(String[] args) throws IOException {
        File file =new File("C:\\DLTE-JavaFullStack-EekshaJain-2024\\DLTE-Java\\DLTE-Java-008\\SampleFile.txt");
       // File file1 =new File("C:\\DLTE-JavaFullStack-EekshaJain-2024\\DLTE-Java\\DLTE-Java-010\\SampleFile.txt");//generate IOException
            file.createNewFile();
        System.out.println("Is File "+file.isFile()+" Is directory "+file.isDirectory()+" Is absolute "+file.isAbsolute()+" File exists "+file.exists());
        System.out.println("Creating another file");
            file.createNewFile();//generated exception as specified directory is not existing
        System.out.println("Is SampleFile2 exists "+file.exists());
    }
}
