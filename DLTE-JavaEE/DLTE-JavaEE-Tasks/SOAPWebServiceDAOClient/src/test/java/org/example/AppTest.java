package org.example;

import org.example.WebServicesDAO;
import org.example.entity.Transaction;
import org.example.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    @Mock
    AccountService accountService;

    @Mock
    WebServicesDAO webServicesDAO;
    @Mock
    WebServicesDAOService webServicesDAOService;
//    @Before
//    public void setup(){
//        webServicesDAOService.getWebServicesDAOPort();
//        webServicesDAOService =new WebServicesDAOService();
//    }

    @Test
    public void testAll(){
        AccountService accountService = mock(AccountService.class);
        Transaction transaction = new Transaction(new Date(),1234,"Eeksha",45,45000);

        // Mock the behavior of callFindAll method of accountService
        List<Transaction> mockTransactionList = new ArrayList<>();
        mockTransactionList.add(transaction); // Add your mock transaction here
        when(accountService.callFindAll()).thenReturn(mockTransactionList);
        // Call the method you want to test
//       List<Transaction> transactions= (List<Transaction>) webServicesDAO.findAll();
//        verify(accountService, times(1)).callFindAll();
       // org.example.ArrayList actual= webServicesDAO.findAll();
        List<Transaction> actual= (List<Transaction>) webServicesDAO.findAll();
        assertEquals(mockTransactionList,actual);

    }
    Account account;
    @Test
    public void testWithdraw() {
        account=new Account(123456790L,45679L,"anandi@gmail.com","Anandi",45000,"anandi78","anandi1234");
        String username = "anandi78";
        String password = "anandi1234";
        double withdrawAmount = 500;
        double expectedBalance=44500;
        when(accountService.callWithdraw(username,password,withdrawAmount)).thenReturn(expectedBalance);
        double actualBalance = webServicesDAO.withdraw(username, password, withdrawAmount);
        // Verify that callWithdraw method of accountService is called exactly once with the correct arguments
        verify(accountService, times(1)).callWithdraw(username, password, withdrawAmount);
        // Verify the returned balance matches the expected balance
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testFndBy(){
        String username="anandi78";
        Account account=new Account(123456790L,45679L,"anandi@gmail.com","Anandi",45000,"anandi78","anandi1234");
        when(accountService.callFindByUsername(username)).thenReturn(account);
        org.example.Account actual=webServicesDAO.findByUser(username);
        assertEquals(account,actual);
    }
}
