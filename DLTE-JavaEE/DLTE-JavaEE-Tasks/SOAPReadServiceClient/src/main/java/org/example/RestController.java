//package org.example;
//
//import read.ReadService;
//import read.TransactionGroup;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.Path;
//import java.io.IOException;
//import java.sql.Date;
//
//@WebServlet("transaction/new")
//public class RestController extends HttpServlet {
//    private static final String SOAP_ENDPOINT_URL = "http://localhost:8080/ReadService/ReadServiceService";
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String date=req.getParameter("date");
//        String username=req.getParameter("username");
//        ReadService readService = createSoapClientProxy();
//        TransactionGroup transactionGroup = readService.findByDateAndUser(Date.valueOf(date), username);
//    }
//
//    private ReadService createSoapClientProxy() {
//        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//        factory.setServiceClass(ReadService.class);
//        factory.setAddress(SOAP_ENDPOINT_URL);
//        return (ReadService) factory.create();
//    }
//}
