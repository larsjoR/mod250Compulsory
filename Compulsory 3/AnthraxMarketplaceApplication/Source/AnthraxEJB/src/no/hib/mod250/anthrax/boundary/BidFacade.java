package no.hib.mod250.anthrax.boundary;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import no.hib.mod250.anthrax.exception.HigherBidExistsException;
import no.hib.mod250.anthrax.exception.ProductNotPublishedOrExpiredException;
import no.hib.mod250.anthrax.exception.SellerBiddingOnOwnProductException;
import no.hib.mod250.anthrax.exception.ValueIsBelowStartingPriceException;
import no.hib.mod250.anthrax.model.Bid;
import no.hib.mod250.anthrax.model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * The Bid facade with the injected persitence unit
 */
@Stateless
public class BidFacade extends AbstractFacade<Bid> {

    @PersistenceContext(unitName = "AnthraxPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BidFacade() {
        super(Bid.class);
    }


    /**
     * Finds the highest bid for the current product
     * @param productId The id of the product
     * @return The highest bid
     */
    public Bid findHighestForProduct(int productId) {
        Bid result;

        try {
            result = em.createQuery("SELECT b FROM Bid b WHERE b.product.id = :productId ORDER BY b.value DESC", Bid.class)
                    .setParameter("productId", productId)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }

        return result;
    }

    /**
     * Finds all bids for current product
     * @param productId The id of the product
     * @return All bids for the current product
     */
    public List<Bid> findAllBidsForProduct(int productId) {
        return em.createQuery("SELECT b FROM Bid b WHERE b.product.id = :productId ORDER BY b.value DESC", Bid.class)
                .setParameter("productId",productId)
                .setMaxResults(50)
                .getResultList();
    }


    /**
     * Adds a new bid to a product.
     * Throws exceptions if higher bids are placed, products are not published,
     * products are expired, if the seller is bidding on its own product or if
     * the value of the bid is lower then the starting price.
     * @param bid the bid to be added
     * @throws HigherBidExistsException
     * @throws ProductNotPublishedOrExpiredException
     * @throws SellerBiddingOnOwnProductException
     * @throws ValueIsBelowStartingPriceException
     */
    public void addBid(Bid bid) throws HigherBidExistsException, ProductNotPublishedOrExpiredException, SellerBiddingOnOwnProductException, ValueIsBelowStartingPriceException {
        Product product = em.find(Product.class, bid.getProduct().getId());
        Bid highestBid = findHighestForProduct(product.getId());

        if (product.getSeller().getUserName().equals(bid.getBidder().getUserName())) {
            throw new SellerBiddingOnOwnProductException();

        } else if(highestBid == null){
            if (bid.getValue() < product.getStartingPrice()) {
                throw new ValueIsBelowStartingPriceException();
            }

        } else if (highestBid.getValue() >= bid.getValue()) {
            throw new HigherBidExistsException();

        } else if (!product.getPublished() || product.getEndTime().toInstant().compareTo(Instant.now()) < 0) {
            throw new ProductNotPublishedOrExpiredException();
        }

        create(bid);
    }

    /**
     * Gets a list of all the winning bids for a user
     * @param username the username
     * @return The list of winning bids
     */
    public List<Bid> getWinningBids(String username) {
        return em.createQuery("SELECT DISTINCT b FROM Bid b WHERE b.bidder.userName = :userName " +
                "AND b.product.published = true AND b.product.endTime < :now " +
                "ORDER BY b.value DESC", Bid.class)
                .setParameter("now", Date.from(Instant.now()))
                .setParameter("userName", username)
                .getResultList();
    }


}
