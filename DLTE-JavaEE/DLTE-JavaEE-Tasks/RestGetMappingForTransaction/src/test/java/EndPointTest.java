import com.google.gson.Gson;
import org.example.entity.Transaction;
import org.example.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EndPointTest {
    @Mock
    private AccountService service;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;

    @Before
    public void initiate() throws IOException {
        stringWriter=new StringWriter();
        printWriter=new PrintWriter(stringWriter);
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
    }
    @Test
    public void testFindAllByDateAndUsername() throws ServletException, IOException {
       FindAllByDateAndUsername findAllByDateAndUsername = new FindAllByDateAndUsername();
       findAllByDateAndUsername.accountService=service;
        Transaction transaction1=new Transaction(new Date("3/2/2024"),1234L,"Jayant",200,4500);
        Transaction transaction2=new Transaction(new Date("3/3/2024"),1235L,"Jayant",600,3900);
        Transaction transaction3=new Transaction(new Date("3/4/2024"),1236L,"Jayant",300,3600);
        Transaction transaction4=new Transaction(new Date("3/4/2024"),1237L,"Jayant",100,3500);
      //  List<Transaction> transactionList= Stream.of(transaction1,transaction2,transaction3,transaction4).collect(Collectors.toList());
        List<Transaction> transactionList1=Stream.of(transaction3,transaction4).collect(Collectors.toList());
        Gson gson=new Gson();
        String transaction=gson.toJson(transactionList1);
        System.out.println(transaction);
        when(httpServletRequest.getParameter("username")).thenReturn("Jayant");
        when(httpServletRequest.getParameter("date")).thenReturn("2024-03-04");
        when(service.callFindAllDate(any(),anyString())).thenReturn(transactionList1);
        findAllByDateAndUsername.doGet(httpServletRequest,httpServletResponse);
        verify(httpServletResponse).setContentType("application/json");
        verify(service).callFindAllDate(any(),anyString());
        //test case passes
      assertEquals("[{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1236,\"user\":\"Jayant\",\"amount\":300.0,\"balance\":3600.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1237,\"user\":\"Jayant\",\"amount\":100.0,\"balance\":3500.0}]",stringWriter.toString().trim());
      //test case fails
    //  assertEquals("[{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1236,\"user\":\"Jayant\",\"amount\":200.0,\"balance\":3600.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1237,\"user\":\"Jayant\",\"amount\":100.0,\"balance\":3500.0}]",stringWriter.toString().trim());
        //test case fails
      // assertNotEquals("[{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1236,\"user\":\"Jayant\",\"amount\":300.0,\"balance\":3600.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1237,\"user\":\"Jayant\",\"amount\":100.0,\"balance\":3500.0}]",stringWriter.toString().trim());
    }
    @Test
  public void testFindAllByUsername() throws ServletException, IOException {
       FindAllByUsername findAllByUsername=new FindAllByUsername();
       findAllByUsername.accountService=service;
        Transaction transaction1=new Transaction(new Date("3/2/2024"),1234L,"Jayant",200,4500);
        Transaction transaction2=new Transaction(new Date("3/3/2024"),1235L,"Jayant",600,3900);
        Transaction transaction3=new Transaction(new Date("3/4/2024"),1236L,"Jayant",300,3600);
        Transaction transaction4=new Transaction(new Date("3/4/2024"),1237L,"Jayant",100,3500);
        List<Transaction> transactionList= Stream.of(transaction1,transaction2,transaction3,transaction4).collect(Collectors.toList());
        when(httpServletRequest.getParameter("username")).thenReturn("Jayant");
        when(service.callFindAllUser(anyString())).thenReturn(transactionList);
        findAllByUsername.doGet(httpServletRequest,httpServletResponse);
        verify(httpServletResponse).setContentType("application/json");
        verify(service).callFindAllUser(anyString());
        //test case passes
        assertEquals("Expected List","[{\"date\":\"Mar 2, 2024 12:00:00 AM\",\"transactionID\":1234,\"user\":\"Jayant\",\"amount\":200.0,\"balance\":4500.0},{\"date\":\"Mar 3, 2024 12:00:00 AM\",\"transactionID\":1235,\"user\":\"Jayant\",\"amount\":600.0,\"balance\":3900.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1236,\"user\":\"Jayant\",\"amount\":300.0,\"balance\":3600.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1237,\"user\":\"Jayant\",\"amount\":100.0,\"balance\":3500.0}]",stringWriter.toString().trim());
        //test case fails
        //assertEquals("Expected List","[{\"date\":\"Mar 2, 2024 12:00:00 AM\",\"transactionID\":1234,\"user\":\"Jayant\",\"amount\":200.0,\"balance\":4800.0},{\"date\":\"Mar 3, 2024 12:00:00 AM\",\"transactionID\":1235,\"user\":\"Jayant\",\"amount\":600.0,\"balance\":3900.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1236,\"user\":\"Jayant\",\"amount\":300.0,\"balance\":3600.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1237,\"user\":\"Jayant\",\"amount\":100.0,\"balance\":3500.0}]",stringWriter.toString().trim());
        //test case fails
        //assertNotEquals("Expected List","[{\"date\":\"Mar 2, 2024 12:00:00 AM\",\"transactionID\":1234,\"user\":\"Jayant\",\"amount\":200.0,\"balance\":4500.0},{\"date\":\"Mar 3, 2024 12:00:00 AM\",\"transactionID\":1235,\"user\":\"Jayant\",\"amount\":600.0,\"balance\":3900.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1236,\"user\":\"Jayant\",\"amount\":300.0,\"balance\":3600.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1237,\"user\":\"Jayant\",\"amount\":100.0,\"balance\":3500.0}]",stringWriter.toString().trim());
  }
    @Test
    public void testFindAll() throws ServletException, IOException {
      FindAll findAll=new FindAll();
      findAll.accountService=service;
        Transaction transaction1=new Transaction(new Date("3/2/2024"),1234L,"Jayant",200,4500);
        Transaction transaction2=new Transaction(new Date("3/3/2024"),1235L,"Jayant",600,3900);
        Transaction transaction3=new Transaction(new Date("3/4/2024"),1236L,"Jayant",300,3600);
        Transaction transaction4=new Transaction(new Date("3/4/2024"),1237L,"Jayant",100,3500);
        Transaction transaction5=new Transaction(new Date("3/4/2024"),1345L,"Jayathi",100,4500);
        List<Transaction> transactionList= Stream.of(transaction1,transaction2,transaction3,transaction4,transaction5).collect(Collectors.toList());
        //System.out.println(transactionList.toString());
        Gson gson=new Gson();
        String transaction=gson.toJson(transactionList);
        System.out.println(transaction);
        when(service.callFindAll()).thenReturn(transactionList);
        findAll.doGet(httpServletRequest,httpServletResponse);
        verify(httpServletResponse).setContentType("application/json");
        verify(service).callFindAll();
       assertEquals("Expected List","[{\"date\":\"Mar 2, 2024 12:00:00 AM\",\"transactionID\":1234,\"user\":\"Jayant\",\"amount\":200.0,\"balance\":4500.0},{\"date\":\"Mar 3, 2024 12:00:00 AM\",\"transactionID\":1235,\"user\":\"Jayant\",\"amount\":600.0,\"balance\":3900.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1236,\"user\":\"Jayant\",\"amount\":300.0,\"balance\":3600.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1237,\"user\":\"Jayant\",\"amount\":100.0,\"balance\":3500.0},{\"date\":\"Mar 4, 2024 12:00:00 AM\",\"transactionID\":1345,\"user\":\"Jayathi\",\"amount\":100.0,\"balance\":4500.0}]",stringWriter.toString().trim());
    }

}
