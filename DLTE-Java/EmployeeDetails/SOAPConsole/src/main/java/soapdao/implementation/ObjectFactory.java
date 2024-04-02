
package soapdao.implementation;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soapdao.implementation package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soapdao.implementation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GroupOfEmployees }
     * 
     */
    public GroupOfEmployees createGroupOfEmployees() {
        return new GroupOfEmployees();
    }

    /**
     * Create an instance of {@link EmployeeBasicDetails }
     * 
     */
    public EmployeeBasicDetails createEmployeeBasicDetails() {
        return new EmployeeBasicDetails();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee() {
        return new Employee();
    }

    /**
     * Create an instance of {@link EmployeeAddress }
     * 
     */
    public EmployeeAddress createEmployeeAddress() {
        return new EmployeeAddress();
    }

}
