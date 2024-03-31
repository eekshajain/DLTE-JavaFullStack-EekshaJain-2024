package springjdbc.transaction.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springjdbc.transaction.demo.configs.SoapPhase;
import springjdbc.transaction.demo.dao.Transaction;
import springjdbc.transaction.demo.dao.TransactionServices;

import javax.xml.transform.Result;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EndPoint {
    @MockBean
    TransactionServices transactionServices;
    @InjectMocks
    SoapPhase soapPhase;
    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void testAdd() throws Exception {
//        String requestXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://your-namespace/\">" +
//                "<soapenv:Header/>" +
//                "<soapenv:Body>" +
//                "<ser:newTransactionRequest>" +
//                "<transaction>" +
//                "<transactionId>123456</transactionId>" +
//                "<transactionDate>2024-03-31T00:00:00</transactionDate>" +
//                "<transactionBy>Eeksha</transactionBy>" +
//                "<transactionTo>Divija</transactionTo>" +
//                "<transactionAmount>2000</transactionAmount>" +
//                "<transactionRemarks>Friend</transactionRemarks>" +
//                "</transaction>" +
//                "</ser:newTransactionRequest>" +
//                "</soapenv:Body>" +
//                "</soapenv:Envelope>";
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/transactionrepo/newTransactionRequest")
//                .contentType(MediaType.TEXT_XML)
//                .content(requestXml))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_XML))
//                // Add more assertions as needed for the response
//                .andReturn();
//
//        // Optionally, you can extract and verify specific elements from the response XML
//        String responseXml = result.getResponse().getContentAsString();
//        // Perform assertions on responseXml
//    }

    @Test
    //@WithMockUser(username = "eeksha", password = "123456", roles = "admin")

    public void testSoapEndpoint() throws Exception {
        String username = "eeksha";
        String password = "123456";
        String role = "admin"; // Assuming the user has admin role

        // Create authentication token with the user details and authorities
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password,
                        Collections.singletonList(new SimpleGrantedAuthority(role)));

        // Set the authentication token to the security context
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String soapRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://your-namespace/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:newTransactionRequest>\n" +
                "         <transaction>\n" +
                "            <transactionId>12345</transactionId>\n" +
                "            <transactionDate>2024-03-31T00:00:00</transactionDate>\n" +
                "            <transactionBy>Eeksha</transactionBy>\n" +
                "            <transactionTo>Divija</transactionTo>\n" +
                "            <transactionAmount>2000</transactionAmount>\n" +
                "            <transactionRemarks>Friend</transactionRemarks>\n" +
                "         </transaction>\n" +
                "      </ser:newTransactionRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/transactionrepo/add")
                .contentType(MediaType.TEXT_XML)
                .content(soapRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_XML))
                .andReturn();

        String soapResponse = result.getResponse().getContentAsString();

        // Perform assertions on the soapResponse XML
        assertThat(soapResponse, containsString("<transactionId>12345</transactionId>"));
        assertThat(soapResponse, containsString("<transactionDate>2024-03-31T00:00:00</transactionDate>"));
        assertThat(soapResponse, containsString("<transactionBy>Eeksha</transactionBy>"));
        assertThat(soapResponse, containsString("<transactionTo>Divija</transactionTo>"));
        assertThat(soapResponse, containsString("<transactionAmount>2000</transactionAmount>"));
        assertThat(soapResponse, containsString("<transactionRemarks>Friend</transactionRemarks>"));
    }

}
