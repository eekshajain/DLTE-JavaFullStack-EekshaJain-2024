
package services.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employebasicdetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employebasicdetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="emailID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="temporaryEmployeeAddress" type="{http://employee.services}employeeaddress"/>
 *         &lt;element name="permanentEmployeeAddress" type="{http://employee.services}employeeaddress"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employebasicdetails", propOrder = {
    "employeeID",
    "firstName",
    "middleName",
    "lastName",
    "phoneNumber",
    "emailID",
    "temporaryEmployeeAddress",
    "permanentEmployeeAddress"
})
public class Employebasicdetails {

    protected int employeeID;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String middleName;
    @XmlElement(required = true)
    protected String lastName;
    protected long phoneNumber;
    @XmlElement(required = true)
    protected String emailID;
    @XmlElement(required = true)
    protected Employeeaddress temporaryEmployeeAddress;
    @XmlElement(required = true)
    protected Employeeaddress permanentEmployeeAddress;

    /**
     * Gets the value of the employeeID property.
     * 
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the value of the employeeID property.
     * 
     */
    public void setEmployeeID(int value) {
        this.employeeID = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     */
    public void setPhoneNumber(long value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the emailID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailID() {
        return emailID;
    }

    /**
     * Sets the value of the emailID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailID(String value) {
        this.emailID = value;
    }

    /**
     * Gets the value of the temporaryEmployeeAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Employeeaddress }
     *     
     */
    public Employeeaddress getTemporaryEmployeeAddress() {
        return temporaryEmployeeAddress;
    }

    /**
     * Sets the value of the temporaryEmployeeAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Employeeaddress }
     *     
     */
    public void setTemporaryEmployeeAddress(Employeeaddress value) {
        this.temporaryEmployeeAddress = value;
    }

    /**
     * Gets the value of the permanentEmployeeAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Employeeaddress }
     *     
     */
    public Employeeaddress getPermanentEmployeeAddress() {
        return permanentEmployeeAddress;
    }

    /**
     * Sets the value of the permanentEmployeeAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Employeeaddress }
     *     
     */
    public void setPermanentEmployeeAddress(Employeeaddress value) {
        this.permanentEmployeeAddress = value;
    }

}