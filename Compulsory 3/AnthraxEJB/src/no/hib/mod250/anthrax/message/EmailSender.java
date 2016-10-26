package no.hib.mod250.anthrax.message;

import no.hib.mod250.anthrax.boundary.BidFacade;
import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.model.Bid;
import no.hib.mod250.anthrax.model.Product;

import javax.ejb.EJB;
import javax.ejb.Singleton;

/**
 * Created by royne on 25.10.2016.
 */
@Singleton
public class EmailSender {
    @EJB
    private ProductFacade productFacade;

    @EJB
    private BidFacade bidFacade;

    public void sendEmail(Product p) {
        Product persistentProduct = productFacade.find(p.getId());
        persistentProduct.setEnded(true);
        productFacade.edit(persistentProduct);
        Bid highestBid = bidFacade.findHighestForProduct(persistentProduct.getId());

        if (highestBid == null) {
            System.out.println("Auction for product '" + persistentProduct.getName() + "' ended with no bids placed.");
        } else {
            System.out.println(
                    "---- START EMAIL to customer " + highestBid.getBidder().getName() + " ----\n" +
                    "Dear " + highestBid.getBidder().getName() + ",\n" +
                    "Congratulations! You have won in bidding for product '" + persistentProduct.getName() + "'.\n" +
                    "You can access the product using the following link:\n" +
                    "URL=https://localhost:8080/product.xhtml?id=" + persistentProduct.getId() + "\n" +
                    "---- END EMAIL to customer " + highestBid.getBidder().getName() + " ----");
        }


    }
}
