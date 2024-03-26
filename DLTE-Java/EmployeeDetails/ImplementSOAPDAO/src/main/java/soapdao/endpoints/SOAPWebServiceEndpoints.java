package soapdao.endpoints;

import soapdao.implementation.WebServiceDAO;

import javax.xml.ws.Endpoint;

public class SOAPWebServiceEndpoints {
    private static String url="http://localhost:1011/webServiceDAO"; //url to publish webservice

    public static void main(String[] args) {
        WebServiceDAO webService=new WebServiceDAO();
        System.out.println("Webservice running at "+url);
        Endpoint.publish(url,webService);
    }
}
