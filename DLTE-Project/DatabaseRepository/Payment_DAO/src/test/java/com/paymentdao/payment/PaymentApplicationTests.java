package com.paymentdao.payment;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

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

}
