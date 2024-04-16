//package com.payment.webservices;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.payment.webservices.controller.PaymentRestController;
//import com.paymentdao.payment.entity.Customer;
//import com.paymentdao.payment.entity.Transaction;
//import com.paymentdao.payment.exceptions.PayeeException;
//import com.paymentdao.payment.remote.PaymentTransferRepository;
//import com.paymentdao.payment.security.MyBankUsers;
//import com.paymentdao.payment.security.MyBankUsersServices;
//import com.paymentdao.payment.service.PaymentTransferImplementation;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class RestEndPoint {
//    @Mock
//    private PaymentTransferRepository paymentTransferRepository;
//    @MockBean
//    private PaymentTransferImplementation paymentService;
//    @InjectMocks
//    PaymentRestController paymentRestController;
//
//    private MockMvc mockMvc;
//
//    @Mock
//    MyBankUsersServices usersServices;
//
//    @BeforeEach
//    void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(paymentRestController).build();
//    }
//
//    @Test
//    public void testNewTransactions_Success() {
//        // Mock data
//        Transaction transaction = new Transaction();
//        transaction.setTransactionFrom(12345678901L);
//        transaction.setTransactionTo(12345667897L);
//        transaction.setTransactionAmount(100.0);
//
//        // Mock authentication
//        Authentication authentication = mock(Authentication.class);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        when(authentication.getName()).thenReturn("testUser");
//
//        // Mock service behavior
//        Customer customer = new Customer();
//        customer.setCustomerId(123);
//        when(usersServices.findByUsernameCustomer("testUser")).thenReturn(customer);
//        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(12345678901L));
//        when(paymentTransferRepository.processTransaction(any(Transaction.class))).thenReturn(transaction);
//
//        // Perform the test
//        ResponseEntity responseEntity = paymentRestController.newTransactions(transaction);
//
//        // Verify the response
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Transaction successful to 12345667897", responseEntity.getBody());
//    }
//
//    @Test
//    public void testNewTransactions_SenderNotFound() {
//        // Mock data
//        Transaction transaction = new Transaction();
//        transaction.setTransactionFrom(12345678901L);
//        transaction.setTransactionTo(12345667897L);
//        transaction.setTransactionAmount(100.0);
//
//        // Mock authentication
//        Authentication authentication = mock(Authentication.class);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        when(authentication.getName()).thenReturn("testUser");
//
//        // Mock service behavior
//        Customer customer = new Customer();
//        customer.setCustomerId(123);
//        when(usersServices.findByUsernameCustomer("testUser")).thenReturn(customer);
//        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Collections.emptyList());
//
//        // Perform the test
//        ResponseEntity responseEntity = paymentRestController.newTransactions(transaction);
//
//        // Verify the response
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//        assertEquals("No account found.Please add new account!", responseEntity.getBody());
//    }
//
//   //@Test
//
//    public void testNewTransactions_SenderNotFounderror() {
//        // Mock data
//        Transaction transaction = new Transaction();
//        transaction.setTransactionFrom(12345678901L);
//        transaction.setTransactionTo(12345667897L);
//        transaction.setTransactionAmount(100.0);
//
//        // Mock authentication
//        Authentication authentication = mock(Authentication.class);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        when(authentication.getName()).thenReturn("testUser");
//
//        // Mock service behavior
//        Customer customer = new Customer();
//        customer.setCustomerId(123);
//        when(usersServices.findByUsernameCustomer("testUser")).thenReturn(customer);
//
//        // Mock sender's account number not found in the list
//        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Arrays.asList(9876543210L, 9876543211L));
//
//        // Perform the test
//        ResponseEntity responseEntity = paymentRestController.newTransactions(transaction);
//
//        // Verify the response
//        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//        assertEquals("No account found for the sender.", responseEntity.getBody());
//    }
//
//}
//
