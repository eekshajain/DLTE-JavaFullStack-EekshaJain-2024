
package read;

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
@WebServiceClient(name = "ReadServiceService", targetNamespace = "http://read/", wsdlLocation = "http://localhost:1011/read?wsdl")
public class ReadServiceService
    extends Service
{

    private final static URL READSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException READSERVICESERVICE_EXCEPTION;
    private final static QName READSERVICESERVICE_QNAME = new QName("http://read/", "ReadServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:1011/read?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        READSERVICESERVICE_WSDL_LOCATION = url;
        READSERVICESERVICE_EXCEPTION = e;
    }

    public ReadServiceService() {
        super(__getWsdlLocation(), READSERVICESERVICE_QNAME);
    }

    public ReadServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), READSERVICESERVICE_QNAME, features);
    }

    public ReadServiceService(URL wsdlLocation) {
        super(wsdlLocation, READSERVICESERVICE_QNAME);
    }

    public ReadServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, READSERVICESERVICE_QNAME, features);
    }

    public ReadServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ReadServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ReadService
     */
    @WebEndpoint(name = "ReadServicePort")
    public ReadService getReadServicePort() {
        return super.getPort(new QName("http://read/", "ReadServicePort"), ReadService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReadService
     */
    @WebEndpoint(name = "ReadServicePort")
    public ReadService getReadServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://read/", "ReadServicePort"), ReadService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (READSERVICESERVICE_EXCEPTION!= null) {
            throw READSERVICESERVICE_EXCEPTION;
        }
        return READSERVICESERVICE_WSDL_LOCATION;
    }

}