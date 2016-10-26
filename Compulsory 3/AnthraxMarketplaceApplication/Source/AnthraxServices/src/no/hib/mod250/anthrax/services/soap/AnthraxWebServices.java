package no.hib.mod250.anthrax.services.soap;

import no.hib.mod250.anthrax.boundary.BidFacade;
import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.model.Bid;
import no.hib.mod250.anthrax.model.Product;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SOAP services for the application
 */
@WebService
public class AnthraxWebServices {
    @EJB
    private BidFacade bidFacade;

    @EJB
    private ProductFacade productFacade;

    public AnthraxWebServices() {
        try {
            String lookupModule = "java:global/AnthraxMarketplace/AnthraxEJB/";
            productFacade = (ProductFacade) InitialContext.doLookup(lookupModule + "ProductFacade");
            bidFacade = (BidFacade) InitialContext.doLookup(lookupModule + "BidFacade");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }


    /**
     * Hello world
     * @param from fromName
     * @return Hello World
     */
    @WebMethod
    public String sayHelloWorldFrom(String from) {
        String result = "Hello, world, from " + from;
        System.out.println(result);
        return result;
    }

    /**
     * Get all active auctions
     * @return List consisting of active auctions
     */
    @WebMethod
    public List<ProductView> getActiveAuctions() {
        List<Product> products = productFacade.findAllActive();

        return products.stream().map(p -> {
            Bid highestBid = bidFacade.findHighestForProduct(p.getId());
            return ProductView.from(p, highestBid != null ? highestBid.getValue() : p.getStartingPrice());
        }).collect(Collectors.toList());
    }

    /**
     * Place a new bid
     * @param newBid The new bid
     * @return the status
     */
    @WebMethod
    public SOAPMessage bidForAuction(NewBidView newBid) {
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();

            AttachmentPart statusCode = soapMessage.createAttachmentPart();
            statusCode.setContentId("StatusCode");

            AttachmentPart message = soapMessage.createAttachmentPart();
            message.setContentId("Message");

            try {
                bidFacade.addBid(newBid.toBid());
                statusCode.setContent("201", "text/plain");
                message.setContent("Customer " + newBid.getBidder() + "'s bid has been successfully placed for product " + newBid.getProduct(), "text/plain");

            } catch (Exception e) {
                statusCode.setContent("500", "text/plain");
                message.setContent("The bid for  " + newBid.getProduct() + " has not been placed for customer " + newBid.getBidder(), "text/plain");

            }

            soapMessage.addAttachmentPart(statusCode);
            soapMessage.addAttachmentPart(message);
            return soapMessage;

        } catch (Exception e) {
            return null;
        }
    }


}
