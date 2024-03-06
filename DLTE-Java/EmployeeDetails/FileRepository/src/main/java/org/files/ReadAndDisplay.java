package org.files;

import org.middleware.*;

import java.io.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;

public class ReadAndDisplay implements InputEmployeeDetails {
 List<Employee> employeeList=new ArrayList<>();
File filePath=new File("AllDetails.txt");
   public void writeIntoFile() throws IOException {
       FileOutputStream fileOutputStream=new FileOutputStream(filePath);
       ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
       objectOutputStream.writeObject(employeeList);
       objectOutputStream.close();
       fileOutputStream.close();
   }
public void readFromFile() throws IOException, ClassNotFoundException {
    FileInputStream fileInputStream=new FileInputStream(filePath);
    ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
    employeeList=(List<Employee>) objectInputStream.readObject();
    objectInputStream.close();
    fileInputStream.close();
}


    @Override
    public void saveAll(List<Employee> employee) {
        try {
           if(filePath.exists()){ readFromFile();}
           for(Employee employee1:employee) {
               boolean employeeExists = employeeList.stream().anyMatch(alreadyExist -> alreadyExist.getEmployeeBasicDetails().getEmployeeID() == employee1.getEmployeeBasicDetails().getEmployeeID());
               if (employeeExists) System.out.println("Employee already exists");
               else {
                   employeeList.addAll(employee);
               }
           }
            writeIntoFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee displayRequired(int employeeID) {
        try {
         readFromFile();
         return employeeList.stream().filter(employee1 -> employee1.getEmployeeBasicDetails().getEmployeeID()==employeeID).findFirst().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee displayBasedOnPinCode(int pinCode) {
        try {
            readFromFile();
            return employeeList.stream().filter(employee1 -> employee1.getEmployeeAddress().getTemporaryPinCode()==pinCode).findAny().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> displayAll() {
        try {
            readFromFile();
            return employeeList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
