import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.services.AccountService;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FindAllByDateAndUsernameSOAP {
    List<Transaction> transactionList=new ArrayList<>();
    AccountService accountService;

    public FindAllByDateAndUsernameSOAP() {
        accountService=new AccountService(new DatabaseTarget());
    }

    @WebMethod
    @WebResult(name="ListOfUsers")
    public List<Transaction> findByDateAndUser(Date date,String username) throws JsonProcessingException, JAXBException {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        transactionList.addAll(accountService.callFindAllDate(sqlDate,username));
        System.out.println(transactionList);
        return transactionList;
//        ObjectMapper objectMapper=new ObjectMapper();
//        JsonNode rootNode=objectMapper.readTree(jsonParams);
//        Date date=new Date(rootNode.get("date").asLong());
//        String username=rootNode.get("username").asText();
//        StringWriter stringWriter = new StringWriter();
//        JAXBContext jaxbContext = JAXBContext.newInstance(Date.class, String.class);
//        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        marshaller.marshal(date, stringWriter);
//        marshaller.marshal(username, stringWriter);
//      //  String xmlParams = stringWriter.toString();
//
//        // Call the service method with XML parameters
//        transactionList.addAll(accountService.callFindAllDate((java.sql.Date) date, username));
//        return transactionList;
    }
}
