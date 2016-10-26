package no.hib.mod250.anthrax.services.soap;

import no.hib.mod250.anthrax.model.Product;
import no.hib.mod250.anthrax.model.User;

import java.util.Date;

/**
 *  View used to display the relevant properties of a product
 */
public class ProductView {
    private Integer id;
    private String name;
    private String featuresText;
    private Boolean published;
    private Boolean ended = false;
    private Double startingPrice;
    private Date publishedTime;
    private Date endTime;
    private String category;
    private User seller;
    private Double currentBid;


    public ProductView() {
    }

    public ProductView(Integer id, String name, String featuresText, Boolean published, Boolean ended, Double startingPrice, Date publishedTime, Date endTime, String category, User seller, Double highestBid) {
        this.id = id;
        this.name = name;
        this.featuresText = featuresText;
        this.published = published;
        this.ended = ended;
        this.startingPrice = startingPrice;
        this.publishedTime = publishedTime;
        this.endTime = endTime;
        this.category = category;
        this.seller = seller;
        this.currentBid = highestBid;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Double getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(Double currentBid) {
        this.currentBid = currentBid;
    }

    /**
     * Static method for creating a ProductView from a product and a bid value
     * @param product the product
     * @param currentBid the bid value
     * @return the product view
     */
    public static ProductView from(Product product, Double currentBid) {
       return new ProductView(product.getId(), product.getName(),product.getFeaturesText(),product.getPublished(),product.getEnded(),product.getStartingPrice(),product.getPublishedTime(),product.getEndTime(),product.getCategory().getName(),product.getSeller(),currentBid);
    }
}
