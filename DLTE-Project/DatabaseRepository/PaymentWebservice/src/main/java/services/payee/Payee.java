//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.04.23 at 06:42:11 AM IST 
//


package services.payee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for payee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="payee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payeeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="senderAccountNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="payeeAccountNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="payeeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "payee", propOrder = {
    "payeeId",
    "senderAccountNumber",
    "payeeAccountNumber",
    "payeeName"
})
public class Payee {

    protected int payeeId;
    protected long senderAccountNumber;
    protected long payeeAccountNumber;
    @XmlElement(required = true)
    protected String payeeName;

    /**
     * Gets the value of the payeeId property.
     * 
     */
    public int getPayeeId() {
        return payeeId;
    }

    /**
     * Sets the value of the payeeId property.
     * 
     */
    public void setPayeeId(int value) {
        this.payeeId = value;
    }

    /**
     * Gets the value of the senderAccountNumber property.
     * 
     */
    public long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    /**
     * Sets the value of the senderAccountNumber property.
     * 
     */
    public void setSenderAccountNumber(long value) {
        this.senderAccountNumber = value;
    }

    /**
     * Gets the value of the payeeAccountNumber property.
     * 
     */
    public long getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    /**
     * Sets the value of the payeeAccountNumber property.
     * 
     */
    public void setPayeeAccountNumber(long value) {
        this.payeeAccountNumber = value;
    }

    /**
     * Gets the value of the payeeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * Sets the value of the payeeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayeeName(String value) {
        this.payeeName = value;
    }

}
