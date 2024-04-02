
package soapdao.implementation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WebServiceDAO", targetNamespace = "http://implementation.soapdao/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebServiceDAO {


    /**
     * 
     * @return
     *     returns soapdao.implementation.GroupOfEmployees
     */
    @WebMethod
    @WebResult(name = "findAll", partName = "findAll")
    @Action(input = "http://implementation.soapdao/WebServiceDAO/callFindAllRequest", output = "http://implementation.soapdao/WebServiceDAO/callFindAllResponse")
    public GroupOfEmployees callFindAll();

    /**
     * 
     * @param arg0
     * @return
     *     returns soapdao.implementation.Employee
     */
    @WebMethod
    @WebResult(name = "addNewEmployee", partName = "addNewEmployee")
    @Action(input = "http://implementation.soapdao/WebServiceDAO/callSaveAllRequest", output = "http://implementation.soapdao/WebServiceDAO/callSaveAllResponse")
    public Employee callSaveAll(
        @WebParam(name = "arg0", partName = "arg0")
        Employee arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(name = "doesEmployeeExists", partName = "doesEmployeeExists")
    @Action(input = "http://implementation.soapdao/WebServiceDAO/callEmployeeExistsRequest", output = "http://implementation.soapdao/WebServiceDAO/callEmployeeExistsResponse")
    public boolean callEmployeeExists(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns soapdao.implementation.GroupOfEmployees
     */
    @WebMethod
    @WebResult(name = "findBasedOnPincode", partName = "findBasedOnPincode")
    @Action(input = "http://implementation.soapdao/WebServiceDAO/callFilterBasedOnPincodeRequest", output = "http://implementation.soapdao/WebServiceDAO/callFilterBasedOnPincodeResponse")
    public GroupOfEmployees callFilterBasedOnPincode(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns soapdao.implementation.Employee
     */
    @WebMethod
    @WebResult(name = "findBasedOnId", partName = "findBasedOnId")
    @Action(input = "http://implementation.soapdao/WebServiceDAO/callFilterBasedOnIDRequest", output = "http://implementation.soapdao/WebServiceDAO/callFilterBasedOnIDResponse")
    public Employee callFilterBasedOnID(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

}
