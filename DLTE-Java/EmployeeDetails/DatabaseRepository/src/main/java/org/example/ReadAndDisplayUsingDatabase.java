package org.example;

import oracle.jdbc.OracleDriver;
import org.middleware.Employee;
import org.middleware.EmployeeAddress;
import org.middleware.EmployeeBasicDetails;
import org.middleware.InputEmployeeDetails;

import java.sql.*;
import java.util.*;

public class ReadAndDisplayUsingDatabase implements InputEmployeeDetails {
    Connection connection;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    List<Employee> employees=new ArrayList<>();
    public ReadAndDisplayUsingDatabase() {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.password"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean isEstablished(){
        return connection!=null;
    }

    @Override
    public void saveAll(Employee employee) {
         int employeeID=employee.getEmployeeBasicDetails().getEmployeeID();
         try {
             String insertBasicDetails="insert into EmployeeBasicDetails(EMPLOYEE_ID,FIRST_NAME,MIDDLE_NAME,LAST_NAME,PHONE_NUMBER,EMAIL_ID) values (?,?,?,?,?,?)";
             preparedStatement=connection.prepareStatement(insertBasicDetails);
             preparedStatement.setInt(1,employeeID);
             preparedStatement.setString(2,employee.getEmployeeBasicDetails().getFirstName());
             preparedStatement.setString(3,employee.getEmployeeBasicDetails().getMiddleName());
             preparedStatement.setString(4,employee.getEmployeeBasicDetails().getLastName());
             preparedStatement.setLong(5,employee.getEmployeeBasicDetails().getPhoneNumber());
             preparedStatement.setString(6,employee.getEmployeeBasicDetails().getEmailID());
             int resultBasic=preparedStatement.executeUpdate();

             String insertTemporaryAddress="insert into TemporaryAddress(EMPLOYEE_ID,HOUSE_NAME,STREET_NAME,CITY_NAME,STATE_NAME,PIN_CODE) values (?,?,?,?,?,?)";
             preparedStatement=connection.prepareStatement(insertTemporaryAddress);
             preparedStatement.setInt(1,employeeID);
             preparedStatement.setString(2,employee.getTemporaryEmployeeAddress().getHouseName());
             preparedStatement.setString(3,employee.getTemporaryEmployeeAddress().getHouseStreet());
             preparedStatement.setString(4,employee.getTemporaryEmployeeAddress().getCityName());
             preparedStatement.setString(5,employee.getTemporaryEmployeeAddress().getStateName());
             preparedStatement.setInt(6,employee.getTemporaryEmployeeAddress().getPinCode());
             int resultTemporary=preparedStatement.executeUpdate();

             String insertPermanentAddress="insert into PermanentAddress(EMPLOYEE_ID,HOUSE_NAME,STREET_NAME,CITY_NAME,STATE_NAME,PIN_CODE) values (?,?,?,?,?,?)";
             preparedStatement=connection.prepareStatement(insertPermanentAddress);
             preparedStatement.setInt(1,employeeID);
             preparedStatement.setString(2,employee.getPermanentEmployeeAddress().getHouseName());
             preparedStatement.setString(3,employee.getPermanentEmployeeAddress().getHouseStreet());
             preparedStatement.setString(4,employee.getPermanentEmployeeAddress().getCityName());
             preparedStatement.setString(5,employee.getPermanentEmployeeAddress().getStateName());
             preparedStatement.setInt(6,employee.getPermanentEmployeeAddress().getPinCode());
             int resultPermanent=preparedStatement.executeUpdate();
             if(resultBasic!=0){
                 System.out.println("Basic details inserted");
             }else{
                 System.out.println("failed");
             }
             if(resultTemporary!=0) System.out.println("Temporary address inserted");
             if(resultPermanent!=0) System.out.println("Permanent address inserted");
         } catch (SQLException e) {
             e.printStackTrace();
         }

    }

