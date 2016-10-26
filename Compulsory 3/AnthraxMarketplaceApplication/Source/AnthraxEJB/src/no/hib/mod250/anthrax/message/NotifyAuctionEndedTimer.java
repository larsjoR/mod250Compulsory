package no.hib.mod250.anthrax.message;

import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.model.Product;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.jms.*;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class for sending messages when auctions ends
 */
@Singleton
@MessageDriven(
        name = "NotifyAuctionEndedTimer",
        mappedName = "jms/anthraxAuctions", activationConfig =
        {
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        })
public class NotifyAuctionEndedTimer {
    @Resource
    TimerService timerService;

    @Resource(lookup = "jms/anthraxDestinationFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/anthraxAuctions")
    private Topic topic;

    @EJB
    ProductFacade productFacade;

    Destination dest;
    private Instant lastAutomaticTimeout;
    private Logger logger = Logger.getLogger("no.hib.mod250.anthrax.services.message.NotifyBuyer");

    /**
     * Check for new ended auctions every 30 seconds
     */
    @Schedule(second = "*/30", minute = "*", hour = "*")
    public void automaticTimeout() {
        this.setLastAutomaticTimeout(Instant.now());
        logger.info("Checking if auctions have ended...");

        sendMessage();
    }

    /**
     * Finds ended auctions and sends a message to the topic
     */
    private void sendMessage() {
        List<Product> endedAuctions = productFacade.findAllToMarkEnded();

        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            dest = (Destination) topic;
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(dest);

            for (Product p : endedAuctions) {
                ObjectMessage message = session.createObjectMessage();
                message.setObject(p);
                producer.send(message);
            }

        } catch (JMSException e) {
//            e.printStackTrace();
        } finally {
            try {
                producer.close();
                session.close();
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the time since the last automatic timeout
     * @return
     */
    public String getLastAutomaticTimeout() {
        if (lastAutomaticTimeout != null) {
            return lastAutomaticTimeout.toString();
        } else {
            return "never";
        }
    }

    private void setLastAutomaticTimeout(Instant lastAutomaticTimeout) {
        this.lastAutomaticTimeout = lastAutomaticTimeout;
    }
}

