package springjdbc.transaction.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springjdbc.transaction.demo.configs.SoapPhase;
import springjdbc.transaction.demo.dao.Transaction;
import springjdbc.transaction.demo.dao.TransactionServices;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Result;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DAOTest {
    @MockBean
    TransactionServices transactionServices;
    @InjectMocks
    SoapPhase soapPhase;
    @Mock
    private JdbcTemplate jdbcTemplate;


    @Test
    void testNewTransaction(){
        Transaction transaction = new Transaction(224555L, new Date(2024, 2, 2), "Arundhathi", "Avinash", 80000, "Bills");

        // Mock the behavior of jdbcTemplate's update method to return the number of rows affected
        when(jdbcTemplate.update(
                eq("INSERT INTO transactions_table VALUES (?, ?, ?, ?, ?, ?)"),
                any(Object[].class),
                any(int[].class)))
                .thenReturn(1); // Assuming 1 row is inserted

        // Call the method under test
        Transaction result = transactionServices.newTransactions(transaction);

        // Assert the result
        assertEquals(transaction, result);
    }
    @Test
    public void testBySender() {
        Transaction transactions1 = new Transaction(123456L, new Date(2024, 03, 31), "Eeksha", "MAX", 500, "Bills");
        Transaction transactions2 = new Transaction(123457L, new Date(2024, 03, 31), "Eeksha", "Yuthika", 700, "Family");

        List<Transaction> transactionsList = Stream.of(transactions1, transactions2).collect(Collectors.toList());
        // when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);
        when(jdbcTemplate.query(eq("select * from transactions_table where transaction_by=?"),
                eq(new Object[]{"Eeksha"}),
                any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);
        List<Transaction> result = transactionServices.apiFindBySender("Eeksha");
        assertNotNull(result);
        assertEquals(transactionsList, result);
    }

    @Test
    public void testByReceiver() {
        Transaction transactions1 = new Transaction(123456L, new Date(2024, 03, 31), "Eeksha", "MAX", 500, "Bills");
        Transaction transactions2 = new Transaction(123457L, new Date(2024, 03, 31), "Eeksha", "Yuthika", 700, "Family");

        List<Transaction> transactionsList = Stream.of(transactions2).collect(Collectors.toList());
        // when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);
        when(jdbcTemplate.query(eq("select * from transactions_table where transaction_to=?"),
                eq(new Object[]{"Yuthiks"}),
                any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);
        List<Transaction> result = transactionServices.apiFindByReceiver("Yuthika");
        assertEquals(transactionsList.get(0).getTransactionAmount(), result.get(0).getTransactionAmount());
    }

    @Test
    public void testByAmount() {
        Transaction transactions1 = new Transaction(123456L, new Date(2024, 03, 31), "Eeksha", "Nanu", 700, "Bills");
        Transaction transactions2 = new Transaction(123457L, new Date(2024, 03, 31), "Eeksha", "Yuthika", 700, "Family");

        List<Transaction> transactionsList = Stream.of(transactions1, transactions2).collect(Collectors.toList());
        // when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);
        when(jdbcTemplate.query(eq("select * from transactions_table where transaction_amount=?"),
                eq(new Object[]{700}),
                any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);
        List<Transaction> result = transactionServices.apiFindByAmount(700);
        assertEquals(transactionsList.get(0).getTransactionTo(), result.get(0).getTransactionTo());
    }
    @Test
    public void testByUpdate() {
        Transaction transactions1 = new Transaction(123456L, new Date(2024, 03, 31), "Eeksha", "Nandu", 700, "Bills");
        Transaction transactions2 = new Transaction(123456L, new Date(2024, 03, 31), "Eeksha", "Nandu", 700, "Bills");

        List<Transaction> transactionsList = Stream.of(transactions1, transactions2).collect(Collectors.toList());
        // when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);
        when(jdbcTemplate.update(eq("update transactions_table set transaction_remarks=? where transaction_id=?"),
                eq(new Object[]{"Friend",123456L}),
                any(BeanPropertyRowMapper.class))).thenReturn(1);
        Transaction result = transactionServices.updateTransaction(transactions2);
        assertEquals("Friend", result.getTransactionRemarks());
    }

    @Test
    void testRemoveTransactionBetweenDates() throws DatatypeConfigurationException {
        XMLGregorianCalendar startDate = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar("2024-03-31");
        XMLGregorianCalendar endDate = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar("2024-04-07");

        // Mock the behavior of jdbcTemplate's update method to return the number of rows affected
        when(jdbcTemplate.update(eq("delete from transactions_table where transaction_date between ? and ?"),
                any(Object[].class)))
                .thenReturn(1); // Assuming 1 row is deleted

        // Call the method under test
        String result = transactionServices.deleteTransaction(startDate, endDate);

        // Assert the result
        assertEquals("Transaction deleted", result);
    }
}
