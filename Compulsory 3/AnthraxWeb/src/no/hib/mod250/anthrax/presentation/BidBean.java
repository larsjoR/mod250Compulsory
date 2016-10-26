package no.hib.mod250.anthrax.presentation;

import com.sun.deploy.net.HttpRequest;
import no.hib.mod250.anthrax.boundary.*;
import no.hib.mod250.anthrax.exception.HigherBidExistsException;
import no.hib.mod250.anthrax.exception.ProductNotPublishedOrExpiredException;
import no.hib.mod250.anthrax.exception.SellerBiddingOnOwnProductException;
import no.hib.mod250.anthrax.exception.ValueIsBelowStartingPriceException;
import no.hib.mod250.anthrax.model.Bid;
import no.hib.mod250.anthrax.model.Product;
import no.hib.mod250.anthrax.model.User;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed bean for bidding operations and presentation
 */
@ManagedBean
public class BidBean {
    @EJB
    private BidFacade bidFacade;
    @EJB
    private ProductFacade productFacade;
    private Bid bid;
    private int productId;

    /**
     * Public constructor
     */
    public BidBean() {
        bid = new Bid();
    }

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Adds a new bid to the database
     * @param product the product to bid on
     * @throws IOException
     */
    public void addBid(Product product) throws IOException {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        if (username == null || username.isEmpty()) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/account/login.xhtml?return=true");
            return;
        }

        Product p = productFacade.find(productId);
        bid.setBidder(new User(username, null, null, null));
        bid.setTimestamp(Date.from(Instant.now()));
        bid.setProduct(productFacade.find(productId));
        p.getBids().add(bid);

        try {
            bidFacade.addBid(bid);
        } catch (HigherBidExistsException e) {
            FacesContext.getCurrentInstance().addMessage("bidForm:bidValue", new FacesMessage("A higher bid exists for this product."));
            return;

        } catch (ProductNotPublishedOrExpiredException e) {
            FacesContext.getCurrentInstance().addMessage("bidForm:bidValue", new FacesMessage("Product is not available for bidding."));
            return;

        } catch (SellerBiddingOnOwnProductException e) {
            FacesContext.getCurrentInstance().addMessage("bidForm:bidValue", new FacesMessage("Cannot bid on a product you're selling"));
            return;
        } catch (ValueIsBelowStartingPriceException e) {
            FacesContext.getCurrentInstance().addMessage("bidForm:bidValue", new FacesMessage("Bid cannot be lower than the starting value"));
            bid.setValue(product.getStartingPrice());
            return;
        }

        FacesContext.getCurrentInstance().getExternalContext().redirect("product.xhtml?id=" + productId);
    }

    /**
     * Get a string presentation of the highest bid
     * @param bid the bid to present
     * @return the formatted string
     */
    public String highestBidString(Bid bid) {
        if (bid == null) {
            return "no bids placed";
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(bid.getValue());

        if (moneyString.endsWith(".00")) {
            int centsIndex = moneyString.lastIndexOf(".00");
            if (centsIndex != -1) {
                moneyString = moneyString.substring(1, centsIndex);
            }
        }

        return moneyString;
    }

    /**
     * Returns a string representaion of the highest bid on a product
     * @param product the product
     * @return the formatted string
     */
    public String highestBidStringForProduct(Product product) {
        Bid highestBid = bidFacade.findHighestForProduct(product.getId());
        return highestBidString(highestBid);
    }


    /**
     * Get a list of winning bids
     * @return winning bids
     */
    public List<Bid> getWinningBids() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        List<Bid> winningBids =  bidFacade.getWinningBids(username);
        return winningBids;
    }

    /**
     * Get a list of pending bids
     * @return pending bids
     */
    public List<Bid> getPendingBids() {
        return new ArrayList<>();
    }

    /**
     * Get a list of lost bids
     * @return lost bide
     */
    public List<Bid> getLostBids() {
return new ArrayList<>();
    }
}
