package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddTransaction {

    public static void main(String[] args) {
        List<Transaction> transactionList=new ArrayList<>();
        transactionList.add(new Transaction("Vinita",new Date(),2000,"Anita","Friend"));
        transactionList.add(new Transaction("Savitha",new Date(),300,"Sunita","Family"));
        transactionList.add(new Transaction("Savitha",new Date(),400,"Tanya","Emergency"));

        TransactionWrapper wrapper=new TransactionWrapper();
        wrapper.setTransactions(transactionList);

        try{
            JAXBContext context = JAXBContext.newInstance(TransactionWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            marshaller.marshal(wrapper,new FileOutputStream("AllTransactions.xml"));
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
