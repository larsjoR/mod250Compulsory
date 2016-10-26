package no.hib.mod250.anthrax.services.rest;


import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.model.Product;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *  Simple service class for auctions
 *  Only implemented GET (getActiveAuctions)
 */
@Path("/auction")
public class AuctionService {

    @EJB
    private ProductFacade productFacade;

    /**
     * Method for getting all active auctions
     * @return the auctions
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/active")
    public Response getActiveAuctions() {
        List<Product> products = productFacade.findAllActive();
        GenericEntity<List<Product>> productWrapper = new GenericEntity<List<Product>>(products){};

        return Response.ok(productWrapper).build();
    }

}
