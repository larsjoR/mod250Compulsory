package no.hib.mod250.anthrax.message;

import no.hib.mod250.anthrax.model.Product;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Receives messages when auctions ends, and sends an email to the auction winners
 */
@MessageDriven(
        name = "NotifyBuyerEJB",
        mappedName = "jms/anthraxAuctions", activationConfig =
        {
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        })
public class NotifyBuyerBean implements MessageListener {
    @Resource private MessageDrivenContext mdctx;
    @EJB private EmailSender emailSender;

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Product product = (Product) objectMessage.getObject();
            emailSender.sendEmail(product);

        } catch (JMSException e) {
            System.out.println("My MDB got message: invalid message");
        }


    }



}
