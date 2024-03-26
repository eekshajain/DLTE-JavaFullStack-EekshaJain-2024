package soapdao.implementation;

import employee.entity.Employee;
import employee.implementation.CreateConnection;
import employee.implementation.ReadAndDisplayUsingDatabase;
import employee.interfaces.InputEmployeeDetails;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebServiceDAO {
    InputEmployeeDetails inputEmployeeDetails;
    Connection connection;

    // Constructor to initialize InputEmployeeDetails and establish database connection
    public WebServiceDAO() {
        inputEmployeeDetails = new ReadAndDisplayUsingDatabase();
        connection= CreateConnection.createConnection();
    }

    @WebResult(name="addNewEmployee")
    public Employee callSaveAll(Employee employee){
      Employee newEmployee=  inputEmployeeDetails.saveAll(employee);
      return newEmployee;
    }

    @WebResult(name="findBasedOnId")
    public Employee callFilterBasedOnID(int employeeId){
        Employee employee = inputEmployeeDetails.displayRequired(employeeId);
        return employee;
    }

    @WebResult(name="findBasedOnPincode")
    public ArrayList<Employee> callFilterBasedOnPincode(int pincode){
        ArrayList<Employee> employees= (ArrayList<Employee>) inputEmployeeDetails.displayBasedOnPinCode(pincode);
        if(employees!=null) return employees;
        else return null;
    }

    @WebResult(name="findAll")
    public ArrayList<Employee> callFindAll(){
        ArrayList<Employee> employees= (ArrayList<Employee>) inputEmployeeDetails.displayAll();
        return employees;
    }

    @WebResult(name = "doesEmployeeExists")
    public boolean callEmployeeExists(int empId){
        return inputEmployeeDetails.doesEmployeeExists(empId);
    }
}
