package no.hib.mod250.anthrax.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;

/**
 * The Bid entity class with object relation mapping
 */
@Entity
@XmlRootElement
public class Bid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double value;
    private Boolean bidderNotified = false;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @ManyToOne
    private User bidder;
    @ManyToOne
    private Product product;


    public Bid() {
    }

    public Bid(Double value, Date timestamp, User bidder, Product product) {
        this.value = value;
        this.timestamp = timestamp;
        this.bidder = bidder;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getBidderNotified() {
        return bidderNotified;
    }

    public void setBidderNotified(Boolean bidderNotified) {
        this.bidderNotified = bidderNotified;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @XmlTransient
    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
