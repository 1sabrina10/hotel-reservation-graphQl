
package org.example.agence.ws.hotelconsultation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour chambreDTO complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="chambreDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adresse" type="{http://service.hotelservice.web_services.example.org/}adresse" minOccurs="0"/>
 *         &lt;element name="etoiles" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hasOffer" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="nomHotel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreLits" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prix" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chambreDTO", propOrder = {
    "adresse",
    "etoiles",
    "hasOffer",
    "id",
    "image",
    "nomHotel",
    "nombreLits",
    "prix",
    "type"
})
public class ChambreDTO {

    protected Adresse adresse;
    protected int etoiles;
    protected boolean hasOffer;
    protected Long id;
    protected byte[] image;
    protected String nomHotel;
    protected int nombreLits;
    protected double prix;
    protected String type;

    /**
     * Obtient la valeur de la propriété adresse.
     * 
     * @return
     *     possible object is
     *     {@link Adresse }
     *     
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * Définit la valeur de la propriété adresse.
     * 
     * @param value
     *     allowed object is
     *     {@link Adresse }
     *     
     */
    public void setAdresse(Adresse value) {
        this.adresse = value;
    }

    /**
     * Obtient la valeur de la propriété etoiles.
     * 
     */
    public int getEtoiles() {
        return etoiles;
    }

    /**
     * Définit la valeur de la propriété etoiles.
     * 
     */
    public void setEtoiles(int value) {
        this.etoiles = value;
    }

    /**
     * Obtient la valeur de la propriété hasOffer.
     * 
     */
    public boolean isHasOffer() {
        return hasOffer;
    }

    /**
     * Définit la valeur de la propriété hasOffer.
     * 
     */
    public void setHasOffer(boolean value) {
        this.hasOffer = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété image.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Définit la valeur de la propriété image.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImage(byte[] value) {
        this.image = value;
    }

    /**
     * Obtient la valeur de la propriété nomHotel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomHotel() {
        return nomHotel;
    }

    /**
     * Définit la valeur de la propriété nomHotel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomHotel(String value) {
        this.nomHotel = value;
    }

    /**
     * Obtient la valeur de la propriété nombreLits.
     * 
     */
    public int getNombreLits() {
        return nombreLits;
    }

    /**
     * Définit la valeur de la propriété nombreLits.
     * 
     */
    public void setNombreLits(int value) {
        this.nombreLits = value;
    }

    /**
     * Obtient la valeur de la propriété prix.
     * 
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Définit la valeur de la propriété prix.
     * 
     */
    public void setPrix(double value) {
        this.prix = value;
    }

    /**
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
