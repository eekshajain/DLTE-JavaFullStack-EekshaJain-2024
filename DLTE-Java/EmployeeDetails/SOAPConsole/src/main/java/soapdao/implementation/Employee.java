
package soapdao.implementation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeBasicDetails" type="{http://implementation.soapdao/}employeeBasicDetails" minOccurs="0"/>
 *         &lt;element name="permanentEmployeeAddress" type="{http://implementation.soapdao/}employeeAddress" minOccurs="0"/>
 *         &lt;element name="temporaryEmployeeAddress" type="{http://implementation.soapdao/}employeeAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employee", propOrder = {
    "employeeBasicDetails",
    "permanentEmployeeAddress",
    "temporaryEmployeeAddress"
})
public class Employee {

    protected EmployeeBasicDetails employeeBasicDetails;
    protected EmployeeAddress permanentEmployeeAddress;
    protected EmployeeAddress temporaryEmployeeAddress;

    /**
     * Gets the value of the employeeBasicDetails property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeBasicDetails }
     *     
     */
    public EmployeeBasicDetails getEmployeeBasicDetails() {
        return employeeBasicDetails;
    }

    /**
     * Sets the value of the employeeBasicDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeBasicDetails }
     *     
     */
    public void setEmployeeBasicDetails(EmployeeBasicDetails value) {
        this.employeeBasicDetails = value;
    }

    /**
     * Gets the value of the permanentEmployeeAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getPermanentEmployeeAddress() {
        return permanentEmployeeAddress;
    }

    /**
     * Sets the value of the permanentEmployeeAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setPermanentEmployeeAddress(EmployeeAddress value) {
        this.permanentEmployeeAddress = value;
    }

    /**
     * Gets the value of the temporaryEmployeeAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeAddress }
     *     
     */
    public EmployeeAddress getTemporaryEmployeeAddress() {
        return temporaryEmployeeAddress;
    }

    /**
     * Sets the value of the temporaryEmployeeAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeAddress }
     *     
     */
    public void setTemporaryEmployeeAddress(EmployeeAddress value) {
        this.temporaryEmployeeAddress = value;
    }

}
