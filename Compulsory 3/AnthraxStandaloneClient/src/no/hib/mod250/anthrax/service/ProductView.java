
package no.hib.mod250.anthrax.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * <p>Java class for productView complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productView">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentBid" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ended" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="featuresText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="published" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="publishedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="seller" type="{http://soap.services.anthrax.mod250.hib.no/}user" minOccurs="0"/>
 *         &lt;element name="startingPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productView", propOrder = {
    "category",
    "currentBid",
    "endTime",
    "ended",
    "featuresText",
    "id",
    "name",
    "published",
    "publishedTime",
    "seller",
    "startingPrice"
})
public class ProductView {

    protected String category;
    protected Double currentBid;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    protected Boolean ended;
    protected String featuresText;
    protected Integer id;
    protected String name;
    protected Boolean published;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publishedTime;
    protected User seller;
    protected Double startingPrice;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the currentBid property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCurrentBid() {
        return currentBid;
    }

    /**
     * Sets the value of the currentBid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCurrentBid(Double value) {
        this.currentBid = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the ended property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnded() {
        return ended;
    }

    /**
     * Sets the value of the ended property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnded(Boolean value) {
        this.ended = value;
    }

    /**
     * Gets the value of the featuresText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeaturesText() {
        return featuresText;
    }

    /**
     * Sets the value of the featuresText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeaturesText(String value) {
        this.featuresText = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the published property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPublished() {
        return published;
    }

    /**
     * Sets the value of the published property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPublished(Boolean value) {
        this.published = value;
    }

    /**
     * Gets the value of the publishedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPublishedTime() {
        return publishedTime;
    }

    /**
     * Sets the value of the publishedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPublishedTime(XMLGregorianCalendar value) {
        this.publishedTime = value;
    }

    /**
     * Gets the value of the seller property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getSeller() {
        return seller;
    }

    /**
     * Sets the value of the seller property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setSeller(User value) {
        this.seller = value;
    }

    /**
     * Gets the value of the startingPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStartingPrice() {
        return startingPrice;
    }

    /**
     * Sets the value of the startingPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStartingPrice(Double value) {
        this.startingPrice = value;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return String.format("%-12s%-25s%-25s%-30s%-15.2f%22s", id, name, category, seller.getName(), currentBid, df.format(toDate(endTime)));
    }

    private static Date toDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }
}
