//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.04.17 at 04:55:29 PM IST 
//


package services.payee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="senderAccount" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "senderAccount"
})
@XmlRootElement(name = "findAllPayeeBasedOnAccountNumberLambdaRequest")
public class FindAllPayeeBasedOnAccountNumberLambdaRequest {

    protected long senderAccount;

    /**
     * Gets the value of the senderAccount property.
     * 
     */
    public long getSenderAccount() {
        return senderAccount;
    }

    /**
     * Sets the value of the senderAccount property.
     * 
     */
    public void setSenderAccount(long value) {
        this.senderAccount = value;
    }

}
