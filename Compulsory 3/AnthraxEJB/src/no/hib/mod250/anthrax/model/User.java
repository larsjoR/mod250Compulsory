package no.hib.mod250.anthrax.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The User entity class with object relation mapping
 */
@Entity
@XmlRootElement
@Table(name = "useraccount")
public class User implements Serializable {
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

    @ManyToMany
    private List<UserGroup> userGroups;

    public User() {
    }

    public User(String userName, String name, String password, List<UserGroup> userGroups) {
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.userGroups = userGroups;
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

    @XmlTransient
    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @XmlTransient
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @XmlTransient
    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }
}
