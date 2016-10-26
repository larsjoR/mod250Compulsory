package no.hib.mod250.anthrax.presentation;

import no.hib.mod250.anthrax.boundary.CategoryFacade;
import no.hib.mod250.anthrax.model.Category;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Simple category bean to handle the categories.
 */
@ManagedBean
@ApplicationScoped
public class CategoryBean {
    @EJB
    private CategoryFacade categoryFacade;

    public List<Category> getCategories() {
        return categoryFacade.findAll();
    }
}
