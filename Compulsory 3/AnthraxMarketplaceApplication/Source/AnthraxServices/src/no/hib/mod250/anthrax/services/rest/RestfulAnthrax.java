package no.hib.mod250.anthrax.services.rest;


import no.hib.mod250.anthrax.boundary.BidFacade;
import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.boundary.UserFacade;
import no.hib.mod250.anthrax.exception.HigherBidExistsException;
import no.hib.mod250.anthrax.exception.ProductNotPublishedOrExpiredException;
import no.hib.mod250.anthrax.exception.SellerBiddingOnOwnProductException;
import no.hib.mod250.anthrax.exception.ValueIsBelowStartingPriceException;
import no.hib.mod250.anthrax.model.Bid;
import no.hib.mod250.anthrax.model.Product;
import no.hib.mod250.anthrax.model.User;
import no.hib.mod250.anthrax.services.rest.views.BidView;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *  Bid service class with all currently implemented bid-endpoints.
 *  Implemented services for GET,PUT and POST
 */

@Path("/bid")
public class RestfulAnthrax {

    @EJB
    private BidFacade bidFacade;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private UserFacade userFacade;

    /**
     * Method to get all bids
     * @return the bids
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/all")
    public Response getAllBids() {
        List<Bid> bids = bidFacade.findAll();
        List<BidView> bidView = bids.stream().map(bidToBidView).collect(Collectors.toList());

        GenericEntity<List<BidView>> bidwrapper = new GenericEntity<List<BidView>>(bidView){};

        return Response.ok(bidwrapper).build();
    }


    /**
     * Method for getting all bids for a specific product
     * @param id The product id
     * @return All bids
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/{id}/all")
    public Response getBidsForProduct(@PathParam("id") int id) {
        List<Bid> bids =  bidFacade.findAllBidsForProduct(id);
        List<BidView> bidView = bids.stream().map(bidToBidView).collect(Collectors.toList());

        GenericEntity<List<BidView>> bidWrapper = new GenericEntity<List<BidView>>(bidView){};
        return Response.ok(bidWrapper).build();
    }

    /**
     * Method for updating a product.
     * @param id the product id
     * @param bv the bidView
     * @return 404 if product could not be found, 200 OK otherwise.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/{id}")
    public Response updateBid(@PathParam("id") int id, BidView bv){
        Bid bid;

        try {
            bid = bidFacade.find(id);
            bid.setValue(bv.getBidValue());
            bid.setTimestamp(Date.from(Instant.now()));
            bidFacade.edit(bid);

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).build();
    }

    /**
     * Method for posting a new bid
     * @param bv the new bidView
     * @return 400 - Bad request if bid is incomplete, 200 OK and bid if ok.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/add")
    public Response postBid(BidView bv) {
        Bid bid;
        User user;
        Product product;

        try {
            product = productFacade.find(bv.getProduct());
            user = userFacade.find(bv.getBidder());
            bid = new Bid(bv.getBidValue(),Date.from(Instant.now()),user,product);

            bidFacade.addBid(bid);

        } catch (SellerBiddingOnOwnProductException | ValueIsBelowStartingPriceException | ProductNotPublishedOrExpiredException | HigherBidExistsException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok(new GenericEntity<Bid>(bid){}).build();
    }

        // Helper lambda to convert from bid to BidView
        // Used in 'getAllBids' and 'getBidsForProduct'
        private Function<Bid,BidView> bidToBidView = b -> {
        BidView bidView = new BidView();
        bidView.setBidder(String.valueOf(b.getBidder().getUserName()));
        bidView.setBidValue(b.getValue());
        bidView.setTimestamp(b.getProduct().getEndTime());
        bidView.setProduct(b.getProduct().getId());
        return bidView;
    };
}
