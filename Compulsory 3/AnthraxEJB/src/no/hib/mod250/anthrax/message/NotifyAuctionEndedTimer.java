package no.hib.mod250.anthrax.message;

import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.model.Product;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.ejb.*;
import javax.jms.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by royne on 25.10.2016.
 */
@Singleton
@MessageDriven(
        name = "NotifyAuctionEndedTimer",
        mappedName = "jms/anthraxAuctions", activationConfig =
        {
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
//                @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
//                @ActivationConfigProperty(propertyName = "clientId", propertyValue = "NotifyAuctionEndedTimer"),
//                @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "NotifyAuctionEndedTimer")
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

    @Schedule(second = "*/30", minute = "*", hour = "*")
    public void automaticTimeout() {
        this.setLastAutomaticTimeout(Instant.now());
        logger.info("Checking if auctions have ended...");


        sendMessage();


    }

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
//                Thread.sleep(1000);
            }



        } catch (JMSException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
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


    public String getLastAutomaticTimeout() {
        if (lastAutomaticTimeout != null) {
            return lastAutomaticTimeout.toString();
        } else {
            return "never";
        }
    }



    public void setLastAutomaticTimeout(Instant lastAutomaticTimeout) {
        this.lastAutomaticTimeout = lastAutomaticTimeout;
    }
}

