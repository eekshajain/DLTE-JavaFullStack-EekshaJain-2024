package employee.implementation;

import employee.entity.Employee;
import employee.entity.EmployeeAddress;
import employee.entity.EmployeeBasicDetails;
import employee.interfaces.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validate.data.ValidationOfData;

import java.sql.*;
import java.util.*;

public class ReadAndDisplayUsingDatabase implements InputEmployeeDetails {
    static Logger logger= LoggerFactory.getLogger("App.class");//import from org.slf4j
    ResourceBundle resourceBundle1=ResourceBundle.getBundle("application");
    Connection connection;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    List<Employee> employees=new ArrayList<>();
    ValidationOfData validationData=new ValidationOfData();
    public ReadAndDisplayUsingDatabase()
    {
        connection=CreateConnection.createConnection();
    }


    @Override
    public Employee saveAll(Employee employee) {
        boolean success=false;
//        if(employee.getEmployeeBasicDetails().getLastName()==null){
//            logger.error("Last name is missing!");
//            throw new EmployeeExceptions("last.name.missing");
//        }
//        if(!validationData.isPhoneNumberValid(employee.getEmployeeBasicDetails().getPhoneNumber())){
//            logger.error("Phone number is corrupted!");
//            throw new EmployeeExceptions("invalid.phone.number");
//        }
//        if(!validationData.isEmailValid(employee.getEmployeeBasicDetails().getEmailID())){
//            logger.error("Email ID is corrupted!");
//            throw new EmployeeExceptions("invalid.email");
//        }
//        if(!validationData.isPinCodeValid(employee.getTemporaryEmployeeAddress().getPinCode())){
//            logger.error("Temporary pincode is corrupted!");
//            throw new EmployeeExceptions("invalid.temporary.pincode");
//        }
//        if(!validationData.isPinCodeValid(employee.getPermanentEmployeeAddress().getPinCode())){
//            logger.error("Permanent pincode is corrupted!");
//            throw new EmployeeExceptions("invalid.permanent.pincode");
//        }
        if (employee == null) {
            throw new IllegalArgumentException("Employee object is null");
        }

        if (employee.getEmployeeBasicDetails() == null) {
            throw new IllegalArgumentException("EmployeeBasicDetails is null");
        }

        if (employee.getTemporaryEmployeeAddress() == null) {
            throw new IllegalArgumentException("TemporaryEmployeeAddress is null");
        }

        if (employee.getPermanentEmployeeAddress() == null) {
            throw new IllegalArgumentException("PermanentEmployeeAddress is null");
        }

            int employeeID = employee.getEmployeeBasicDetails().getEmployeeID();
            try {
                connection=CreateConnection.createConnection();
                String insertBasicDetails = "insert into EmployeeBasicDetails(EMPLOYEE_ID,FIRST_NAME,MIDDLE_NAME,LAST_NAME,PHONE_NUMBER,EMAIL_ID) values (?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(insertBasicDetails);
                preparedStatement.setInt(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeeBasicDetails().getFirstName());
                preparedStatement.setString(3, employee.getEmployeeBasicDetails().getMiddleName());
                preparedStatement.setString(4, employee.getEmployeeBasicDetails().getLastName());
                preparedStatement.setLong(5, employee.getEmployeeBasicDetails().getPhoneNumber());
                preparedStatement.setString(6, employee.getEmployeeBasicDetails().getEmailID());
                int resultBasic = preparedStatement.executeUpdate();

                //String insertTemporaryAddress = "insert into TemporaryAddress(EMPLOYEE_ID,HOUSE_NAME,STREET_NAME,CITY_NAME,STATE_NAME,PIN_CODE) values (?,?,?,?,?,?)";
                String insertTemporaryAddress = "insert into EmployeeAddress(ADDRESS_ID,EMPLOYEE_ID,HOUSE_NAME,STREET_NAME,CITY_NAME,STATE_NAME,PIN_CODE,IS_TEMPORARY) values (address_seq.nextval,?,?,?,?,?,?,1)";
                preparedStatement = connection.prepareStatement(insertTemporaryAddress);
                preparedStatement.setInt(1, employeeID);
                preparedStatement.setString(2, employee.getTemporaryEmployeeAddress().getHouseName());
                preparedStatement.setString(3, employee.getTemporaryEmployeeAddress().getHouseStreet());
                preparedStatement.setString(4, employee.getTemporaryEmployeeAddress().getCityName());
                preparedStatement.setString(5, employee.getTemporaryEmployeeAddress().getStateName());
                preparedStatement.setInt(6, employee.getTemporaryEmployeeAddress().getPinCode());
                int resultTemporary = preparedStatement.executeUpdate();

                //String insertPermanentAddress = "insert into PermanentAddress(EMPLOYEE_ID,HOUSE_NAME,STREET_NAME,CITY_NAME,STATE_NAME,PIN_CODE) values (?,?,?,?,?,?)";
                String insertPermanentAddress = "insert into EmployeeAddress(ADDRESS_ID,EMPLOYEE_ID,HOUSE_NAME,STREET_NAME,CITY_NAME,STATE_NAME,PIN_CODE,IS_TEMPORARY) values (address_seq.nextval,?,?,?,?,?,?,0)";
                preparedStatement = connection.prepareStatement(insertPermanentAddress);
                preparedStatement.setInt(1, employeeID);
                preparedStatement.setString(2, employee.getPermanentEmployeeAddress().getHouseName());
                preparedStatement.setString(3, employee.getPermanentEmployeeAddress().getHouseStreet());
                preparedStatement.setString(4, employee.getPermanentEmployeeAddress().getCityName());
                preparedStatement.setString(5, employee.getPermanentEmployeeAddress().getStateName());
                preparedStatement.setInt(6, employee.getPermanentEmployeeAddress().getPinCode());
                int resultPermanent = preparedStatement.executeUpdate();
                  success = resultBasic>0 & resultPermanent>0 & resultTemporary>0;
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                close();
            }

          return employee;
    }

