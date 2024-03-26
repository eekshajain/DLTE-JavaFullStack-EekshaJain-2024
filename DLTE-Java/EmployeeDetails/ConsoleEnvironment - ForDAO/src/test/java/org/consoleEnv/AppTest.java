package org.consoleEnv;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import employee.entity.Employee;
import employee.entity.EmployeeAddress;
import employee.entity.EmployeeBasicDetails;
import employee.implementation.ReadAndDisplayUsingDatabase;
import employee.interfaces.InputEmployeeDetails;
import employee.methodsCalling.InputDetailsCollectAndDisplay;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import validate.data.ValidationOfData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Mock
    InputEmployeeDetails inputEmployeeDetails;
    @Mock
    ValidationOfData validation;
    private Connection connection;
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "system";
    private static final String PASSWORD = "662002";
    @Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection(JDBC_URL,USER,PASSWORD);
    }
    @Test
    public void testConnectionNotNull() {
        assertNotNull("Database connection should not be null", connection);
    }
    @Test
    public void testConnectionIsValid() {
        try {
            assertTrue("Database connection should be valid", connection.isValid(5)); // 5 seconds timeout
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }

   @After
   public void tearDown() throws SQLException {
        if(connection!=null) connection.close();
   }
@Test
    public void testInsert(){
    int empID=14;
    employee.entity.EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails(empID,"Eeksha","P","Jain",9876543210L,"jain@gmail.com");
    employee.entity.EmployeeAddress tempEmployeeAddress=new employee.entity.EmployeeAddress("6-75(D)","Jain Pete","Moodbidri","Karnataka",987643);
    employee.entity.EmployeeAddress permEmployeeAddress=new EmployeeAddress("Pushapadanta","Near Mahaveer College","Moodbidri","Karnataka",876421);
   Employee employee=new Employee(employeeBasicDetails,tempEmployeeAddress,permEmployeeAddress);
   int empID2=18;
    Employee employee3=new Employee(
            new EmployeeBasicDetails(empID2,"Lakshmi","Naveen","Raj",7654321980L,"diya@gmail.com"),
            new EmployeeAddress("Om","5th cross Rajaji Nagar"," Banglore","Karnataka",576403),
            new EmployeeAddress("Latha","Ram nagar","Moodbidri","Karnataka",5432009)
    );

    when(inputEmployeeDetails.saveAll(employee)).thenReturn(employee);
    inputEmployeeDetails.saveAll(employee);
    Employee employees=inputEmployeeDetails.saveAll(employee);
  //   verify(inputEmployeeDetails).saveAll(employee);
  assertEquals(employee,employees);
}

@Test
public void checkExists(){
    int empID=14;
    employee.entity.EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails(empID,"Eeksha","P","Jain",9876543210L,"jain@gmail.com");
    employee.entity.EmployeeAddress tempEmployeeAddress=new employee.entity.EmployeeAddress("6-75(D)","Jain Pete","Moodbidri","Karnataka",987643);
    employee.entity.EmployeeAddress permEmployeeAddress=new EmployeeAddress("Pushapadanta","Near Mahaveer College","Moodbidri","Karnataka",876421);
    Employee employee=new Employee(employeeBasicDetails,tempEmployeeAddress,permEmployeeAddress);
    int empID2=16;
    Employee employee3=new Employee(
            new EmployeeBasicDetails(empID2,"Lakshmi","Naveen","Raj",7654321980L,"diya@gmail.com"),
            new EmployeeAddress("Om","5th cross Rajaji Nagar"," Banglore","Karnataka",576403),
            new EmployeeAddress("Latha","Ram nagar","Moodbidri","Karnataka",5432009)
    );
    when(inputEmployeeDetails.doesEmployeeExists(empID2)).thenReturn(false);
}

@Test
public void validatePincode(){
    int pincode=537890;
    int picode2=87653;//fails
    boolean isValid=validation.isPinCodeValid(pincode);
    assertTrue(isValid);
}

@Test
public void testDisplayByPincode(){
    int pincode=567008;
Employee employee1=new Employee(
        new EmployeeBasicDetails(16,"Diya","Shekar","Shetty",9812345679L,"diya@gmail.com"),
        new EmployeeAddress("Shanthi","6th cross Jaynagar"," Banglore","Karnataka",567008),
        new EmployeeAddress("Lakshmi","Ram nagar","Moodbidri","Karnataka",5432009)
);
    Employee employee2=new Employee(
            new EmployeeBasicDetails(17,"Shwetha","Shekar","Shetty",9845679432L,"diya@gmail.com"),
            new EmployeeAddress("Shanthi","6th cross Jaynagar"," Banglore","Karnataka",567008),
            new EmployeeAddress("Lakshmi","Ram nagar","Moodbidri","Karnataka",5432009)
    );
    Employee employee3=new Employee(
            new EmployeeBasicDetails(18,"Lakshmi","Naveen","Raj",7654321980L,"diya@gmail.com"),
            new EmployeeAddress("Om","5th cross Rajaji Nagar"," Banglore","Karnataka",576403),
            new EmployeeAddress("Latha","Ram nagar","Moodbidri","Karnataka",5432009)
    );
    List<Employee> employees =new ArrayList<>();
    employees.add(employee1);
    employees.add(employee2);

    when(inputEmployeeDetails.displayBasedOnPinCode(pincode)).thenReturn(employees);
    List<Employee> actual= (List<Employee>) inputEmployeeDetails.displayBasedOnPinCode(pincode);
    verify(inputEmployeeDetails).displayBasedOnPinCode(pincode);
    assertSame(2,actual.size());
     assertEquals(employees.get(0).getEmployeeBasicDetails().getEmployeeID(),actual.get(0).getEmployeeBasicDetails().getEmployeeID());
     assertEquals(employees.get(1).getEmployeeBasicDetails().getEmployeeID(),actual.get(1).getEmployeeBasicDetails().getEmployeeID());
}

@Test
    public void testEmail(){
    String email="example@domain.com";//example@domaincom||example@domain..com||example@domain_com||example@domain.||@domain.com||example@domain.c(all fails)
    boolean isValid=validation.isEmailValid(email);
    assertTrue(isValid);
}

@Test
public void testAll(){
    Employee employee1=new Employee(
            new EmployeeBasicDetails(16,"Diya","Shekar","Shetty",9812345679L,"diya@gmail.com"),
            new EmployeeAddress("Shanthi","6th cross Jaynagar"," Banglore","Karnataka",567008),
            new EmployeeAddress("Lakshmi","Ram nagar","Moodbidri","Karnataka",5432009)
    );
    Employee employee2=new Employee(
            new EmployeeBasicDetails(17,"Shwetha","Shekar","Shetty",9845679432L,"diya@gmail.com"),
            new EmployeeAddress("Shanthi","6th cross Jaynagar"," Banglore","Karnataka",567008),
            new EmployeeAddress("Lakshmi","Ram nagar","Moodbidri","Karnataka",5432009)
    );
    Employee employee3=new Employee(
            new EmployeeBasicDetails(18,"Lakshmi","Naveen","Raj",7654321980L,"diya@gmail.com"),
            new EmployeeAddress("Om","5th cross Rajaji Nagar"," Banglore","Karnataka",576403),
            new EmployeeAddress("Latha","Ram nagar","Moodbidri","Karnataka",5432009)
    );
    List<Employee> employees =new ArrayList<>();
    employees.add(employee1);
    employees.add(employee2);
    employees.add(employee3);
    when(inputEmployeeDetails.displayAll()).thenReturn(employees);
    List<Employee> actual= (List<Employee>) inputEmployeeDetails.displayAll();
    verify(inputEmployeeDetails).displayAll();
    assertSame(3,actual.size());
//    assertEquals(employees.get(0).getEmployeeBasicDetails().getEmployeeID(),actual.get(0).getEmployeeBasicDetails().getEmployeeID());
//    assertEquals(employees.get(1).getEmployeeBasicDetails().getEmployeeID(),actual.get(1).getEmployeeBasicDetails().getEmployeeID());
}

}
