package com.springproject.employee;

import com.employeedao.employee.entity.EmployeeAddress;
import com.employeedao.employee.entity.EmployeeBasicDetails;
import com.employeedao.employee.exception.EmployeeException;
import com.employeedao.employee.services.EmployeeImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class EmployeeApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private EmployeeImplementation.EmployeeRowMapper employeeRowMapper;

    @InjectMocks
    private EmployeeImplementation employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDisplayRequired_EmployeeFound() {
        EmployeeBasicDetails employeeDetails = new EmployeeBasicDetails();
        employeeDetails.setEmployeeID(1);
        employeeDetails.setFirstName("John");
        employeeDetails.setMiddleName("Doe");
        employeeDetails.setLastName("Smith");
        employeeDetails.setPhoneNumber(9876543210L);
        employeeDetails.setEmailID("john@example.com");

        // Mocking EmployeeAddress for temporary and permanent addresses
        EmployeeAddress tempAddress = new EmployeeAddress();
        tempAddress.setHouseName("Temporary House");
        tempAddress.setHouseStreet("Temporary Street");
        tempAddress.setCityName("Temporary City");
        tempAddress.setStateName("Temporary State");
        tempAddress.setPinCode(123456);
        employeeDetails.setTemporaryEmployeeAddress(tempAddress);

        EmployeeAddress permAddress = new EmployeeAddress();
        permAddress.setHouseName("Permanent House");
        permAddress.setHouseStreet("Permanent Street");
        permAddress.setCityName("Permanent City");
        permAddress.setStateName("Permanent State");
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
        assertEquals(employeeDetails,result);
        // Add more assertions as per your requirements
    }

//    @Test
//    public void testDisplayRequired_EmployeeNotFound() {
//        // Mocking behavior of jdbcTemplate.queryForObject to return null
//        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(EmployeeImplementation.EmployeeRowMapper.class)))
//                .thenReturn(null);
//
//        // Call the method to be tested
//        employeeService.displayRequired(/* Provide non-existing employeeID */);
//    }

}
