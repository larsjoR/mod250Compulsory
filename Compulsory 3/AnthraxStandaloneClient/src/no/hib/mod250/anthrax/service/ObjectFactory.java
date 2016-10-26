
package no.hib.mod250.anthrax.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the no.hib.mod250.anthrax.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _User_QNAME = new QName("http://soap.services.anthrax.mod250.hib.no/", "user");
    private final static QName _SayHelloWorldFrom_QNAME = new QName("http://soap.services.anthrax.mod250.hib.no/", "sayHelloWorldFrom");
    private final static QName _GetActiveAuctionsResponse_QNAME = new QName("http://soap.services.anthrax.mod250.hib.no/", "getActiveAuctionsResponse");
    private final static QName _SayHelloWorldFromResponse_QNAME = new QName("http://soap.services.anthrax.mod250.hib.no/", "sayHelloWorldFromResponse");
    private final static QName _BidForAuctionResponse_QNAME = new QName("http://soap.services.anthrax.mod250.hib.no/", "bidForAuctionResponse");
    private final static QName _BidForAuction_QNAME = new QName("http://soap.services.anthrax.mod250.hib.no/", "bidForAuction");
    private final static QName _GetActiveAuctions_QNAME = new QName("http://soap.services.anthrax.mod250.hib.no/", "getActiveAuctions");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: no.hib.mod250.anthrax.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BidForAuction }
     * 
     */
    public BidForAuction createBidForAuction() {
        return new BidForAuction();
    }

    /**
     * Create an instance of {@link GetActiveAuctions }
     * 
     */
    public GetActiveAuctions createGetActiveAuctions() {
        return new GetActiveAuctions();
    }

    /**
     * Create an instance of {@link BidForAuctionResponse }
     * 
     */
    public BidForAuctionResponse createBidForAuctionResponse() {
        return new BidForAuctionResponse();
    }

    /**
     * Create an instance of {@link GetActiveAuctionsResponse }
     * 
     */
    public GetActiveAuctionsResponse createGetActiveAuctionsResponse() {
        return new GetActiveAuctionsResponse();
    }

    /**
     * Create an instance of {@link SayHelloWorldFromResponse }
     * 
     */
    public SayHelloWorldFromResponse createSayHelloWorldFromResponse() {
        return new SayHelloWorldFromResponse();
    }

    /**
     * Create an instance of {@link SayHelloWorldFrom }
     * 
     */
    public SayHelloWorldFrom createSayHelloWorldFrom() {
        return new SayHelloWorldFrom();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link ProductView }
     * 
     */
    public ProductView createProductView() {
        return new ProductView();
    }

    /**
     * Create an instance of {@link NewBidView }
     * 
     */
    public NewBidView createNewBidView() {
        return new NewBidView();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.anthrax.mod250.hib.no/", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloWorldFrom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.anthrax.mod250.hib.no/", name = "sayHelloWorldFrom")
    public JAXBElement<SayHelloWorldFrom> createSayHelloWorldFrom(SayHelloWorldFrom value) {
        return new JAXBElement<SayHelloWorldFrom>(_SayHelloWorldFrom_QNAME, SayHelloWorldFrom.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActiveAuctionsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.anthrax.mod250.hib.no/", name = "getActiveAuctionsResponse")
    public JAXBElement<GetActiveAuctionsResponse> createGetActiveAuctionsResponse(GetActiveAuctionsResponse value) {
        return new JAXBElement<GetActiveAuctionsResponse>(_GetActiveAuctionsResponse_QNAME, GetActiveAuctionsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloWorldFromResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.anthrax.mod250.hib.no/", name = "sayHelloWorldFromResponse")
    public JAXBElement<SayHelloWorldFromResponse> createSayHelloWorldFromResponse(SayHelloWorldFromResponse value) {
        return new JAXBElement<SayHelloWorldFromResponse>(_SayHelloWorldFromResponse_QNAME, SayHelloWorldFromResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BidForAuctionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.anthrax.mod250.hib.no/", name = "bidForAuctionResponse")
    public JAXBElement<BidForAuctionResponse> createBidForAuctionResponse(BidForAuctionResponse value) {
        return new JAXBElement<BidForAuctionResponse>(_BidForAuctionResponse_QNAME, BidForAuctionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BidForAuction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.anthrax.mod250.hib.no/", name = "bidForAuction")
    public JAXBElement<BidForAuction> createBidForAuction(BidForAuction value) {
        return new JAXBElement<BidForAuction>(_BidForAuction_QNAME, BidForAuction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetActiveAuctions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.services.anthrax.mod250.hib.no/", name = "getActiveAuctions")
    public JAXBElement<GetActiveAuctions> createGetActiveAuctions(GetActiveAuctions value) {
        return new JAXBElement<GetActiveAuctions>(_GetActiveAuctions_QNAME, GetActiveAuctions.class, null, value);
    }

}
