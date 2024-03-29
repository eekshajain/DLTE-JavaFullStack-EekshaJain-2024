
package read;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;
import java.util.Date;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ReadService", targetNamespace = "http://read/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ReadService {


    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns read.TransactionGroup
     */
    @WebMethod
    @WebResult(name = "ListOfUsers", partName = "ListOfUsers")
    @Action(input = "http://read/ReadService/findByDateAndUserRequest", output = "http://read/ReadService/findByDateAndUserResponse")
    public TransactionGroup findByDateAndUser(
        @WebParam(name = "arg0", partName = "arg0")
                Date arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

}
