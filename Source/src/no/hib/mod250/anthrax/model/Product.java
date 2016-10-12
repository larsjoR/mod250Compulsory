package no.hib.mod250.anthrax.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

/**
 * The product entity class with object relation mapping.
 *
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String imgPath;
    private String featuresText;
    private Boolean published;
    private Double startingPrice;
    private Timestamp publishedTime;
    private Timestamp endTime;
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

    public Product(String name, String imgPath, String featuresText, Boolean published, Double startingPrice, Timestamp publishedTime, Timestamp endTime, Category category, Rating rating, User seller, List<Bid> bids) {
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

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Timestamp getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Timestamp publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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
        if (bids.isEmpty()) {
            return null;
        }
        if (bids.size() == 1) {
            return bids.get(0);
        }

        return Collections.max(bids, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
    }

}
