
package org.me.imagenes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SearchbyCreatDate complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SearchbyCreatDate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="creaDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchbyCreatDate", propOrder = {
    "creaDate"
})
public class SearchbyCreatDate {

    protected String creaDate;

    /**
     * Obtiene el valor de la propiedad creaDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreaDate() {
        return creaDate;
    }

    /**
     * Define el valor de la propiedad creaDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreaDate(String value) {
        this.creaDate = value;
    }

}
