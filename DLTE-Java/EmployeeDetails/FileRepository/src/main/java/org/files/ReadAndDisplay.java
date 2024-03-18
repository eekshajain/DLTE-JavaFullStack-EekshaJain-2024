package org.files;

import org.middleware.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ReadAndDisplay implements InputEmployeeDetails {
   ResourceBundle resourceBundle=ResourceBundle.getBundle("filerepo");
    List<Employee> employeeList=new ArrayList<>();
    File filePath=new File("AllDetailsList.txt");
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
//           for(Employee employee1:employee) {
//               boolean employeeExists = employeeList.stream().anyMatch(alreadyExist -> alreadyExist.getEmployeeBasicDetails().getEmployeeID() == employee1.getEmployeeBasicDetails().getEmployeeID());
//                   if (employeeExists) throw new EmployeeException();
//                   else {
                       System.out.println("Employee added Successfully!");
                       employeeList.addAll(employee);
//                   }
//           }
            writeIntoFile();
        }
//        catch(EmployeeException e) {
//            System.out.println(resourceBundle.getString("employee.exists"));
//        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  public  boolean doesEmployeeExists(int employeeID){
       try{
           if(filePath.exists()) {
               readFromFile();
               return employeeList.stream().anyMatch(alreadyExist -> alreadyExist.getEmployeeBasicDetails().getEmployeeID() == employeeID);
           }
       } catch (IOException e) {
           e.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return false;
  }
    @Override
    public Employee displayRequired(int employeeID) {
        //List<Employee> employeeList=new ArrayList<>();
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
    public List<Employee> displayBasedOnPinCode(int pinCode) {
       // List<Employee> employeeList=new ArrayList<>();
        try {
            readFromFile();
            return employeeList.stream().filter(employee1 -> employee1.getEmployeeAddress().getTemporaryPinCode()==pinCode).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> displayAll() {
     //   List<Employee> employeeList=new ArrayList<>();
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
