package org.example;

import org.example.WebServicesDAO;
import org.example.entity.Transaction;
import org.example.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    @Before
    public void setup(){
        webServicesDAOService.getWebServicesDAOPort();
        webServicesDAOService =new WebServicesDAOService();
    }

    @Test
    public void testAll(){
        AccountService accountService = mock(AccountService.class);
        Transaction transaction = new Transaction(new Date(),1234,"Eeksha",45,45000);

        // Mock the behavior of callFindAll method of accountService
        List<Transaction> mockTransactionList = (List<Transaction>) new ArrayList();
        mockTransactionList.add(transaction); // Add your mock transaction here
        when(accountService.callFindAll()).thenReturn(mockTransactionList);

        // Call the method you want to test
        webServicesDAO.withdraw("Eeksha", "1234", 45);
        verify(accountService, times(1)).callFindAll();
    }
    Account account;
    @Test
    public void testWithdraw() {
        account=new Account(123456790L,45679L,"anandi@gmail.com","Anandi",45000,"anandi78","anandi1234");
        String username = "eeksha06";
        String password = "eekshajain06";
        double withdrawAmount = 500;
        double expectedBalance=44500;
        when(accountService.callWithdraw(username,password,withdrawAmount)).thenReturn(expectedBalance);
        double actualBalance = webServicesDAO.withdraw(username, password, withdrawAmount);

        // Verify that callWithdraw method of accountService is called exactly once with the correct arguments
        verify(accountService, times(1)).callWithdraw(username, password, withdrawAmount);

        // Verify the returned balance matches the expected balance
        assertEquals(expectedBalance, actualBalance);
    }
}
