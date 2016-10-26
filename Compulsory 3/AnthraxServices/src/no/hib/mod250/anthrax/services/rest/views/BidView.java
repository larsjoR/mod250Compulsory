package no.hib.mod250.anthrax.services.rest.views;

import java.util.Date;

/**
 *  View used to display the relevant properties of the bid object
 */
public class BidView {

    private String bidder;
    private Double bidValue;
    private Date timestamp;
    private Integer product;

    public BidView(String bidder, Double bidValue, Date timestamp, Integer product) {
        this.bidder = bidder;
        this.bidValue = bidValue;
        this.timestamp = timestamp;
        this.product = product;
    }

   

    public BidView() {}

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public Double getBidValue() {
        return bidValue;
    }

    public void setBidValue(Double bidValue) {
        this.bidValue = bidValue;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
