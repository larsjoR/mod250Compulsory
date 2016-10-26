package no.hib.mod250.anthrax.presentation;

import no.hib.mod250.anthrax.dummydata.DummyData;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * A simple bean to help create dummy data
 */
@ManagedBean
@ApplicationScoped
public class DummyDataBean {
    @EJB
    DummyData dd;

    private boolean seeded;

    /**
     * Calls the seed method on the database, to fill with dummy data.
     */
    public void seed() {
        dd.seedDatabase();
        seeded = true;
    }

    /**
     * Checks whether the database is seeded or not
     * @return true if seeded, false otherwise
     */
    public boolean isSeeded() {
        return seeded;
    }

    /**
     * Sets the seeded property
     * @param seeded the seed param to be set
     */
    public void setSeeded(boolean seeded) {
        this.seeded = seeded;
    }
}
