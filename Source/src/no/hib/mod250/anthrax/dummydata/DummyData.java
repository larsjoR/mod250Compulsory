package no.hib.mod250.anthrax.dummydata;

import no.hib.mod250.anthrax.model.Bid;
import no.hib.mod250.anthrax.model.Category;
import no.hib.mod250.anthrax.model.Product;
import no.hib.mod250.anthrax.model.User;

import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Class to seed the database with dummy data.
 */
@Stateless
public class DummyData {
    @PersistenceContext(unitName = "AnthraxPU")
    private EntityManager em;

    private User finn;
    private User tore;
    private User timnina;
    private User malin;
    Random r = new Random();

    public void seedDatabase() {
        if (em.find(User.class, "Finn") != null) {
            return;
        }

        finn = new User("finn", "Finn Hyllesleik", "finn");
        tore = new User("tore", "Tore Hyllesleik", "tore");
        timnina = new User("timnina", "Tim Nina Hyllesleik", "timnina");
        malin = new User("malin", "Malin Hyllesleik", "malin");

        List<User> users = new ArrayList<User>() {{add(finn); add(tore); add(timnina); add(malin);}};

        Category animals = new Category("Animals");
        Category furniture = new Category("Furniture");
        Category tools = new Category("Tools");
        Category movies = new Category("Movies");

        Product cat = new Product("Cat", "cat.jpg", "Nice cat", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 20, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product dog = new Product("Dog", "dog.jpg", "Nice dog", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 21, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product rabbit = new Product("Rabbit", "rabbit.jpg", "Nice rabbit", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 22, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product snake = new Product("Snake", "snek.jpg", "Nice snake", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 23, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product hammer = new Product("Hammer", "hammer.jpg", "Nice hammer", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 20, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product saw = new Product("Saw", "saw.jpg", "Nice saw", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 21, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product nails = new Product("Nails", "nails.jpg", "Nice nails", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 22, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product level = new Product("Level", "level.jpg", "Nice level", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 23, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product harrypotter = new Product("Harry Potter", "harrypotter.jpg", "Nice Harry Potter", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 20, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product gladiator = new Product("Gladiator", "gladiator.jpg", "Nice gladiator", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 21, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product chair = new Product("Chair", "chair.jpg", "Nice chair", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 22, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product table = new Product("Table", "table.jpg", "Nice table", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 12, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 23, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        finn.setProducts(new ArrayList<Product>(){{add(gladiator);add(chair);add(table);}});
        tore.setProducts(new ArrayList<Product>(){{add(snake);add(hammer);add(saw);}});
        timnina.setProducts(new ArrayList<Product>(){{add(dog);add(rabbit);add(harrypotter);}});
        malin.setProducts(new ArrayList<Product>(){{add(nails);add(cat);add(level);}});

        List<Product> products = new ArrayList<Product>(){{add(cat);add(dog);add(rabbit);add(snake);add(hammer);add(saw);add(nails);add(level);add(harrypotter);add(gladiator);add(chair);add(table);}};


        em.persist(animals);
        em.persist(furniture);
        em.persist(tools);
        em.persist(movies);

        em.persist(finn);
        em.persist(tore);
        em.persist(timnina);
        em.persist(malin);

        for (Product p: products) {
            em.persist(p);
        }

        for (int i = 0; i < 25; i++) {
            Product randomProduct = products.get(r.nextInt(products.size()));
            Bid toAdd = generateDummyBids(randomProduct, users);

            if (toAdd == null){
                continue;
            }

            List<Bid> bids = randomProduct.getBids() == null ? new ArrayList<Bid>() : randomProduct.getBids();
            bids.add(toAdd);
            em.persist(toAdd);
        }





    }


    private Bid generateDummyBids(Product product, List<User> users) {

        Bid ret = new Bid();
        ret.setProduct(product);
        ret.setTimestamp(Timestamp.from(Instant.now()));

        if (r.nextInt() % 3  != 0) {
            ret.setBidder(users.get(r.nextInt(users.size())));

            if (product.highestBid() == null) {
                ret.setValue(product.getStartingPrice() + abs(r.nextInt() % 200));
            } else {
                ret.setValue(product.highestBid().getValue() + abs(r.nextInt() % 200));
            }
        } else {
            return null;
        }

        return ret;
    }
}
