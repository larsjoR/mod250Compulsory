package no.hib.mod250.anthrax.message;

import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.Topic;
import no.hib.mod250.anthrax.util.AuctionServiceUtility;

import javax.jms.*;

/**
 * Created by royne on 25.10.2016.
 */
public class NotifyUpdatesMessageListener implements MessageListener {
    private AuctionServiceUtility serviceUtility;

    public NotifyUpdatesMessageListener(AuctionServiceUtility serviceUtility) {
        this.serviceUtility = serviceUtility;

        try {
            Topic myTopic = new Topic("anthraxAuctions");
            ConnectionFactory connFactory = new ConnectionFactory();
            Connection connection = connFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer mc = session.createConsumer(myTopic);

            mc.setMessageListener(this);
            connection.start();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Message received, updating product list....");
        serviceUtility.updateList();
    }



}