    @Override
    public Employee displayRequired(int employeeID) {
        Employee employee=null;
       EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
        EmployeeAddress tempEmployeeAddress=new EmployeeAddress();
        EmployeeAddress permEmployeeAddress=new EmployeeAddress();
        EmployeeAddress employeeAddress=new EmployeeAddress();
        try {
            connection=CreateConnection.createConnection();
          //  String findByID="SELECT * FROM employeebasicdetails ebd INNER JOIN temporaryaddress ta ON ebd.employee_id = ta.employee_id INNER JOIN permanentaddress pa ON ebd.employee_id = pa.employee_id WHERE ebd.employee_id = ?";
            String findByID = "SELECT e.EMPLOYEE_ID,\n" +
                    "       e.FIRST_NAME,\n" +
                    "       e.MIDDLE_NAME,\n" +
                    "       e.LAST_NAME,\n" +
                    "       e.PHONE_NUMBER,\n" +
                    "       e.EMAIL_ID,\n" +
                    "       ta.HOUSE_NAME ,\n" +
                    "       ta.STREET_NAME ,\n" +
                    "       ta.CITY_NAME ,\n" +
                    "       ta.STATE_NAME ,\n" +
                    "       ta.PIN_CODE ,\n" +
                    "       pa.HOUSE_NAME ,\n" +
                    "       pa.STREET_NAME ,\n" +
                    "       pa.CITY_NAME ,\n" +
                    "       pa.STATE_NAME ,\n" +
                    "       pa.PIN_CODE \n" +
                    "FROM EmployeeBasicDetails e\n" +
                    "INNER JOIN EmployeeAddress ta ON e.EMPLOYEE_ID = ta.EMPLOYEE_ID AND ta.IS_TEMPORARY = 1\n" +
                    "INNER JOIN EmployeeAddress pa ON e.EMPLOYEE_ID = pa.EMPLOYEE_ID AND pa.IS_TEMPORARY = 0\n" +
                    "WHERE e.EMPLOYEE_ID=?";
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

                tempEmployeeAddress.setHouseName(resultSet.getString(7));
//                employeeAddress.setTemporaryHouseStreet(resultSet.getString("street_name"));
//                employeeAddress.setTemporaryCityName(resultSet.getString("city_name"));
//                employeeAddress.setTemporaryStateName(resultSet.getString("state_name"));
//                employeeAddress.setTemporaryPinCode(resultSet.getInt("pin_code"));
                tempEmployeeAddress.setHouseStreet(resultSet.getString(8));
                tempEmployeeAddress.setCityName(resultSet.getString(9));
                tempEmployeeAddress.setStateName(resultSet.getString(10));
                tempEmployeeAddress.setPinCode(resultSet.getInt(11));

                permEmployeeAddress.setHouseName(resultSet.getString(12));
                permEmployeeAddress.setHouseStreet(resultSet.getString(13));
                permEmployeeAddress.setCityName(resultSet.getString(14));
                permEmployeeAddress.setStateName(resultSet.getString(15));
                permEmployeeAddress.setPinCode(resultSet.getInt(16));
//                employeeBasicDetails.setTemporaryEmployeeAddress(tempEmployeeAddress);
//                employeeBasicDetails.setTemporaryEmployeeAddress(permEmployeeAddress);
                logger.info("Displaying details of Employee with Employee ID:"+employeeID);
                employee =new Employee(employeeBasicDetails,permEmployeeAddress,tempEmployeeAddress);
              return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return null;
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int pincode) {
        List<Employee> employees1=new ArrayList<>();
        try {
            connection=CreateConnection.createConnection();
          //  Employee employee=new Employee();
            EmployeeBasicDetails employeeBasicDetails;
            EmployeeAddress tempEmployeeAddress;
            EmployeeAddress permEmployeeAddress;
           // String findByID="SELECT * FROM employeebasicdetails ebd JOIN temporaryaddress ta ON ebd.employee_id = ta.employee_id JOIN permanentaddress pa ON ebd.employee_id = pa.employee_id WHERE pa.pin_code = ? or ta.pin_code=?";
            String findByPincode = "SELECT e.EMPLOYEE_ID,\n" +
                    "       e.FIRST_NAME,\n" +
                    "       e.MIDDLE_NAME,\n" +
                    "       e.LAST_NAME,\n" +
                    "       e.PHONE_NUMBER,\n" +
                    "       e.EMAIL_ID,\n" +
                    "       ta.HOUSE_NAME ,\n" +
                    "       ta.STREET_NAME ,\n" +
                    "       ta.CITY_NAME ,\n" +
                    "       ta.STATE_NAME ,\n" +
                    "       ta.PIN_CODE ,\n" +
                    "       pa.HOUSE_NAME ,\n" +
                    "       pa.STREET_NAME ,\n" +
                    "       pa.CITY_NAME ,\n" +
                    "       pa.STATE_NAME ,\n" +
                    "       pa.PIN_CODE \n" +
                    "FROM EmployeeBasicDetails e\n" +
                    "INNER JOIN EmployeeAddress ta ON e.EMPLOYEE_ID = ta.EMPLOYEE_ID AND ta.IS_TEMPORARY = 1\n" +
                    "INNER JOIN EmployeeAddress pa ON e.EMPLOYEE_ID = pa.EMPLOYEE_ID AND pa.IS_TEMPORARY = 0 " + // Added space before "WHERE"
                    "WHERE ta.PIN_CODE=? or pa.PIN_CODE=?";
            preparedStatement=connection.prepareStatement(findByPincode);
            preparedStatement.setInt(1,pincode);
            preparedStatement.setInt(2,pincode);
            resultSet=preparedStatement.executeQuery();
            logger.info("Displaying details based on Temporary Pincode:"+pincode);
            while (resultSet.next()){
                employeeBasicDetails=new EmployeeBasicDetails();
                tempEmployeeAddress=new EmployeeAddress();
                permEmployeeAddress=new EmployeeAddress();
                employeeBasicDetails.setEmployeeID(resultSet.getInt(1));
                employeeBasicDetails.setFirstName(resultSet.getString(2));
                employeeBasicDetails.setMiddleName(resultSet.getString(3));
                employeeBasicDetails.setLastName(resultSet.getString(4));
                employeeBasicDetails.setPhoneNumber(resultSet.getLong(5));
                employeeBasicDetails.setEmailID(resultSet.getString(6));

                tempEmployeeAddress.setHouseName(resultSet.getString(7));
                tempEmployeeAddress.setHouseStreet(resultSet.getString(8));
                tempEmployeeAddress.setCityName(resultSet.getString(9));
                tempEmployeeAddress.setStateName(resultSet.getString(1));
                tempEmployeeAddress.setPinCode(resultSet.getInt(11));
//                employeeAddress.setTemporaryHouseStreet(resultSet.getString("street_name"));
//                employeeAddress.setTemporaryCityName(resultSet.getString("city_name"));
//                employeeAddress.setTemporaryStateName(resultSet.getString("state_name"));
//                employeeAddress.setTemporaryPinCode(resultSet.getInt("pin_code"));
                permEmployeeAddress.setHouseName(resultSet.getString(12));
                permEmployeeAddress.setHouseStreet(resultSet.getString(13));
                permEmployeeAddress.setCityName(resultSet.getString(14));
                permEmployeeAddress.setStateName(resultSet.getString(15));
                permEmployeeAddress.setPinCode(resultSet.getInt(16));
                employees1.add(new Employee(employeeBasicDetails,tempEmployeeAddress,permEmployeeAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return employees1;
    }

    @Override
    public List<Employee> displayAll() {
        List<Employee> employees1=new ArrayList<>();
        // Employee employee=null;
        try {
            connection=CreateConnection.createConnection();
            //String findAll="SELECT * FROM employeebasicdetails ebd INNER JOIN temporaryaddress ta ON ebd.employee_id = ta.employee_id INNER JOIN permanentaddress pa ON ebd.employee_id = pa.employee_id ";
            String findAll = "SELECT e.EMPLOYEE_ID,\n" +
                    "       e.FIRST_NAME,\n" +
                    "       e.MIDDLE_NAME,\n" +
                    "       e.LAST_NAME,\n" +
                    "       e.PHONE_NUMBER,\n" +
                    "       e.EMAIL_ID,\n" +
                    "       ta.HOUSE_NAME as TEMPORARY_HOUSENAME,\n" +
                    "       ta.STREET_NAME ,\n" +
                    "       ta.CITY_NAME ,\n" +
                    "       ta.STATE_NAME ,\n" +
                    "       ta.PIN_CODE ,\n" +
                    "       pa.HOUSE_NAME ,\n" +
                    "       pa.STREET_NAME ,\n" +
                    "       pa.CITY_NAME ,\n" +
                    "       pa.STATE_NAME ,\n" +
                    "       pa.PIN_CODE \n" +
                    "FROM EmployeeBasicDetails e\n" +
                    "INNER JOIN EmployeeAddress ta ON e.EMPLOYEE_ID = ta.EMPLOYEE_ID AND ta.IS_TEMPORARY = 1\n" +
                    "INNER JOIN EmployeeAddress pa ON e.EMPLOYEE_ID = pa.EMPLOYEE_ID AND pa.IS_TEMPORARY = 0";
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
                //employeeBasicDetails.setTemporaryEmployeeAddress(tempEmployeeAddress.setHouseName(resultSet.getString(8)));
                tempEmployeeAddress.setHouseName(resultSet.getString("TEMPORARY_HOUSENAME"));
//                employeeAddress.setTemporaryHouseStreet(resultSet.getString("street_name"));
//                employeeAddress.setTemporaryCityName(resultSet.getString("city_name"));
//                employeeAddress.setTemporaryStateName(resultSet.getString("state_name"));
//                employeeAddress.setTemporaryPinCode(resultSet.getInt("pin_code"));
                tempEmployeeAddress.setHouseStreet(resultSet.getString(8));
                tempEmployeeAddress.setCityName(resultSet.getString(9));
                tempEmployeeAddress.setStateName(resultSet.getString(10));
                tempEmployeeAddress.setPinCode(resultSet.getInt(11));
                permEmployeeAddress.setHouseName(resultSet.getString(12));
                permEmployeeAddress.setHouseStreet(resultSet.getString(13));
                permEmployeeAddress.setCityName(resultSet.getString(14));
                permEmployeeAddress.setStateName(resultSet.getString(15));
                permEmployeeAddress.setPinCode(resultSet.getInt(16));
                employees1.add(new Employee(employeeBasicDetails,tempEmployeeAddress,permEmployeeAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return employees1;
    }

    @Override
    public boolean doesEmployeeExists(int employeeID) {
        try {
            connection=CreateConnection.createConnection();
           // String findByID="SELECT ebd.employee_id FROM employeebasicdetails ebd INNER JOIN temporaryaddress ta ON ebd.employee_id = ta.employee_id INNER JOIN permanentaddress pa ON ebd.employee_id = pa.employee_id WHERE ebd.employee_id = ?";
            String findByID="SELECT ebd.employee_id FROM employeebasicdetails ebd WHERE ebd.employee_id = ?";
            preparedStatement=connection.prepareStatement(findByID);
            preparedStatement.setInt(1,employeeID);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int employeeID) {
        String deleteByID="DELETE FROM employeebasicdetails WHERE employee_id = ?";
        try {
            connection=CreateConnection.createConnection();
            preparedStatement=connection.prepareStatement(deleteByID);
            preparedStatement.setInt(1,employeeID);
           int deleteResult=preparedStatement.executeUpdate();
           if(deleteResult!=0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
  return false;
    }

    public void close(){
        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new EmployeeExceptions("system.error");
            }
        }
        if(preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new EmployeeExceptions("system.error");
            }
        }
        try{
           if(connection!=null) connection.close();
        } catch (SQLException e) {
            throw new EmployeeExceptions("system.error");
        }
    }


}
