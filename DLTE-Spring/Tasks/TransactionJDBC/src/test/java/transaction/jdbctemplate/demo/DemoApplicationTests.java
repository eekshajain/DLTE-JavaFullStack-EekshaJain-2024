//package transaction.jdbctemplate.demo;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import transaction.jdbctemplate.demo.entity.Transaction;
//import transaction.jdbctemplate.demo.services.TransactionServices;
//
////import java.util.Date;
//import javax.sql.DataSource;
//import java.sql.Date;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class DemoApplicationTests {
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    private TransactionServices transactionServices;
//
//    @Test
//    void testSpringContextLoads() {
//        assertNotNull(transactionServices, "Spring context not loaded properly");
//    }
//
////    @Test
////    void testNewTransaction(){
////        Transaction transaction1=new Transaction(123456L,Date.valueOf("2024-02-25"),"Eeksha","Spandana",400,"Family");
////     //   Transaction transaction2=new Transaction(123457L,new Date("02/03/2024"),"Eeksha","Divija",250,"Friend");
////       // Transaction transaction3=new Transaction(123458L,new Date("02/03/2024"),"Eeksha","Hospital",1000,"Emergency");
////        //verify(transactionServices).apiSave(any(Transaction.class));
////     when(jdbcTemplate.update(anyString(),any(Object[].class))).thenReturn(1);
//////    when(jdbcTemplate.update(
//////                eq("insert into transactions_table values(?,?,?,?,?,?)"),
//////                eq(new Object[]{123456L, Date.valueOf("2024-02-25"), "Eeksha", "Spandana", 400, "Family"})
//////        )).thenReturn(1);
////
////        Transaction transactionActual=transactionServices.apiSave(transaction1);
////        verify(jdbcTemplate).update(anyString(), any(Object[].class));
////
//////        System.out.println(transactionActual.getTransactionBy());
//////        assertEquals(transaction1.getTransactionBy(),transactionActual.getTransactionBy());
////    }
//
//    @Test
//    void testNewTransaction() {
//        Transaction transaction1 = new Transaction(123456L, Date.valueOf("2024-02-25"), "Eeksha", "Spandana", 400, "Family");
//        Transaction transaction2=new Transaction(123457L, Date.valueOf("2024-03-02"),"Eeksha","Divija",400,"Friend");
//        when(jdbcTemplate.update(
//                eq("insert into transactions_table values(?,?,?,?,?,?)"),
//                any(Long.class),
//                any(Date.class),
//                any(String.class),
//                any(String.class),
//                any(Integer.class),
//                any(String.class)
//        )).thenReturn(1);
//        Transaction transactionActual = transactionServices.apiSave(transaction1);
//        System.out.println(transactionActual.getTransactionBy());
//      assertEquals(transaction1.getTransactionBy(),transactionActual.getTransactionBy());
//      assertEquals(transaction2.getTransactionBy(),transactionActual.getTransactionBy());//fails
//
//
//    }
//
//    @Test
//    void testFindBySender(){
//        Transaction transaction1=new Transaction(123456L,Date.valueOf("2024-02-25"),"Eeksha","Spandana",400,"Family");
//      Transaction transaction2=new Transaction(123457L, Date.valueOf("2024-03-02"),"Eeksha","Divija",250,"Friend");
//       Transaction transaction3=new Transaction(123458L,Date.valueOf("2024-02-03"),"Eeksha","Hospital",1000,"Emergency");
//        List<Transaction> expected= Stream.of(transaction1,transaction2,transaction3).collect(Collectors.toList());
//        List<Transaction> notExpected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
//        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
//        List<Transaction> actual=transactionServices.apiFindBySender("Eeksha");
//        assertEquals(expected,actual);
//        assertNotEquals(notExpected,actual);
//        assertNull(expected);//fails
//    }
//
//    @Test
//    void testFindByReceiver(){
//        Transaction transaction1=new Transaction(123456L,Date.valueOf("2024-02-25"),"Eeksha","Spandana",400,"Family");
//        Transaction transaction2=new Transaction(123457L, Date.valueOf("2024-03-02"),"Eeksha","Divija",250,"Friend");
//        Transaction transaction3=new Transaction(123458L,Date.valueOf("2024-02-03"),"Eeksha","Hospital",1000,"Emergency");
//        List<Transaction> expected= Stream.of(transaction1).collect(Collectors.toList());
//        List<Transaction> notExpected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
//        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
//        List<Transaction> actual=transactionServices.apiFindByReceiver("Spandana");
//        assertEquals(expected,actual);
//        assertNotEquals(notExpected,actual);
//        assertNotNull(expected);
//        assertNotNull(actual);//fails as actual is not null
//    }
//
//    @Test
//    void testFindByAmount(){
//        Transaction transaction1=new Transaction(123456L,Date.valueOf("2024-02-25"),"Eeksha","Spandana",400,"Family");
//        Transaction transaction2=new Transaction(123457L, Date.valueOf("2024-03-02"),"Eeksha","Divija",400,"Friend");
//        Transaction transaction3=new Transaction(123458L,Date.valueOf("2024-02-03"),"Eeksha","Hospital",1000,"Emergency");
//        List<Transaction> expected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
//        List<Transaction> notExpected= Stream.of(transaction1).collect(Collectors.toList());
//        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
//        List<Transaction> actual=transactionServices.apiFindByAmount(400);
//        assertEquals(expected,actual);
//        assertNotEquals(notExpected,actual);//fails as both are same
//    }
//}
