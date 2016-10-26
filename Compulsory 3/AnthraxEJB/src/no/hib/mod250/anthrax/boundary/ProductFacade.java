package no.hib.mod250.anthrax.boundary;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import no.hib.mod250.anthrax.model.Bid;
import no.hib.mod250.anthrax.model.Product;
import no.hib.mod250.anthrax.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Queue;

/**
 * The product facade class
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product>  {

    @PersistenceContext(unitName = "AnthraxPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    /**
     * Get all products for active auctions, sorted by ending time.
     * @param search the search filter
     * @param category the category filter
     * @param pageNumber the page number
     * @param pageSize the page size
     * @return the products
     */
    public List<Product> findAllActiveSortByClosingTime(String search, String category, int pageNumber, int pageSize) {
        boolean includeCategory = category != null && !category.isEmpty();
        boolean includeSearch = search != null && !search.isEmpty();

        return em.createQuery("" +
                "SELECT p " +
                "FROM Product p " +
                "WHERE p.published = true " +
                    "AND p.endTime > :nowDate " +
                    "AND LOWER(p.category.name) LIKE :category " +
                    "AND LOWER(p.name) LIKE :search " +
                "ORDER BY p.endTime ASC ", Product.class)
                .setParameter("nowDate", Timestamp.from(Instant.now()), TemporalType.TIMESTAMP)
                .setParameter("category", includeCategory ? category.toLowerCase() : "%")
                .setParameter("search", includeSearch ? "%" + search.toLowerCase() + "%" : "%")
                .setFirstResult((pageNumber-1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    /**
     * Get the number of active auctions
     * @param search the search filter
     * @param category the category filter
     * @return the number of actions
     */
    public long activeCount(String search, String category){
        boolean includeCategory = category != null && !category.isEmpty();
        boolean includeSearch = search != null && !search.isEmpty();

        return (long) em.createQuery("" +
                "SELECT COUNT(p.id) " +
                "FROM Product p " +
                "WHERE p.published = true " +
                    "AND p.endTime > :nowDate " +
                    "AND LOWER(p.category.name) LIKE :category " +
                    "AND LOWER(p.name) LIKE :search")
                .setParameter("nowDate", Timestamp.from(Instant.now()), TemporalType.TIMESTAMP)
                .setParameter("category", includeCategory ? category.toLowerCase() : "%")
                .setParameter("search", includeSearch ? "%" + search.toLowerCase() + "%" : "%")
                .getSingleResult();
    }

    /**
     * Add a product to the database
     * @param product the new product
     * @return the product
     */
    public Product addProduct(Product product) {
        this.create(product);
        return product;
    }

    /**
     * Find all products in a users portfolio
     * @param username the username
     * @param pageNumber the page to get
     * @param pageSize the page size
     * @return the products
     */
    public List<Product> findAllByUser(String username, int pageNumber, int pageSize) {
        return em.createQuery("SELECT p FROM Product p WHERE p.seller.userName LIKE :seller ORDER BY p.id DESC", Product.class)
                .setParameter("seller", username)
                .setFirstResult((pageNumber-1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    /**
     * Get the number of products in a users portfolio
     * @param username the username
     * @return the number of products
     */
    public long countByUser(String username) {
        return (long) em.createQuery("SELECT COUNT(p.id) FROM Product p WHERE p.seller.userName LIKE :seller")
                .setParameter("seller", username)
                .getSingleResult();
    }

    /**
     * Set a product's auction status to published
     * @param product the product
     */
    public void setPublished(Product product) {
        Product prod = this.find(product.getId());
        prod.setPublished(true);
        prod.setPublishedTime(new Date(Date.from(Instant.now()).getTime()));
    }

    public List<Product> findAllActive() {
        return em.createQuery("" +
                "SELECT p " +
                "FROM Product p " +
                "WHERE p.published = true " +
                "AND p.endTime > :nowDate " +
                "ORDER BY p.endTime ASC ", Product.class)
                .setParameter("nowDate", Date.from(Instant.now()), TemporalType.TIMESTAMP)
                .getResultList();
    }

    public List<Product> findAllEnded() {
        return em.createQuery("" +
                "SELECT p " +
                "FROM Product p " +
                "WHERE p.endTime < :nowDate " +
                "AND p.published = true ", Product.class)
                .setParameter("nowDate", Date.from(Instant.now()), TemporalType.TIMESTAMP)
                .getResultList();
    }

    public List<Product> findAllEndedAfter(Instant endedAfter) {
        return em.createQuery("" +
                "SELECT p " +
                "FROM Product p " +
                "WHERE p.endTime < :endedDate " +
                "AND p.published = true ", Product.class)
                .setParameter("endedDate", Date.from(endedAfter), TemporalType.TIMESTAMP)
                .getResultList();
    }

    public List<Product> findAllToMarkEnded() {
        return em.createQuery("" +
                "SELECT p " +
                "FROM Product p " +
                "WHERE p.endTime < :nowDate " +
                "AND p.published = true " +
                "AND p.ended = false", Product.class)
                .setParameter("nowDate", Date.from(Instant.now()), TemporalType.TIMESTAMP)
                .getResultList();
    }
}
