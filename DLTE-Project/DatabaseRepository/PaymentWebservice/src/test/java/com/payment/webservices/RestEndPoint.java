package com.payment.webservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.webservices.controller.PaymentRestController;
import com.payment.webservices.controller.PaymentSoapPhase;
import com.payment.webservices.mvccontroller.MyBank;
import com.payment.webservices.security.MyBankAppApi;
import com.payment.webservices.security.OfficialsSuccessHandler;
import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.entity.Transaction;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.exceptions.TransactionException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.security.MyBankUsersServices;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import services.payee.FindAllPayeeBasedOnAccountNumberRequest;
import services.payee.FindAllPayeeBasedOnAccountNumberResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestEndPoint {
    @Mock
    private PaymentTransferRepository paymentTransferRepository;
    @InjectMocks
    private PaymentSoapPhase soapPhase;
    @MockBean
    private PaymentTransferImplementation paymentService;
    @InjectMocks
    PaymentRestController paymentRestController;

    List<Payee> payees;
   private ResourceBundle resourceBundle;

    private MockMvc mockMvc;
    private MockMvc mockMvc2;
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;
    @InjectMocks
    private OfficialsSuccessHandler successHandler;

    @InjectMocks
    MyBankAppApi myBankAppApi;
    @Mock
    private PasswordEncoder passwordEncoder;


    @Mock
    MyBankUsersServices usersServices;

    @InjectMocks
    MyBank mvcController;
    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentRestController).build();
        mockMvc2 = MockMvcBuilders.standaloneSetup(mvcController).build();
        resourceBundle = ResourceBundle.getBundle("payment");
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setUp() {
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
        payees= Stream.of(payee1,payee3).collect(Collectors.toList());
    }

    @Test
    public void testFindAllAccountNumber()  {
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("Vandana");

        // Mock service behavior
        Customer customer = new Customer();
        customer.setCustomerId(123);
        when(usersServices.findByUsernameCustomerStream("Vandana")).thenReturn(customer);
        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(213456789654L));

        when(paymentTransferRepository.findAllPayeeBasedOnAccountNumber(213456789654L)).thenReturn(payees);

        FindAllPayeeBasedOnAccountNumberRequest request = new FindAllPayeeBasedOnAccountNumberRequest();
        // passing the entity
        request.setSenderAccount(213456789654L);
        FindAllPayeeBasedOnAccountNumberResponse response = soapPhase.listPayeeBasedOnAccountNumber(request);

        //checking the payeeName in entity as well in response
        assertEquals(payees.get(0).getPayeeAccountNumber(), response.getPayee().get(0).getPayeeAccountNumber());
        // assertEquals(payees.get(0).getPayeeName(), response.getPayee().get(1).getPayeeName());
        // checking the response is success or not
        assertEquals(200, response.getServiceStatus().getStatus());

    }

    @Test
    public void testFindAllAccountNumberFail() throws SQLSyntaxErrorException {
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("Vandana");

        // Mock service behavior
        Customer customer = new Customer();
        customer.setCustomerId(123);
        when(usersServices.findByUsernameCustomerStream("Vandana")).thenReturn(customer);
        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(213456789654L));
        when(paymentTransferRepository.findAllPayeeBasedOnAccountNumber(213456789654L)).thenReturn(payees);
        FindAllPayeeBasedOnAccountNumberRequest request = new FindAllPayeeBasedOnAccountNumberRequest();
        // passing the entity
        request.setSenderAccount(213456789654L);
        FindAllPayeeBasedOnAccountNumberResponse response = soapPhase.listPayeeBasedOnAccountNumber(request);

        assertNotEquals(payees.get(0).getPayeeName(), response.getPayee().get(1).getPayeeName());
    }

    @Test
    public void testFindAllAccountNumberPayeeException() throws SQLSyntaxErrorException {
        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("Vandana");

        // Mock service behavior
        Customer customer = new Customer();
        customer.setCustomerId(123);
        when(usersServices.findByUsernameCustomerStream("Vandana")).thenReturn(customer);
        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(213456789654L));

        // Mock the repository to throw a PayeeException
        when(paymentTransferRepository.findAllPayeeBasedOnAccountNumber(213456789654L)).thenThrow(new PayeeException("No payee found"));

        FindAllPayeeBasedOnAccountNumberRequest request = new FindAllPayeeBasedOnAccountNumberRequest();
        request.setSenderAccount(213456789654L);
        FindAllPayeeBasedOnAccountNumberResponse response = soapPhase.listPayeeBasedOnAccountNumber(request);

        // Verify that the response contains the appropriate error message
        assertEquals("EXC001 :No payee found", response.getServiceStatus().getMessage());
    }


    @Test
    public void testNewTransactions_Success() {
        // Mock data
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(12345678901L);
        transaction.setTransactionTo(12345667897L);
        transaction.setTransactionAmount(100.0);

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mock service behavior
        Customer customer = new Customer();
        customer.setCustomerId(123);
        when(usersServices.findByUsernameCustomerStream("testUser")).thenReturn(customer);
        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(12345678901L));
        when(paymentTransferRepository.processTransaction(any(Transaction.class))).thenReturn(transaction);

        // Perform the test
        ResponseEntity responseEntity = paymentRestController.newTransactions(transaction);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Transaction successful to 12345667897", responseEntity.getBody());
    }

    @Test
    public void testNewTransactions_SenderNotFound() {
        // Mock data
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(12345678901L);
        transaction.setTransactionTo(12345667897L);
        transaction.setTransactionAmount(100.0);

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mock service behavior
        Customer customer = new Customer();
        customer.setCustomerId(123);
        when(usersServices.findByUsernameCustomerStream("testUser")).thenReturn(customer);
        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Collections.emptyList());

        // Perform the test
        ResponseEntity responseEntity = paymentRestController.newTransactions(transaction);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("EXC006 :No account found.Please add new account!", responseEntity.getBody());
    }

   @Test
    public void testNewTransactions_SenderNotFoundError() {
        // Mock data
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(12345678901L);
        transaction.setTransactionTo(12345667897L);
        transaction.setTransactionAmount(100.0);

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mock service behavior
        Customer customer = new Customer();
        customer.setCustomerId(123);
        when(usersServices.findByUsernameCustomerStream("testUser")).thenReturn(customer);
        // Mock sender's account number not found in the list
        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Arrays.asList(9876543210L, 9876543211L));
        // Perform the test
        ResponseEntity responseEntity = paymentRestController.newTransactions(transaction);
        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("EXC006 :No account found.Please add new account!", responseEntity.getBody());
    }

    @Test
    public void testEndPoint() throws Exception {
        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("Eeksha");

        // Mock service behavior
        Customer customer = new Customer();
        customer.setCustomerId(123);
        when(usersServices.findByUsernameCustomerStream("Eeksha")).thenReturn(customer);
        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(123456789011L));
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(123456789011L);
        transaction.setTransactionTo(123456678971L);
        transaction.setTransactionAmount(100.0);
        transaction.setTransactionType("imps");

        // Define the expected return value from the service method
        Transaction processedTransaction = new Transaction();
        processedTransaction.setTransactionFrom(transaction.getTransactionFrom());
        processedTransaction.setTransactionTo(transaction.getTransactionTo());
        processedTransaction.setTransactionAmount(transaction.getTransactionAmount());
        processedTransaction.setTransactionType(transaction.getTransactionType());

        // Mock the service method call
        when(paymentService.processTransaction(any(Transaction.class))).thenReturn(processedTransaction);
        System.out.println(asJsonString(transaction));
        // Perform the POST request
        mockMvc.perform(post("/transactions/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(transaction)))
                .andExpect(status().isOk());
    }





    private static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testNewTransactions_InsufficientBalance() {
        // Mock data
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(12345678901L);
        transaction.setTransactionTo(12345667897L);
        transaction.setTransactionAmount(100.0);

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mock service behavior
        Customer customer = new Customer();
        customer.setCustomerId(123);
        when(usersServices.findByUsernameCustomerStream("testUser")).thenReturn(customer);
        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(12345678901L));

        // Mock the repository to throw a TransactionException with the appropriate message
        when(paymentTransferRepository.processTransaction(any(Transaction.class)))
                .thenThrow(new TransactionException(resourceBundle.getString("minimum.balance.fail")));

        // Perform the test
        ResponseEntity<String> responseEntity = paymentRestController.newTransactions(transaction);

        // Verify the response
        //assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("EXC002 :Transaction cannot proceed as it would lower account balance below the minimum requirement.", responseEntity.getBody());
    }


    @Test
    public void testNewTransactions_PayeeNotFound() {
        // Mock data
        Transaction transaction = new Transaction();
        transaction.setTransactionFrom(12345678901L);
        transaction.setTransactionTo(12345667897L);
        transaction.setTransactionAmount(100.0);

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");
        // Mock service behavior
        Customer customer = new Customer();
        customer.setCustomerId(123);
        when(usersServices.findByUsernameCustomerStream("testUser")).thenReturn(customer);
        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(12345678901L));

        // Mock the repository to throw a TransactionException with the appropriate message
        when(paymentTransferRepository.processTransaction(any(Transaction.class)))
                .thenThrow(new TransactionException(resourceBundle.getString("no.payee.found")));

        ResponseEntity<String> responseEntity = paymentRestController.newTransactions(transaction);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("EXC003 :No such payee found. Please add new payee!", responseEntity.getBody());
    }


    @Test
    public void testFetchAccountNumber() {
        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mock service behavior
        Customer customer = new Customer();
        customer.setCustomerId(123);
        when(usersServices.findByUsernameCustomerStream("testUser")).thenReturn(customer);
        when(usersServices.getAccountNumbersByCustomerId(123)).thenReturn(Arrays.asList(12345678901L, 12345678902L));

        // Call the method
        List<Long> accountNumbers = paymentRestController.fetchAccountNumber();

        // Verify the returned account numbers
        assertEquals(Arrays.asList(12345678901L, 12345678902L), accountNumbers);
    }

    @Test
    public void testHandleValidationExceptions() {

        BindingResult bindingResult = mock(BindingResult.class);

        FieldError fieldError1 = new FieldError("transaction", "transactionType", "Transaction type must be IMPS, NEFT, RTGS, or UPI");
        FieldError fieldError2 = new FieldError("transaction", "transactionAmount", "Transaction amount must be greater than 0");
        Mockito.when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(fieldError1, fieldError2));
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        Map<String, String> result = paymentRestController.handleValidationExceptions(exception);
        assertEquals(result.get("transactionType"), "Transaction type must be IMPS, NEFT, RTGS, or UPI");
        assertEquals(result.get("transactionAmount"), "Transaction amount must be greater than 0");
    }

    @Test
    public void testLandingPage() {
        String result = mvcController.login();
        assertEquals("index", result);
    }


    @Test
    public void testDashboard(){
        String result=mvcController.showDash();
        assertEquals("dashboard",result);
    }

   @Test
    public void testHeader(){
        String result=mvcController.showHeader();
        assertEquals("header",result);
   }

   @Test
    public void testFooter(){
        String result=mvcController.showFooter();
        assertEquals("footer",result);
   }

   @Test
    public void testTransactionPage(){
        String result=mvcController.showTrans();
        assertEquals("newtransaction",result);
   }

   @Test
    public void testViewAll(){
        String result=mvcController.showViewAll();
        assertEquals("viewall",result);
   }

   @Test
   public void testErrorPage(){
        String result=mvcController.errorPage();
        assertEquals("error",result);
   }

    @Test
    public void testShowDashboard(){
        String result=mvcController.showDashboard();
        assertEquals("dashboard",result);
    }
    @Test
    public void testLoginError() {
        Model model = mock(Model.class);
        String result = mvcController.loginError(model);
        assertEquals("index", result);
        verify(model).addAttribute("error", true);
    }

    @Test
    public void testOnAuthenticationFailure_InactiveStatus() throws Exception {
        // Mock authentication
        Customer myBankOfficials = new Customer();
        myBankOfficials.setCustomerStatus("Inactive");
        Authentication authentication = new UsernamePasswordAuthenticationToken(myBankOfficials, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Call the method
        successHandler.onAuthenticationSuccess(request, response, authentication);
        // Verify behavior
        assertNotEquals("/payment/?errors=Contact%20administrator", response.encodeRedirectURL("/payment/?errors=Contact administrator"));// Assuming failure URL is "/payeelogin/?errors=Contact administrator"
    }

    @Test
    public void testGetCustomerName() {
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("testUser");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(usersServices.getCustomerName(Mockito.anyString())).thenReturn("Aru");

        String result = mvcController.getCustomerName();
        Assertions.assertEquals("Aru", result);
    }

    @Test
    public void testAccountBalance() {
        Long accountNumber = 123456782349L;
        double expectedBalance = 1000.00;
        when(paymentTransferRepository.retrieveBalance(accountNumber)).thenReturn(expectedBalance);

        double actualBalance = paymentRestController.accountBalance(accountNumber);

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void save() {
        Customer mockCustomer = new Customer();
        mockCustomer.setUsername("User");
        mockCustomer.setPassword("Pass");
        when(passwordEncoder.encode(mockCustomer.getPassword())).thenReturn("encodedPassword");
        when(usersServices.signUp(mockCustomer)).thenReturn(mockCustomer);
        Customer savedCustomer = myBankAppApi.save(mockCustomer);
        verify(passwordEncoder).encode("Pass");
        verify(usersServices).signUp(mockCustomer);
        assertEquals("User", savedCustomer.getUsername());
        assertEquals("encodedPassword", savedCustomer.getPassword()); // Assuming getPassword() returns the encoded password
    }

}

