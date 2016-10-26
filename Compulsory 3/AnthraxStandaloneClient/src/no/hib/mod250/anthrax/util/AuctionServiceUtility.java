package no.hib.mod250.anthrax.util;

import no.hib.mod250.anthrax.message.NotifyUpdatesMessageListener;
import no.hib.mod250.anthrax.service.AnthraxWebServices;
import no.hib.mod250.anthrax.service.AnthraxWebServicesService;
import no.hib.mod250.anthrax.service.NewBidView;
import no.hib.mod250.anthrax.service.ProductView;

import java.util.List;

/**
 * Created by royne on 26.10.2016.
 */
public class AuctionServiceUtility {
    private List<ProductView> products;
    private AnthraxWebServices service;
    private NotifyUpdatesMessageListener messageListener;

    public AuctionServiceUtility() {
        service = new AnthraxWebServicesService().getAnthraxWebServicesPort();
        messageListener = new NotifyUpdatesMessageListener(this);
        updateList();
    }

    public void updateList() {
        products = service.getActiveAuctions();
    }

    public void placeBid(NewBidView bid) {
        service.bidForAuction(bid);
    }

    public List<ProductView> getProducts() {
        return products;
    }
}

