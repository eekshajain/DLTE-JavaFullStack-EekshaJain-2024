package com.paymentdao.payment;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PaymentApplicationTests {

@Mock
    JdbcTemplate jdbcTemplate;
@InjectMocks
PaymentTransferImplementation paymentTransferImplementation;

@Mock
    SimpleJdbcCall simpleJdbcCall;
    @Mock
    private DataSource dataSource;
//    @Autowired
//    public void PaymentTransferImplementation(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//    public PaymentApplicationTests(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
    @Test
    void testFindAllBasedOnAccount()  {
        Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
       List<Payee> payees=Stream.of(payee1,payee3).collect(Collectors.toList());
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

    //@Test
    void testFindAllBasedOnAccountFail()  {
        Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
        List<Payee> payees=Stream.of(payee1,payee3).collect(Collectors.toList());

        when(jdbcTemplate.query(eq("select * from MYBANK_APP_Payee where sender_account_number=?"),
                eq(new Object[]{213456789654L}),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);
        // Call the method under test
        List<Payee> actualList = paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(213456789654L);

        // Assert that the actual list contains the expected payee account number
        assertEquals(987654321234L, actualList.get(0).getPayeeAccountNumber());
    }

    @Test
    void testFindAll()  {
        Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
        List<Payee> payees=Stream.of(payee1,payee2,payee3,payee4).collect(Collectors.toList());
        when(jdbcTemplate.query(eq("select * from MYBANK_APP_Payee"),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);
//        when(jdbcTemplate.query(anyString(),any(Object[].class),
//                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);
        // Call the method under test
        List<Payee> actualList = paymentTransferImplementation.findAllPayee();

        // Assert that the actual list contains the expected payee account number
        assertEquals(543212345678L, actualList.get(0).getPayeeAccountNumber());
        assertEquals(765432345678L, actualList.get(1).getPayeeAccountNumber());
        assertEquals(987654321234L, actualList.get(2).getPayeeAccountNumber());
        assertEquals(543567543456L, actualList.get(3).getPayeeAccountNumber());
    }

   // @Test
    void testFindAllFail()  {
        Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
        List<Payee> payees=Stream.of(payee1,payee2,payee3,payee4).collect(Collectors.toList());
        when(jdbcTemplate.query(eq("select * from MYBANK_APP_Payee"),
                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);
//        when(jdbcTemplate.query(anyString(),any(Object[].class),
//                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);
        // Call the method under test
        List<Payee> actualList = paymentTransferImplementation.findAllPayee();

        // Assert that the actual list contains the expected payee account number
        assertEquals(543212345678L, actualList.get(3).getPayeeAccountNumber());
        assertEquals(765432345678L, actualList.get(2).getPayeeAccountNumber());
        assertEquals(987654321234L, actualList.get(1).getPayeeAccountNumber());
        assertEquals(543567543456L, actualList.get(0).getPayeeAccountNumber());
    }

    @Test
    public void testProcessTransaction() {
        long senderAccountNumber = 123456789;
        long payeeAccountNumber = 987654321;
        String transactionType = "transfer";
        double transactionAmount = 100.0;

        // Call the method under test
        paymentTransferImplementation.processTransaction(senderAccountNumber, payeeAccountNumber, transactionType, transactionAmount);

        // Verify that jdbcTemplate.update() was called with the correct arguments
        verify(jdbcTemplate).update(
                eq("CALL ADD_NEW_TRANSACTIONS(?, ?, ?, ?)"),
                eq(senderAccountNumber),
                eq(payeeAccountNumber),
                eq(transactionType),
                eq(transactionAmount)
        );
    }

//    @Test
//    void testProcessTransactionException() {
//        // Mock JdbcTemplate
//        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
//
//        // Create the PaymentTransferImplementation instance
//        PaymentTransferImplementation paymentTransferImplementation = new PaymentTransferImplementation(jdbcTemplate);
//
//        // Test data
//        long senderAccountNumber = 123456789;
//        long payeeAccountNumber = 987654321;
//        String transactionType = "transfer";
//        double transactionAmount = 100.0;
//
//        // Verify that jdbcTemplate.update() was called with the correct arguments
//        assertDoesNotThrow(() -> {
//            paymentTransferImplementation.processTransaction(senderAccountNumber, payeeAccountNumber, transactionType, transactionAmount);
//
//            verify(jdbcTemplate).update(
//                    eq("CALL ADD_NEW_TRANSACTIONS(?, ?, ?, ?)"),
//                    eq(senderAccountNumber),
//                    eq(payeeAccountNumber),
//                    eq(transactionType),
//                    eq(transactionAmount)
//            );
//
//            // Additional assertion to ensure no other methods were called on the JdbcTemplate
//            verifyNoMoreInteractions(jdbcTemplate);
//        });
//
//        // Test exception handling for insufficient balance
//        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenThrow(new DataAccessException.("ORA-20001"){
//
//        });
//        assertThrows(PayeeException.class, () ->
//                paymentTransferImplementation.processTransaction(senderAccountNumber, payeeAccountNumber, transactionType, transactionAmount));
//
//        // Test exception handling for no payee found
//        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenThrow(new DataAccessException("ORA-20002"){});
//        assertThrows(PayeeException.class, () ->
//                paymentTransferImplementation.processTransaction(senderAccountNumber, payeeAccountNumber, transactionType, transactionAmount));
//
//        // Test exception handling for sender inactive
//        when(jdbcTemplate.update(anyString(), any(), any(), any(), any())).thenThrow(new DataAccessException("ORA-20003"){});
//        assertThrows(PayeeException.class, () ->
//                paymentTransferImplementation.processTransaction(senderAccountNumber, payeeAccountNumber, transactionType, transactionAmount));
//    }
}
