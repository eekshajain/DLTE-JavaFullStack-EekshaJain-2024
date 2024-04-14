package com.payment.webservices;

import com.payment.webservices.controller.PaymentRestController;
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

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RestEndPoint {
    @MockBean
    private PaymentTransferImplementation paymentService;
    @InjectMocks
    PaymentRestController paymentRestController;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentRestController).build();
    }

    @Test
    public void testNewTransaction() throws Exception {

        // Create a transaction object
//    Transaction transaction = new Transaction();
//    transaction.setTransactionFrom(123456789);
//    transaction.setTransactionTo(987654321);
//    transaction.setTransactionType("transfer");
//    transaction.setTransactionAmount(100.0);
        String transactionJson="{\n" +
                "  \"transactionFrom\": 123456789,\n" +
                "  \"transactionTo\": 987654321,\n" +
                "  \"transactionType\": \"transfer\",\n" +
                "  \"transactionAmount\": 100.0\n" +
                "}";
        doNothing().when(paymentService).processTransaction(eq(123456789L), eq(987654321L), eq("transfer"), eq(100.0));

        // Perform the POST request using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionJson))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Transaction successful to 987654321"));
    }


}
