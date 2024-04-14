
package services.employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for employeeaddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="employeeaddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="houseName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="houseStreet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stateName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pinCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "employeeaddress", propOrder = {
    "houseName",
    "houseStreet",
    "cityName",
    "stateName",
    "pinCode"
})
public class Employeeaddress {

    @XmlElement(required = true)
    protected String houseName;
    @XmlElement(required = true)
    protected String houseStreet;
    @XmlElement(required = true)
    protected String cityName;
    @XmlElement(required = true)
    protected String stateName;
    protected int pinCode;

    /**
     * Gets the value of the houseName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseName() {
        return houseName;
    }

    /**
     * Sets the value of the houseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseName(String value) {
        this.houseName = value;
    }

    /**
     * Gets the value of the houseStreet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseStreet() {
        return houseStreet;
    }

    /**
     * Sets the value of the houseStreet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseStreet(String value) {
        this.houseStreet = value;
    }

    /**
     * Gets the value of the cityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the value of the cityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * Gets the value of the stateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Sets the value of the stateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateName(String value) {
        this.stateName = value;
    }

    /**
     * Gets the value of the pinCode property.
     * 
     */
    public int getPinCode() {
        return pinCode;
    }

    /**
     * Sets the value of the pinCode property.
     * 
     */
    public void setPinCode(int value) {
        this.pinCode = value;
    }

}
