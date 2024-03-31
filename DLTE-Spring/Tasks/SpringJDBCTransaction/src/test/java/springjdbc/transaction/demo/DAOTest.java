package springjdbc.transaction.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DAOTest {
    @MockBean
    TransactionServices transactionServices;
    @InjectMocks
    SoapPhase soapPhase;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
public void testBySender(){
    Transaction transactions1=new Transaction(123456L,new Date(2024,03,31),"Eeksha","MAX",500,"Bills");
    Transaction transactions2=new Transaction(123457L,new Date(2024,03,31),"Eeksha","Yuthika",700,"Family");

    List<Transaction> transactionsList= Stream.of(transactions1,transactions2).collect(Collectors.toList());
    when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);
    List<Transaction> result=transactionServices.apiFindBySender("Eeksha");
    assertNotNull(result);
    assertEquals(transactionsList,result);
}
}
