
package org.me.imagenes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SearchComb complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SearchComb"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="autorbus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="desbus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="titulobus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechabus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="palclavebusqueda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchComb", propOrder = {
    "autorbus",
    "desbus",
    "titulobus",
    "fechabus",
    "palclavebusqueda"
})
public class SearchComb {

    protected String autorbus;
    protected String desbus;
    protected String titulobus;
    protected String fechabus;
    protected String palclavebusqueda;

    /**
     * Obtiene el valor de la propiedad autorbus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutorbus() {
        return autorbus;
    }

    /**
     * Define el valor de la propiedad autorbus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutorbus(String value) {
        this.autorbus = value;
    }

    /**
     * Obtiene el valor de la propiedad desbus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesbus() {
        return desbus;
    }

    /**
     * Define el valor de la propiedad desbus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesbus(String value) {
        this.desbus = value;
    }

    /**
     * Obtiene el valor de la propiedad titulobus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulobus() {
        return titulobus;
    }

    /**
     * Define el valor de la propiedad titulobus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulobus(String value) {
        this.titulobus = value;
    }

    /**
     * Obtiene el valor de la propiedad fechabus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechabus() {
        return fechabus;
    }

    /**
     * Define el valor de la propiedad fechabus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechabus(String value) {
        this.fechabus = value;
    }

    /**
     * Obtiene el valor de la propiedad palclavebusqueda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPalclavebusqueda() {
        return palclavebusqueda;
    }

    /**
     * Define el valor de la propiedad palclavebusqueda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPalclavebusqueda(String value) {
        this.palclavebusqueda = value;
    }

}
