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

/**
 * Class to seed the database with dummy data.
 */
@Stateless
public class DummyData {
    @PersistenceContext(unitName = "AnthraxPU")
    private EntityManager em;

    public void seedDatabase() {
        if (em.find(User.class, "Finn") != null) {
            return;
        }

        User finn = new User("finn", "Finn Hyllesleik", "finn");
        User tore = new User("tore", "Tore Hyllesleik", "tore");
        User timnina = new User("timnina", "Tim Nina Hyllesleik", "timnina");
        User malin = new User("malin", "Malin Hyllesleik", "malin");

        Category animals = new Category("Animals");
        Category furniture = new Category("Furniture");
        Category tools = new Category("Tools");
        Category movies = new Category("Movies");

        Product aa = new Product("Cat", "katt.jpg", "Nice cat", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 2, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 3, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product ab = new Product("Dog", "hund.jpg", "Nice dog", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 2, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 3, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product ac = new Product("Rabbit", "kanin.jpg", "Nice rabbit", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 2, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 3, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        Product ad = new Product("Snake", "snek.jpg", "Nice snake", true, 10.0, Timestamp.from(LocalDateTime.of(2016, 10, 2, 12, 0).toInstant(ZoneOffset.UTC)), Timestamp.from(LocalDateTime.of(2016, 10, 3, 12, 0).toInstant(ZoneOffset.UTC)), animals, null, timnina, null);
        timnina.setProducts(new ArrayList<Product>(){{add(aa);add(ab);add(ac);add(ad);}});

        Bid ba = new Bid(aa.getStartingPrice(), Date.from(Instant.EPOCH), timnina, aa);
        Bid ba1 = new Bid(aa.getStartingPrice()+5, Date.from(Instant.EPOCH), malin, aa);
        aa.setBids(new ArrayList<Bid>() {{add(ba); add(ba1);}});

        Bid bb = new Bid(ab.getStartingPrice(), Date.from(Instant.EPOCH), malin, ab);
//        Bid bb1 = new Bid(ab.getStartingPrice()+10, Instant.EPOCH, timnina, ab);
        ab.setBids(new ArrayList<Bid>() {{add(bb); /*add(bb1);*/}});


        Bid bc = new Bid(ac.getStartingPrice(), Date.from(Instant.EPOCH), finn, ac);
        Bid bc1 = new Bid(ac.getStartingPrice()+50, Date.from(Instant.EPOCH), timnina, ac);
        ac.setBids(new ArrayList<Bid>() {{add(bc); add(bc1);}});

        em.persist(finn);
        em.persist(tore);
        em.persist(timnina);
        em.persist(malin);

        em.persist(animals);
        em.persist(furniture);
        em.persist(tools);
        em.persist(movies);

        em.persist(aa);
        em.persist(ab);
        em.persist(ac);
        em.persist(ad);

        em.persist(ba);
        em.persist(ba1);
        em.persist(bb);
//        em.persist(bb1);
        em.persist(bc);
        em.persist(bc1);



    }
}
