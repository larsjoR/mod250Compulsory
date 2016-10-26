package no.hib.mod250.anthrax.services.soap;

import no.hib.mod250.anthrax.model.Bid;
import no.hib.mod250.anthrax.model.Product;
import no.hib.mod250.anthrax.model.User;

import java.util.Date;

/**
 *  View used to display the relevant properties of a "new bid" object
 */
public class NewBidView {
    private double value;
    private Date timestamp;
    private String bidder;
    private int product;

    public NewBidView() {
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    /**
     * Convert the bid view to a Bid object
     * @return the bid object
     */
    public Bid toBid() {
        Product product = new Product();
        product.setId(this.product);

        User user = new User();
        user.setUserName(bidder);

        return new Bid(value, timestamp, user, product);
    }
}
