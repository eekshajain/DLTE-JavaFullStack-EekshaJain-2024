package com.employeedao.employee;

import com.employeedao.employee.entity.EmployeeAddress;
import com.employeedao.employee.entity.EmployeeBasicDetails;
import com.employeedao.employee.exception.EmployeeException;
import com.employeedao.employee.remotes.EmployeeRepository;
import com.employeedao.employee.services.EmployeeImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmployeeTest {


    @Mock
    private ResourceBundle resourceBundle;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private EmployeeImplementation.EmployeeRowMapper employeeRowMapper;

    @InjectMocks
    private EmployeeImplementation employeeService;

//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        when(resourceBundle.getString("no.employee")).thenReturn("No employee with given employee ID");
//    }

    @Test
    public void testDisplayRequired_EmployeeFound() {
        EmployeeBasicDetails employeeDetails = new EmployeeBasicDetails();
        employeeDetails.setEmployeeID(1);
        employeeDetails.setFirstName("Vandana");
        employeeDetails.setMiddleName("Rajesh");
        employeeDetails.setLastName("Wagle");
        employeeDetails.setPhoneNumber(9876543210L);
        employeeDetails.setEmailID("vandanawagle@gmail.com");

        // Mocking EmployeeAddress for temporary and permanent addresses
        EmployeeAddress tempAddress = new EmployeeAddress();
        tempAddress.setHouseName("Flat No 12,Empire Society");
        tempAddress.setHouseStreet("Andheri West");
        tempAddress.setCityName("Mumbai");
        tempAddress.setStateName("Maharastra");
        tempAddress.setPinCode(123456);
        employeeDetails.setTemporaryEmployeeAddress(tempAddress);

        EmployeeAddress permAddress = new EmployeeAddress();
        permAddress.setHouseName("Wagle House,Sai Darshan Society");
        permAddress.setHouseStreet("Bandra West");
        permAddress.setCityName("Mumbai");
        permAddress.setStateName("Maharastra");
        permAddress.setPinCode(654321);
        employeeDetails.setPermanentEmployeeAddress(permAddress);
        // Mocking behavior of jdbcTemplate.queryForObject to return an EmployeeBasicDetails object
        //EmployeeBasicDetails mockEmployeeDetails = new EmployeeBasicDetails();
        // Fill mockEmployeeDetails with necessary parameters
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(EmployeeImplementation.EmployeeRowMapper.class)))
                .thenReturn(employeeDetails);

        // Call the method to be tested
        EmployeeBasicDetails result = employeeService.displayRequired(1);

        // Assertions
        assertEquals(employeeDetails, result);

    }

    @Test
    public void testDisplayRequired_EmployeeNotFound() {
        // Mocking behavior of jdbcTemplate.queryForObject to return null when employee is not found
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(EmployeeImplementation.EmployeeRowMapper.class)))
                .thenReturn(null);

        // Call the method to be tested and expect an exception
        assertThrows(EmployeeException.class, () -> {
            employeeService.displayRequired(2);
        });
    }
}