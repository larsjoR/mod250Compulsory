package no.hib.mod250.anthrax.model;

import javax.persistence.*;

/**
 * The Rating entity class with object relation mapping
 */
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer value;

    @OneToOne(mappedBy = "rating")
    private Product product;

    @ManyToOne
    private User user;

    public Rating() {
    }

    public Rating(Product product, User user) {
        this.product = product;
        this.user = user;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
