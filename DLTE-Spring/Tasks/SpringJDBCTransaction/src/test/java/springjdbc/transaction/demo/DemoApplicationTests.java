package springjdbc.transaction.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import services.transaction.*;
import springjdbc.transaction.demo.configs.SoapPhase;
import springjdbc.transaction.demo.dao.Transaction;
import springjdbc.transaction.demo.dao.TransactionServices;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DemoApplicationTests {
@MockBean
    private TransactionServices transactionServices;
@InjectMocks
    private SoapPhase soapPhase;

@Test
public void testAddNew() throws DatatypeConfigurationException {
    Transaction transaction1=new Transaction(12345L,new Date("2024/3/30"),"Eeksha","Divija",2000,"Friend");
    Transaction transaction2=new Transaction(12346L,new Date("2024/3/30"),"Eeksha","Arundathi",2000,"Friend");
    when(transactionServices.apiSave(any(Transaction.class))).thenReturn(transaction1);

    NewTransactionRequest newTransaction=new NewTransactionRequest();
    services.transaction.Transaction transaction=new services.transaction.Transaction();
    transaction.setTransactionId(123456L);
    LocalDate date = LocalDate.of(2024, 3, 31);
    XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
    transaction.setTransactionDate(xmlGregorianCalendar);
    transaction.setTransactionBy("Eeksha");
    transaction.setTransactionTo("Divija");
    transaction.setTransactionAmount(2000);
    transaction.setTransactionRemarks("Friend");
    newTransaction.setTransaction(transaction);
    NewTransactionResponse response=soapPhase.addNewTransaction(newTransaction);
    assertTrue(transaction1.getTransactionId().equals(response.getTransaction().getTransactionId()));
}

@Test
public void testFindBySender(){
    Transaction transaction1=new Transaction(12345L,new Date("2024/3/30"),"Eeksha","Divija",2000,"Friend");
    Transaction transaction2=new Transaction(12346L,new Date("2024/3/30"),"Eeksha","Arundathi",2000,"Friend");
    List<Transaction> expectedTransaction= Stream.of(transaction1,transaction2).collect(Collectors.toList());
    when(transactionServices.apiFindBySender(anyString())).thenReturn(expectedTransaction);
    FilterBySenderRequest filterBySenderRequest=new FilterBySenderRequest();
    filterBySenderRequest.setSender("Eeksha");
    FilterBySenderResponse filterBySenderResponse=soapPhase.filterBySender(filterBySenderRequest);
    assertEquals(transaction1.getTransactionId(),filterBySenderResponse.getTransaction().get(0).getTransactionId());
}

    @Test
    public void testFindByReceiver(){
        Transaction transaction1=new Transaction(12345L,new Date("2024/3/30"),"Eeksha","Divija",2000,"Friend");
        Transaction transaction2=new Transaction(12346L,new Date("2024/3/30"),"Eeksha","Arundathi",2000,"Friend");
        List<Transaction> expectedTransaction= Stream.of(transaction1).collect(Collectors.toList());
        when(transactionServices.apiFindByReceiver(anyString())).thenReturn(expectedTransaction);
        FilterByReceiverRequest filterByReceiverRequest=new FilterByReceiverRequest();
        filterByReceiverRequest.setReceiver("Divija");
        FilterByReceiverResponse filterByReceiverResponse=soapPhase.filterByReceiver(filterByReceiverRequest);
        assertEquals(transaction1.getTransactionId(),filterByReceiverResponse.getTransaction().get(0).getTransactionId());
    }


    @Test
    public void testFindByAmount(){
       Transaction transaction1=new Transaction( 12345L,new Date("2024/3/30"),"Eeksha","Divija",2000,"Friend");
        Transaction transaction2=new Transaction(12346L,new Date("2024/3/30"),"Eeksha","Arundathi",2000,"Friend");
        List<Transaction> expectedTransaction= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        when(transactionServices.apiFindByAmount(anyInt())).thenReturn(expectedTransaction);
        FilterByAmountRequest filterByAmountRequest=new FilterByAmountRequest();
        filterByAmountRequest.setAmount(2000);
        FilterByAmountResponse filterByAmountResponse=soapPhase.filterByAmount(filterByAmountRequest);
        assertEquals(transaction1.getTransactionId(),filterByAmountResponse.getTransaction().get(0).getTransactionId());
    }

    @Test
    public void testUpdate() throws DatatypeConfigurationException {
       // Transaction actualTransaction=new Transaction( 12345L,new Date("2024/3/30"),"Eeksha","Spandana",2000,"Friend");
        Transaction transaction1=new Transaction( 12345L,new Date("2024/3/30"),"Eeksha","Spandana",2000,"Family");
        List<Transaction> expectedTransaction= Stream.of(transaction1).collect(Collectors.toList());
        when(transactionServices.updateTransaction(any(Transaction.class))).thenReturn(transaction1);
        UpdateByRemarksRequest updateByRemarksRequest=new UpdateByRemarksRequest();
        services.transaction.Transaction transaction=new services.transaction.Transaction();
        transaction.setTransactionId(123456);
        LocalDate date = LocalDate.of(2024, 3, 31);
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
        transaction.setTransactionDate(xmlGregorianCalendar);
        transaction.setTransactionBy("Eeksha");
        transaction.setTransactionTo("Spandana");
        transaction.setTransactionAmount(2000);
        transaction.setTransactionRemarks("Friend");
        updateByRemarksRequest.setTransaction(transaction);
        UpdateByRemarksResponse updateByRemarksResponse=soapPhase.updateByRemarks(updateByRemarksRequest);
        assertTrue(transaction1.getTransactionId().equals(updateByRemarksResponse.getTransaction().getTransactionId()));

    }

    @Test
    public void testDelete() throws DatatypeConfigurationException {
    when(transactionServices.deleteTransaction(any(),any())).thenReturn("Transaction deleted");
    DeleteByRangeOfDatesRequest deleteByRangeOfDatesRequest=new DeleteByRangeOfDatesRequest();
        LocalDate sDate = LocalDate.of(2024, 3, 31);
        XMLGregorianCalendar startDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(sDate.toString());
        LocalDate eDate = LocalDate.of(2024, 3, 31);
        XMLGregorianCalendar endDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(eDate.toString());
        deleteByRangeOfDatesRequest.setStartDate(startDate);
        deleteByRangeOfDatesRequest.setEndDate(endDate);
       DeleteByRangeOfDatesResponse deleteByRangeOfDatesResponse=soapPhase.deleteBasedOnDates(deleteByRangeOfDatesRequest);
        assertTrue(deleteByRangeOfDatesResponse.getServiceStatus().getMessage().equals("Transaction deleted"));
    }
}
