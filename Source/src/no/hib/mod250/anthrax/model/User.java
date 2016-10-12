package no.hib.mod250.anthrax.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * The User entity class with object relation mapping
 */
@Entity
@Table(name = "useraccount")
public class User {

    @Id
    private String userName;
    private String name;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;
    @OneToMany(mappedBy = "seller")
    private List<Product> products;
    @OneToMany(mappedBy = "bidder")
    private List<Bid> bids;

    public User() {
    }

    public User(String userName, String name, String password) {
        this.userName = userName;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
