package org.example;

import org.example.WebServicesDAO;

import javax.xml.ws.Endpoint;

public class MyEndPoint {
    private static String url="http://localhost:1011/webServiceDAO"; //url to publish webservice

    public static void main(String[] args) {
   WebServicesDAO webServices=new WebServicesDAO();
        System.out.println("Webservice running at "+url);
        Endpoint.publish(url,webServices);
    }
}
