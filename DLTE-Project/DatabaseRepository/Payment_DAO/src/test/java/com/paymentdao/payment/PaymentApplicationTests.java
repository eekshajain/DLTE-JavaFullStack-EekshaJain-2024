package com.paymentdao.payment;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.entity.Transaction;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.exceptions.TransactionException;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
    public void testProcessTransaction() {
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(123456789);
        transaction.setTransactionTo(987654321);
        transaction.setTransactionType("transfer");
        transaction.setTransactionAmount(100.0);

        when(jdbcTemplate.update(eq("CALL ADD_NEW_TRANSACTIONS(?, ?, ?, ?)"), any(), any(), any(), any())).thenReturn(1);

        // Act
        Transaction result = paymentTransferImplementation.processTransaction(transaction);

        // Assert
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

}
