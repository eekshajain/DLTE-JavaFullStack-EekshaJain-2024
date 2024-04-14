
package services.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employeeBasic" type="{http://employee.services}employebasicdetails"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "employeeBasic"
})
@XmlRootElement(name = "newEmployeeRequest")
public class NewEmployeeRequest {

    @XmlElement(required = true)
    protected Employebasicdetails employeeBasic;

    /**
     * Gets the value of the employeeBasic property.
     * 
     * @return
     *     possible object is
     *     {@link Employebasicdetails }
     *     
     */
    public Employebasicdetails getEmployeeBasic() {
        return employeeBasic;
    }

    /**
     * Sets the value of the employeeBasic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Employebasicdetails }
     *     
     */
    public void setEmployeeBasic(Employebasicdetails value) {
        this.employeeBasic = value;
    }

}
