package com.payment.webservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.webservices.controller.PaymentSoapPhase;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.entity.Transaction;
import com.paymentdao.payment.exceptions.PayeeException;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import services.payee.*;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndPointTesting {
    @MockBean
    private PaymentTransferImplementation paymentService;

    @InjectMocks
    private PaymentSoapPhase soapPhase;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentService).build();
    }
    @Test
    public void testFindAllAccountNumber() throws SQLSyntaxErrorException {
        List<com.paymentdao.payment.entity.Payee> payees;
        com.paymentdao.payment.entity.Payee payee = new Payee();
        com.paymentdao.payment.entity.Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        com.paymentdao.payment.entity.Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        com.paymentdao.payment.entity.Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        com.paymentdao.payment.entity.Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
        payees= Stream.of(payee1,payee3).collect(Collectors.toList());

        when(paymentService.findAllPayeeBasedOnAccountNumber(213456789654L)).thenReturn(payees);

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
        List<com.paymentdao.payment.entity.Payee> payees;
        com.paymentdao.payment.entity.Payee payee = new Payee();
        com.paymentdao.payment.entity.Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        com.paymentdao.payment.entity.Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        com.paymentdao.payment.entity.Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        com.paymentdao.payment.entity.Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
        payees= Stream.of(payee1,payee3).collect(Collectors.toList());

        when(paymentService.findAllPayeeBasedOnAccountNumber(213456789654L)).thenReturn(payees);

        FindAllPayeeBasedOnAccountNumberRequest request = new FindAllPayeeBasedOnAccountNumberRequest();
        // passing the entity
        request.setSenderAccount(213456789654L);
        FindAllPayeeBasedOnAccountNumberResponse response = soapPhase.listPayeeBasedOnAccountNumber(request);

        //checking the payeeName in entity as well in response

         assertEquals(payees.get(0).getPayeeName(), response.getPayee().get(1).getPayeeName());
        // checking the response is success or not
       assertEquals(200, response.getServiceStatus().getStatus());

    }


    @Test
    public void testAll() throws SQLSyntaxErrorException {
        List<com.paymentdao.payment.entity.Payee> payees;
        com.paymentdao.payment.entity.Payee payee = new Payee();
        com.paymentdao.payment.entity.Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        com.paymentdao.payment.entity.Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        com.paymentdao.payment.entity.Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        com.paymentdao.payment.entity.Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
        payees= Stream.of(payee1,payee2,payee3,payee4).collect(Collectors.toList());

        when(paymentService.findAllPayee()).thenReturn(payees);

        FindAllPayeeRequest request = new FindAllPayeeRequest();
        // passing the entity

        FindAllPayeeResponse response = soapPhase.listAllPayee(request);

        //checking the payeeName in entity as well in response
        assertEquals(payees.get(0).getPayeeName(), response.getPayee().get(0).getPayeeName());
    }

    @Test
    public void testAllFail() throws SQLSyntaxErrorException {
        List<com.paymentdao.payment.entity.Payee> payees;
        com.paymentdao.payment.entity.Payee payee = new Payee();
        com.paymentdao.payment.entity.Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        com.paymentdao.payment.entity.Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        com.paymentdao.payment.entity.Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        com.paymentdao.payment.entity.Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
        payees= Stream.of(payee1,payee2,payee3,payee4).collect(Collectors.toList());

        when(paymentService.findAllPayee()).thenReturn(payees);

        FindAllPayeeRequest request = new FindAllPayeeRequest();
        // passing the entity

        FindAllPayeeResponse response = soapPhase.listAllPayee(request);

        //checking the payeeName in entity as well in response
        assertEquals(0,response.getPayee().size());
    }


}
