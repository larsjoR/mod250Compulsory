package no.hib.mod250.anthrax.dummydata;

import no.hib.mod250.anthrax.model.*;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

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
    private User admin;
    Random r = new Random();

    public void seedDatabase() {
        if (em.find(User.class, "Finn") != null) {
            return;
        }

        UserGroup userGroup = new UserGroup();
        userGroup.setGroupId("Users");

        ArrayList<UserGroup> userGroupList = new ArrayList<>();
        userGroupList.add(userGroup);

        admin = new User("admin", "Admin", createPasswordHash("admin"), userGroupList);
        finn = new User("finn", "Finn Hyllesleik", createPasswordHash("finn"), userGroupList);
        tore = new User("tore", "Tore Hyllesleik", createPasswordHash("tore"), userGroupList);
        timnina = new User("timnina", "Tim Nina Hyllesleik", createPasswordHash("timnina"), userGroupList);
        malin = new User("malin", "Malin Hyllesleik", createPasswordHash("malin"), userGroupList);


        List<User> users = new ArrayList<User>();
        users.add(finn);
        users.add(tore);
        users.add(timnina);
        users.add(malin);

        Category animals = new Category("Animals");
        Category furniture = new Category("Furniture");
        Category tools = new Category("Tools");
        Category movies = new Category("Movies");

        Product cat = new Product("Cat", "cat.jpg", "Nice cat", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, timnina, null);
        Product dog = new Product("Dog", "dog.jpg", "Nice dog", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, malin, null);
        Product rabbit = new Product("Rabbit", "rabbit.jpg", "Nice rabbit", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, malin, null);
        Product snake = new Product("Snake", "snek.jpg", "Nice snake", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, timnina, null);
        Product hammer = new Product("Hammer", "hammer.jpg", "Nice hammer", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, timnina, null);
        Product saw = new Product("Saw", "saw.jpg", "Nice saw", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, timnina, null);
        Product nails = new Product("Nails", "nails.jpg", "Nice nails", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, timnina, null);
        Product level = new Product("Level", "level.jpg", "Nice level", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, timnina, null);
        Product harrypotter = new Product("Harry Potter", "harrypotter.jpg", "Nice Harry Potter", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, malin, null);
        Product gladiator = new Product("Gladiator", "gladiator.jpg", "Nice gladiator", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, timnina, null);
        Product chair = new Product("Chair", "chair.jpg", "Nice chair", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, timnina, null);
        Product table = new Product("Table", "table.jpg", "Nice table", true, 10.0, getRandomDateBefore(Instant.now(), 36000), getRandomDateAfter(Instant.now(), 1800), animals, null, timnina, null);
        finn.setProducts(Arrays.asList(gladiator, chair, table));
        tore.setProducts(Arrays.asList(snake, hammer, saw));
        timnina.setProducts(Arrays.asList(dog, rabbit, harrypotter));
        malin.setProducts(Arrays.asList(nails, cat, level));

        List<Product> products = Arrays.asList(cat, dog, rabbit, snake, hammer, saw, nails, level, harrypotter, gladiator, chair, table);

        em.persist(animals);
        em.persist(furniture);
        em.persist(tools);
        em.persist(movies);

        em.persist(finn);
        em.persist(tore);
        em.persist(timnina);
        em.persist(malin);
        em.persist(admin);

        em.persist(userGroup);

        for (Product p : products) {
            em.persist(p);
        }

        for (int i = 0; i < 25; i++) {
            Product randomProduct = products.get(r.nextInt(products.size()));
            Bid toAdd = generateDummyBids(randomProduct, users);

            if (toAdd == null) {
                continue;
            }

            List<Bid> bids = randomProduct.getBids() == null ? new ArrayList<Bid>() : randomProduct.getBids();
            bids.add(toAdd);
            em.persist(toAdd);
        }

    }


    private Bid generateDummyBids(Product product, List<User> users) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Bid ret = new Bid();
        ret.setProduct(product);
        ret.setTimestamp(Date.from(Instant.now()));

        if (r.nextInt() % 3 != 0) {
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

    private String createPasswordHash(String password) {
        return DigestUtils.sha256Hex(password);

    }

    private Date getRandomDateAfter(Instant instant, long maxSecondAfter) {
        return Date.from(instant.plusSeconds(r.nextLong() % maxSecondAfter));
    }

    private Date getRandomDateBefore(Instant instant, long maxSecondBefore) {
        return Date.from(instant.minusSeconds(r.nextLong() % maxSecondBefore));
    }
}
