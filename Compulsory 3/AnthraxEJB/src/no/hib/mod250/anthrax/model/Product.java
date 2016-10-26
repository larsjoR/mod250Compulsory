package no.hib.mod250.anthrax.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The product entity class with object relation mapping.
 *
 */
@Entity
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String imgPath;
    private String featuresText;
    private Boolean published;
    private Boolean ended = false;
    private Double startingPrice;
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishedTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @ManyToOne
    private Category category;
    @OneToOne
    private Rating rating;
    @ManyToOne
    private User seller;
    @OneToMany
    private List<Bid> bids;

    public Product() {
    }

    public Product(String name, String imgPath, String featuresText, Boolean published, Double startingPrice, Date publishedTime, Date endTime, Category category, Rating rating, User seller, List<Bid> bids) {
        this.name = name;
        this.imgPath = imgPath;
        this.featuresText = featuresText;
        this.published = published;
        this.startingPrice = startingPrice;
        this.publishedTime = publishedTime;
        this.endTime = endTime;
        this.category = category;
        this.rating = rating;
        this.seller = seller;
        this.bids = bids;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getFeaturesText() {
        return featuresText;
    }

    public void setFeaturesText(String featuresText) {
        this.featuresText = featuresText;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlTransient
    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @XmlTransient
    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    /**
     * Method to return the highes bid in db.
     * @return
     */

    public Bid highestBid() {
        if (bids == null || bids.isEmpty()) {
            return null;
        }
        if (bids.size() == 1) {
            return bids.get(0);
        }

        return Collections.max(bids, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
    }



}
