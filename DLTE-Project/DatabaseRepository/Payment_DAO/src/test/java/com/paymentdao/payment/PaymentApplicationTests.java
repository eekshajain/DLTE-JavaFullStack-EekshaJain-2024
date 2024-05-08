package com.paymentdao.payment;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.entity.Transaction;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.exceptions.TransactionException;
import com.paymentdao.payment.security.MyBankUsersServices;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PaymentApplicationTests {
@InjectMocks
MyBankUsersServices myBankUsersServices;
@Mock
    JdbcTemplate jdbcTemplate;
@InjectMocks
PaymentTransferImplementation paymentTransferImplementation;
    List<Payee> payees;


@BeforeEach
void setUp(){
    Payee payee1 = new Payee();
    payee1.setPayeeId(101);
    payee1.setSenderAccountNumber(213456789654L);
    payee1.setPayeeAccountNumber(543212345678L);
    payee1.setPayeeName("Eeksha");

    Payee payee2 = new Payee();
    payee2.setPayeeId(102);
    payee2.setSenderAccountNumber(765423123564L);
    payee2.setPayeeAccountNumber(765432345678L);
    payee2.setPayeeName("Divija");

    Payee payee3 = new Payee();
    payee3.setPayeeId(103);
    payee3.setSenderAccountNumber(213456789654L);
    payee3.setPayeeAccountNumber(987654321234L);
    payee3.setPayeeName("Arundhathi");

    Payee payee4 = new Payee();
    payee4.setPayeeId(104);
    payee4.setSenderAccountNumber(765423123564L);
    payee4.setPayeeAccountNumber(543567543456L);
    payee4.setPayeeName("Anu");
    payees=Stream.of(payee1,payee3).collect(Collectors.toList());
}

    @Test
    public void testTransactionValidation_ValidTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("NEFT");
        transaction.setTransactionFrom(123456789012L);
        transaction.setTransactionTo(987654321098L);
        transaction.setTransactionAmount(100.0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        assertEquals(0, violations.size(), "Valid transaction should have no violations");
    }

    @Test
    public void testTransactionValidation_InvalidTransactionType() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("invalidType");
        transaction.setTransactionFrom(123456789012L);
        transaction.setTransactionTo(987654321098L);
        transaction.setTransactionAmount(100.0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        assertEquals(1, violations.size(), "Transaction with invalid type should have one violation");
    }

    @Test
    public void testTransactionValidation_InvalidTransactionFrom() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("neft");
        transaction.setTransactionFrom(12345678012L);
        transaction.setTransactionTo(987654321098L);
        transaction.setTransactionAmount(100.0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        assertEquals(1, violations.size(), "Transaction with invalid size of sender account number should have one violation");
    }

    @Test
    public void testTransactionValidation_InvalidTransactionTo() {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("rtgs");
        transaction.setTransactionFrom(123456789012L);
        transaction.setTransactionTo(987654328L);
        transaction.setTransactionAmount(100.0);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

        assertEquals(1, violations.size(), "Transaction with invalid size of payee account number should have one violation");
    }

    @Test
    void testFindAllBasedOnAccount()  {
        when(jdbcTemplate.query(eq("select * from MYBANK_APP_Payee where sender_account_number=?"),
                eq(new Object[]{213456789654L}),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);

        // Call the method under test
        List<Payee> actualList = paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(213456789654L);

        // Assert that the actual list contains the expected payee account number
        assertEquals(543212345678L, actualList.get(0).getPayeeAccountNumber());
        assertEquals(987654321234L,actualList.get(1).getPayeeAccountNumber());
        assertEquals(2,actualList.size());
    }

    @Test
    void testFindAllBasedOnAccountFail()  {
        when(jdbcTemplate.query(eq("select * from MYBANK_APP_Payee where sender_account_number=?"),
                eq(new Object[]{213456789654L}),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);
        // Call the method under test
        List<Payee> actualList = paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(213456789654L);

        // Assert that the actual list contains the expected payee account number
        assertNotEquals(987654321234L, actualList.get(0).getPayeeAccountNumber());
    }

    @Test
    public void testFindAllPayeeBasedOnAccountNumber_PayeeException() {
        when(jdbcTemplate.query(any(String.class), any(Object[].class), any(PaymentTransferImplementation.PayeeMapper.class)))
                .thenReturn(Collections.emptyList());

        // Asserting that the PayeeException is thrown when the method is called with any account number
        assertThrows(PayeeException.class, () -> paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(123456789L));
    }
  @Test
    public void testProcessTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(123456789);
        transaction.setTransactionTo(987654321);
        transaction.setTransactionType("transfer");
        transaction.setTransactionAmount(100.0);
        when(jdbcTemplate.update(eq("CALL ADD_NEW_TRANSACTIONS(?, ?, ?, ?)"), any(), any(), any(), any())).thenReturn(1);
        Transaction result = paymentTransferImplementation.processTransaction(transaction);
        assertEquals(transaction, result);
    }


    @Test
    void testProcessTransaction_InsufficientBalance() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(12345678901L);
        transaction.setTransactionTo(12345667897L);
        transaction.setTransactionType("transfer");
        transaction.setTransactionAmount(100.0);

        // Mock procedure call to throw exception for insufficient balance
        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(
                eq("CALL ADD_NEW_TRANSACTIONS(?, ?, ?, ?)"),
                eq(transaction.getTransactionFrom()),
                eq(transaction.getTransactionTo()),
                eq(transaction.getTransactionType()),
                eq(transaction.getTransactionAmount())
        );
        // Perform the transaction and verify the exception
        assertThrows(TransactionException.class, () -> paymentTransferImplementation.processTransaction(transaction));
    }

    @Test
    void testProcessTransaction_PayeeNotFound() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(12345678901L);
        transaction.setTransactionTo(12345667897L);
        transaction.setTransactionType("transfer");
        transaction.setTransactionAmount(100.0);

        // Mock procedure call to throw exception for insufficient balance
        doThrow(new DataAccessException("ORA-20002") {}).when(jdbcTemplate).update(
                eq("CALL ADD_NEW_TRANSACTIONS(?, ?, ?, ?)"),
                eq(transaction.getTransactionFrom()),
                eq(transaction.getTransactionTo()),
                eq(transaction.getTransactionType()),
                eq(transaction.getTransactionAmount())
        );
        assertThrows(TransactionException.class, () -> paymentTransferImplementation.processTransaction(transaction));
    }


    @Test
    void testProcessTransaction_RTGSAmount() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(12345678901L);
        transaction.setTransactionTo(12345667897L);
        transaction.setTransactionType("transfer");
        transaction.setTransactionAmount(100.0);

        // Mock procedure call to throw exception for insufficient balance
        doThrow(new DataAccessException("ORA-20003") {}).when(jdbcTemplate).update(
                eq("CALL ADD_NEW_TRANSACTIONS(?, ?, ?, ?)"),
                eq(transaction.getTransactionFrom()),
                eq(transaction.getTransactionTo()),
                eq(transaction.getTransactionType()),
                eq(transaction.getTransactionAmount())
        );
        assertThrows(TransactionException.class, () -> paymentTransferImplementation.processTransaction(transaction));
    }


    @Test
    void testProcessTransaction_SenderInactive() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(12345678901L);
        transaction.setTransactionTo(12345667897L);
        transaction.setTransactionType("transfer");
        transaction.setTransactionAmount(100.0);

        // Mock procedure call to throw exception for insufficient balance
        doThrow(new DataAccessException("ORA-20004") {}).when(jdbcTemplate).update(
                eq("CALL ADD_NEW_TRANSACTIONS(?, ?, ?, ?)"),
                eq(transaction.getTransactionFrom()),
                eq(transaction.getTransactionTo()),
                eq(transaction.getTransactionType()),
                eq(transaction.getTransactionAmount())
        );
        // Perform the transaction and verify the exception
        assertThrows(TransactionException.class, () -> paymentTransferImplementation.processTransaction(transaction));
    }


    @Test
    public void testPasswordMatch() {
        MyBankUsersServices myBankUsersServices = mock(MyBankUsersServices.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = "Diya";
        String rawPassword = "diya123";
        String encodedPassword =passwordEncoder.encode(rawPassword);
        // Configure mock behavior
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(encodedPassword);
        when(myBankUsersServices.loadUserByUsername(username))
                .thenReturn(customer);
        // Invoke the authentication process
        UserDetails userDetails = myBankUsersServices.loadUserByUsername(username);

        String enteredPassword="diya123";
        // Verify the result
        assertTrue(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));
    }


    @Test
    public void testPasswordNoMatch() {
        MyBankUsersServices myBankUsersServices = mock(MyBankUsersServices.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Setup test data
        String username = "Diya";
        String rawPassword = "diya123";
        String encodedPassword =passwordEncoder.encode(rawPassword);
        // Configure mock behavior
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(encodedPassword);
        when(myBankUsersServices.loadUserByUsername(username))
                .thenReturn(customer);
        // Invoke the authentication process
        UserDetails userDetails = myBankUsersServices.loadUserByUsername(username);

        String enteredPassword="diya13";
        // Verify the result
        assertFalse(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));
    }


    @Test
    public void testRowMapper() throws SQLException{
    ResultSet rs = Mockito.mock(ResultSet.class);
    Mockito.when(rs.getInt(1)).thenReturn(100);
    Mockito.when(rs.getLong(2)).thenReturn(123456728912L);
    Mockito.when(rs.getLong("payee_account_number")).thenReturn(987654231425L);
    Mockito.when(rs.getString("payee_name")).thenReturn("Dhanu");

    PaymentTransferImplementation.PayeeMapper mapper=new PaymentTransferImplementation.PayeeMapper();

    Payee payee=mapper.mapRow(rs,1);
        assertEquals(100, payee.getPayeeId());
        assertEquals(123456728912L, payee.getSenderAccountNumber());
        assertEquals(987654231425L, payee.getPayeeAccountNumber());
        assertEquals("Dhanu", payee.getPayeeName());

    }



    @Test
    void testSigningUp() {
     //   Customer customer = new Customer(123,"sanath","sringeri","active",8745213698L,"san","san123",1);
        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("Eeksha");
        customer.setCustomerAddress("Bedra");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(9875633271L);
        customer.setAttempts(1);
        customer.setUsername("eeku");
        customer.setPassword("eeksha123");
        when(jdbcTemplate.update(anyString(),eq(customer.getCustomerName()),eq(customer.getCustomerAddress()),eq(customer.getCustomerContact()),eq(customer.getUsername()),eq(customer.getPassword()),eq(customer.getCustomerStatus()),eq(customer.getAttempts()))).thenReturn(1);

        Customer result = myBankUsersServices.signUp(customer);

        assertEquals(customer.getCustomerId()+customer.getAttempts(),result.getCustomerId()+customer.getAttempts());
        assertEquals(customer, result);
    }

    @Test
    void testSigningUpUserDetails() {
        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("Eeksha");
        customer.setCustomerAddress("Bedra");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(9875633271L);
        customer.setAttempts(1);
        customer.setUsername("eeku");
        customer.setPassword("eeksha123");
        when(jdbcTemplate.update(anyString(),eq(customer.getCustomerName()),eq(customer.getCustomerAddress()),eq(customer.getCustomerContact()),eq(customer.getUsername()),eq(customer.getPassword()),eq(customer.getCustomerStatus()),eq(customer.getAttempts()))).thenReturn(1);

        Customer result = myBankUsersServices.signUp(customer);

        assertEquals(customer.getAuthorities(),result.getAuthorities());
        assertEquals(customer.isAccountNonExpired(),result.isAccountNonExpired());
        assertEquals(customer.isAccountNonLocked(),result.isAccountNonLocked());
        assertEquals(customer.isCredentialsNonExpired(),result.isCredentialsNonExpired());
        assertEquals(customer.isEnabled(),result.isEnabled());
    }

    @Test
    void testUpdateAttempts() {
        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("Eeksha");
        customer.setCustomerAddress("Bedra");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(9875633271L);
        customer.setAttempts(1);
        customer.setUsername("eeku");
        customer.setPassword("eeksha123");

        when(jdbcTemplate.update(anyString(), eq(customer.getAttempts()),eq(customer.getUsername()))).thenReturn(1);

        assertDoesNotThrow(() -> {
            myBankUsersServices.updateAttempts(customer);
        });

        verify(jdbcTemplate, times(1)).update(anyString(), eq(customer.getAttempts()),eq(customer.getUsername()));

    }


    @Test
    void testUpdateStatus() {
        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("Eeksha");
        customer.setCustomerAddress("Bedra");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(9875633271L);
        customer.setAttempts(1);
        customer.setUsername("eeku");
        customer.setPassword("eeksha123");

        when(jdbcTemplate.update(anyString(),eq(customer.getUsername()))).thenReturn(1);

        assertDoesNotThrow(() -> {
            myBankUsersServices.updateStatus(customer);
        });

        verify(jdbcTemplate, times(1)).update(anyString(),eq(customer.getUsername()));
    }

    @Test
    public void testGetCustomerName(){
        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("Eeksha");
        customer.setCustomerAddress("Bedra");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(9875633271L);
        customer.setAttempts(1);
        customer.setUsername("eeku");
        customer.setPassword("eeksha123");
        String sql = "SELECT c.CUSTOMER_NAME FROM mybank_app_customer c WHERE c.username =  ?";
        // Stub the jdbcTemplate.queryForObject() method
        when(jdbcTemplate.queryForObject(eq(sql), any(Object[].class), eq(String.class)))
                .thenReturn("Eeksha");
        String name=myBankUsersServices.getCustomerName("eeku");
        assertEquals("Eeksha",name);
    }

    @Test
    public void testGetCustomerNameNotSame(){
        Customer customer = new Customer();
        customer.setCustomerId(123);
        customer.setCustomerName("Eeksha");
        customer.setCustomerAddress("Bedra");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(9875633271L);
        customer.setAttempts(1);
        customer.setUsername("eeku");
        customer.setPassword("eeksha123");
        String sql = "SELECT c.CUSTOMER_NAME FROM mybank_app_customer c WHERE c.username =  ?";
        // Stub the jdbcTemplate.queryForObject() method
        when(jdbcTemplate.queryForObject(eq(sql), any(Object[].class), eq(String.class)))
                .thenReturn("Eeksha");
        String name=myBankUsersServices.getCustomerName("eeku");
        assertNotEquals("Eeksh",name);
    }

    @Test
    void testFindByUsernameNotExistingCustomer() {
       Customer customer1=new Customer();
       customer1.setCustomerId(123);
       customer1.setCustomerName("Diya");
       customer1.setCustomerAddress("Moodbidri");
       customer1.setCustomerContact(8736251627L);
       customer1.setCustomerStatus("active");
       customer1.setUsername("diya12");
       customer1.setPassword("diya123");
       customer1.setAttempts(1);
        Customer customer2=new Customer();
        customer2.setCustomerId(147);
        customer2.setCustomerName("Anand");
        customer2.setCustomerAddress("manglore");
        customer2.setCustomerStatus("active");
        customer2.setCustomerContact(8364526183L);
        customer2.setUsername("anand19");
        customer2.setPassword("anand123");
        customer2.setAttempts(1);
        assertThrows(UsernameNotFoundException.class, () -> myBankUsersServices.findByUsernameCustomerStream("eek"));
    }

    @Test
    void testRetrieveBalance_AccountFound() {
        Long senderAccount = 123454356271L;
        double expectedBalance = 1000.0;

        // Mocking the expected result
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(double.class)))
                .thenReturn(expectedBalance);
        double actualBalance = paymentTransferImplementation.retrieveBalance(senderAccount);

        // Verifying interactions
        verify(jdbcTemplate).queryForObject(anyString(), any(Object[].class), eq(double.class));

        // Asserting the result
        assertEquals(expectedBalance, actualBalance);
    }


    }
