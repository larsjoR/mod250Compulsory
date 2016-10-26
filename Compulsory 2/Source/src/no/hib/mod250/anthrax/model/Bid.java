package no.hib.mod250.anthrax.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

/**
 * The Bid entity class with object relation mapping
 */
@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double value;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

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
