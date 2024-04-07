package soapdao.implementation;

import employee.entity.Employee;
import employee.implementation.CreateConnection;
import employee.implementation.EmployeeExceptions;
import employee.implementation.ReadAndDisplayUsingDatabase;
import employee.interfaces.InputEmployeeDetails;
import soapdao.exceptions.EmployeeNotFound;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.ValidationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.Response;
import javax.xml.ws.soap.SOAPFaultException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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

    private String exceptionHandler(Exception e) {
         if (e.getClass().equals(SQLException.class)) {
            return "SQLException";
        } else if (e.getClass().equals(EmployeeNotFound.class)) {
            return "EmployeeNotFoundException";
        } else {
            return "Unknown Exception";
        }
    }
    @WebResult(name="addNewEmployee")
    public Employee callSaveAll(Employee employee){
      Employee newEmployee=  inputEmployeeDetails.saveAll(employee);
      return newEmployee;
    }

    @WebResult(name="findBasedOnId")
//    public Employee callFilterBasedOnID(int employeeId){
//        Employee employee = inputEmployeeDetails.displayRequired(employeeId);
//        return employee;
//    }
    public Employee callFilterBasedOnID(int employeeId) throws SOAPException {
        SOAPFault soapFault = SOAPFactory.newInstance().createFault();
        Employee employee=new Employee();
        try {
            employee = inputEmployeeDetails.displayRequired(employeeId);
        }catch (Exception e){
            //logger.error(e.getMessage() + e.getClass().getName());
            try {
                soapFault = SOAPFactory.newInstance().createFault();
                soapFault.setFaultCode(exceptionHandler(e));
                soapFault.setFaultString(e.getMessage());
            } catch (SOAPException ex) {
                ex.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return employee;
    }

    @WebResult(name="findBasedOnPincode")
    public GroupOfEmployees callFilterBasedOnPincode(int pincode){
        GroupOfEmployees groupOfEmployees=new GroupOfEmployees();
        ArrayList<Employee> employees= (ArrayList<Employee>) inputEmployeeDetails.displayBasedOnPinCode(pincode);
       if(employees!=null) groupOfEmployees.setEmployeesArrayList(employees);
       return groupOfEmployees;
    }

    @WebResult(name="findAll")
//    public GroupOfEmployees callFindAll(){
//        GroupOfEmployees groupOfEmployees=new GroupOfEmployees();
//        ArrayList<Employee> employees= (ArrayList<Employee>) inputEmployeeDetails.displayAll();
//         groupOfEmployees.setEmployeesArrayList(employees);
//         return groupOfEmployees;
//    }
    public GroupOfEmployees callFindAll(){
        GroupOfEmployees groupOfEmployees = new GroupOfEmployees();
        try {
            ArrayList<Employee> employees = (ArrayList<Employee>) inputEmployeeDetails.displayAll();
            groupOfEmployees.setEmployeesArrayList(employees);
        } catch (Exception e) {
           // logger.error(e.getMessage() + e.getClass().getName());
            try {
                // Create a SOAPFault instance
                SOAPFault soapFault = SOAPFactory.newInstance().createFault();
                // Set fault code and message
                soapFault.setFaultCode(exceptionHandler(e));
                soapFault.setFaultString(e.getMessage());
                // Throw SOAPFaultException with the SOAPFault
                throw new SOAPFaultException(soapFault);
            } catch (SOAPException ex) {
                ex.printStackTrace();
            }
        }
        return groupOfEmployees;
    }

    @WebResult(name = "doesEmployeeExists")
    public boolean callEmployeeExists(int empId){
        return inputEmployeeDetails.doesEmployeeExists(empId);
    }


}

