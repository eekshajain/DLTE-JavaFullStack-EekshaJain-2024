package com.payment.webservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.webservices.controller.PaymentSoapPhase;
import com.payment.webservices.security.MyBankAppApi;
import com.payment.webservices.security.OfficialsFailureHandler;
import com.payment.webservices.security.OfficialsSuccessHandler;
import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.security.MyBankUsersServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import services.payee.FindAllPayeeBasedOnAccountNumberRequest;
import services.payee.FindAllPayeeBasedOnAccountNumberResponse;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndPointTesting {

    @Mock
    MyBankUsersServices myBankUsersServices;
    @InjectMocks
    private OfficialsFailureHandler failureHandler;
    @InjectMocks
    private OfficialsSuccessHandler successHandler;
    @Mock
    private SpringApplicationBuilder mockApplicationBuilder;

  //  @Test
    public void testAuthenticationFailureAttemptsExceeded() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException exception = new BadCredentialsException("Invalid credentials");

        String username = "testUser";
        Customer myBankOfficials = new Customer();
        myBankOfficials.setUsername(username);
        myBankOfficials.setCustomerStatus("active"); // Assuming status allows authentication
        myBankOfficials.setAttempts(3); // Assuming maximum attempts are 3
        when(myBankUsersServices.findByUsernameCustomerStream(username)).thenReturn(myBankOfficials);
        failureHandler.onAuthenticationFailure(request, response, exception);
        assertEquals("/web/?error=User not exists",response.getRedirectedUrl());
    }

 //   @Test
    public void testAuthenticationFailureUserNotExists() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException exception = new UsernameNotFoundException("User not exists");
        String username = "nonExistingUser";
        when(myBankUsersServices.findByUsernameCustomerStream(username)).thenReturn(null);
        failureHandler.onAuthenticationFailure(request, response, exception);
        assertEquals("/web/?error=User not exists", response.getRedirectedUrl());
    }


    @Test
    public void testSuccessHandler() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Authentication authentication = mock(Authentication.class);
        Customer customer = new Customer();
        customer.setCustomerStatus("active"); // Assuming status allows authentication
        when(authentication.getPrincipal()).thenReturn(customer);
        successHandler.onAuthenticationSuccess(request, response, authentication);
        assertEquals("/payment/dashboard", response.getRedirectedUrl());
    }

    @Test
    public void testMaxAttemptsReached() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Authentication authentication = mock(Authentication.class);
        Customer myBankOfficials = new Customer();
        myBankOfficials.setCustomerStatus("inactive"); // Assuming status indicates maximum attempts reached
        when(authentication.getPrincipal()).thenReturn(myBankOfficials);
        successHandler.onAuthenticationSuccess(request, response, authentication);
        assertEquals("/payment/?errors=Your account is blocked,Please contact admin!", response.getRedirectedUrl());
    }



    @Test
    void configureTest() {
        ServletInitializer servletInitializer = new ServletInitializer();
        servletInitializer.configure(mockApplicationBuilder);
        verify(mockApplicationBuilder).sources(WebservicesApplication.class);
    }
    }







