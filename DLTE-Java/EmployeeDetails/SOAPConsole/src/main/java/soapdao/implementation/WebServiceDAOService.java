
package soapdao.implementation;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WebServiceDAOService", targetNamespace = "http://implementation.soapdao/", wsdlLocation = "http://localhost:1011/webServiceDAO?wsdl")
public class WebServiceDAOService
    extends Service
{

    private final static URL WEBSERVICEDAOSERVICE_WSDL_LOCATION;
    private final static WebServiceException WEBSERVICEDAOSERVICE_EXCEPTION;
    private final static QName WEBSERVICEDAOSERVICE_QNAME = new QName("http://implementation.soapdao/", "WebServiceDAOService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:1011/webServiceDAO?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WEBSERVICEDAOSERVICE_WSDL_LOCATION = url;
        WEBSERVICEDAOSERVICE_EXCEPTION = e;
    }

    public WebServiceDAOService() {
        super(__getWsdlLocation(), WEBSERVICEDAOSERVICE_QNAME);
    }

    public WebServiceDAOService(WebServiceFeature... features) {
        super(__getWsdlLocation(), WEBSERVICEDAOSERVICE_QNAME, features);
    }

    public WebServiceDAOService(URL wsdlLocation) {
        super(wsdlLocation, WEBSERVICEDAOSERVICE_QNAME);
    }

    public WebServiceDAOService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WEBSERVICEDAOSERVICE_QNAME, features);
    }

    public WebServiceDAOService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServiceDAOService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WebServiceDAO
     */
    @WebEndpoint(name = "WebServiceDAOPort")
    public WebServiceDAO getWebServiceDAOPort() {
        return super.getPort(new QName("http://implementation.soapdao/", "WebServiceDAOPort"), WebServiceDAO.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServiceDAO
     */
    @WebEndpoint(name = "WebServiceDAOPort")
    public WebServiceDAO getWebServiceDAOPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://implementation.soapdao/", "WebServiceDAOPort"), WebServiceDAO.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WEBSERVICEDAOSERVICE_EXCEPTION!= null) {
            throw WEBSERVICEDAOSERVICE_EXCEPTION;
        }
        return WEBSERVICEDAOSERVICE_WSDL_LOCATION;
    }

}
