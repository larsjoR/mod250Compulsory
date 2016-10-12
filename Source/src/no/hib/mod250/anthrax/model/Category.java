package no.hib.mod250.anthrax.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.List;

/**
 * The Category entity class with object relation mapping.
 */
@Entity
public class Category {

    @Id
    private String name;
    @OneToMany
    private List<Product> products;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