    @Override
    public Employee displayRequired(int employeeID) {
        Employee employee=null;
        EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
        EmployeeAddress tempEmployeeAddress=new EmployeeAddress();
        EmployeeAddress permEmployeeAddress=new EmployeeAddress();
        EmployeeAddress employeeAddress=new EmployeeAddress();
        try {
            String findByID="SELECT * FROM employeebasicdetails ebd INNER JOIN temporaryaddress ta ON ebd.employee_id = ta.employee_id INNER JOIN permanentaddress pa ON ebd.employee_id = pa.employee_id WHERE ebd.employee_id = ?";
            preparedStatement=connection.prepareStatement(findByID);
            preparedStatement.setInt(1,employeeID);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                employeeBasicDetails.setEmployeeID(resultSet.getInt(1));
                employeeBasicDetails.setFirstName(resultSet.getString(2));
                employeeBasicDetails.setMiddleName(resultSet.getString(3));
                employeeBasicDetails.setLastName(resultSet.getString(4));
                employeeBasicDetails.setPhoneNumber(resultSet.getLong(5));
                employeeBasicDetails.setEmailID(resultSet.getString(6));

                tempEmployeeAddress.setHouseName(resultSet.getString(8));
//                employeeAddress.setTemporaryHouseStreet(resultSet.getString("street_name"));
//                employeeAddress.setTemporaryCityName(resultSet.getString("city_name"));
//                employeeAddress.setTemporaryStateName(resultSet.getString("state_name"));
//                employeeAddress.setTemporaryPinCode(resultSet.getInt("pin_code"));
                tempEmployeeAddress.setHouseStreet(resultSet.getString(9));
                tempEmployeeAddress.setCityName(resultSet.getString(10));
                tempEmployeeAddress.setStateName(resultSet.getString(11));
                tempEmployeeAddress.setPinCode(resultSet.getInt(12));

                permEmployeeAddress.setHouseName(resultSet.getString(14));
                tempEmployeeAddress.setHouseStreet(resultSet.getString(15));
                tempEmployeeAddress.setCityName(resultSet.getString(16));
                tempEmployeeAddress.setStateName(resultSet.getString(17));
                tempEmployeeAddress.setPinCode(resultSet.getInt(18));
               employee= new Employee(employeeBasicDetails,tempEmployeeAddress,permEmployeeAddress);
              return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int temporaryPincode) {
        try {
            Employee employee=new Employee();
            EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
            EmployeeAddress tempEmployeeAddress=new EmployeeAddress();
            EmployeeAddress permEmployeeAddress=new EmployeeAddress();
            String findByID="SELECT * FROM employeebasicdetails ebd INNER JOIN temporaryaddress ta ON ebd.employee_id = ta.employee_id INNER JOIN permanentaddress pa ON ebd.employee_id = pa.employee_id WHERE ta.pin_code = ?";
            preparedStatement=connection.prepareStatement(findByID);
            preparedStatement.setInt(1,temporaryPincode);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                employeeBasicDetails.setEmployeeID(resultSet.getInt(1));
                employeeBasicDetails.setFirstName(resultSet.getString(2));
                employeeBasicDetails.setMiddleName(resultSet.getString(3));
                employeeBasicDetails.setLastName(resultSet.getString(4));
                employeeBasicDetails.setPhoneNumber(resultSet.getLong(5));
                employeeBasicDetails.setEmailID(resultSet.getString(6));

                tempEmployeeAddress.setHouseName(resultSet.getString(8));
                tempEmployeeAddress.setHouseStreet(resultSet.getString(9));
                tempEmployeeAddress.setCityName(resultSet.getString(10));
                tempEmployeeAddress.setStateName(resultSet.getString(11));
                tempEmployeeAddress.setPinCode(resultSet.getInt(12));
//                employeeAddress.setTemporaryHouseStreet(resultSet.getString("street_name"));
//                employeeAddress.setTemporaryCityName(resultSet.getString("city_name"));
//                employeeAddress.setTemporaryStateName(resultSet.getString("state_name"));
//                employeeAddress.setTemporaryPinCode(resultSet.getInt("pin_code"));
                permEmployeeAddress.setHouseName(resultSet.getString(14));
                permEmployeeAddress.setHouseStreet(resultSet.getString(15));
                permEmployeeAddress.setCityName(resultSet.getString(16));
                permEmployeeAddress.setStateName(resultSet.getString(17));
                permEmployeeAddress.setPinCode(resultSet.getInt(18));
                employees.add(new Employee(employeeBasicDetails,tempEmployeeAddress,permEmployeeAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<Employee> displayAll() {
       // Employee employee=null;
        try {
            String findAll="SELECT * FROM employeebasicdetails ebd INNER JOIN temporaryaddress ta ON ebd.employee_id = ta.employee_id INNER JOIN permanentaddress pa ON ebd.employee_id = pa.employee_id ";
            preparedStatement=connection.prepareStatement(findAll);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee=new Employee();
                EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
                EmployeeAddress tempEmployeeAddress=new EmployeeAddress();
                EmployeeAddress permEmployeeAddress=new EmployeeAddress();
                employeeBasicDetails.setEmployeeID(resultSet.getInt(1));
                employeeBasicDetails.setFirstName(resultSet.getString(2));
                employeeBasicDetails.setMiddleName(resultSet.getString(3));
                employeeBasicDetails.setLastName(resultSet.getString(4));
                employeeBasicDetails.setPhoneNumber(resultSet.getLong(5));
                employeeBasicDetails.setEmailID(resultSet.getString(6));

                tempEmployeeAddress.setHouseName(resultSet.getString(8));
//                employeeAddress.setTemporaryHouseStreet(resultSet.getString("street_name"));
//                employeeAddress.setTemporaryCityName(resultSet.getString("city_name"));
//                employeeAddress.setTemporaryStateName(resultSet.getString("state_name"));
//                employeeAddress.setTemporaryPinCode(resultSet.getInt("pin_code"));
                tempEmployeeAddress.setHouseStreet(resultSet.getString(9));
                tempEmployeeAddress.setCityName(resultSet.getString(10));
                tempEmployeeAddress.setStateName(resultSet.getString(11));
                tempEmployeeAddress.setPinCode(resultSet.getInt(12));
                permEmployeeAddress.setHouseName(resultSet.getString(14));
                permEmployeeAddress.setHouseStreet(resultSet.getString(15));
                permEmployeeAddress.setCityName(resultSet.getString(16));
                permEmployeeAddress.setStateName(resultSet.getString(17));
                permEmployeeAddress.setPinCode(resultSet.getInt(18));
                employees.add(new Employee(employeeBasicDetails,tempEmployeeAddress,permEmployeeAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public boolean doesEmployeeExists(int employeeID) {
        try {
            String findByID="SELECT * FROM employeebasicdetails ebd INNER JOIN temporaryaddress ta ON ebd.employee_id = ta.employee_id INNER JOIN permanentaddress pa ON ebd.employee_id = pa.employee_id WHERE ebd.employee_id = ?";
            preparedStatement=connection.prepareStatement(findByID);
            preparedStatement.setInt(1,employeeID);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteByID(int employeeID) {
        String deleteByID="DELETE FROM employeebasicdetails WHERE employee_id = ?";
        try {
            preparedStatement=connection.prepareStatement(deleteByID);
            preparedStatement.setInt(1,employeeID);
           int deleteResult=preparedStatement.executeUpdate();
           if(deleteResult!=0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
  return false;
    }
}
