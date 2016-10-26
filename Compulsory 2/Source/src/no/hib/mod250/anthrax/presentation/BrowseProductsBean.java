package no.hib.mod250.anthrax.presentation;

import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.model.Product;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed bean for displaying the browse products page
 */
@ManagedBean(name = "browseProductsBean")
@SessionScoped
public class BrowseProductsBean {
    private final int pageSize = 10;
    @EJB
    private ProductFacade productFacade;

    /**
     * Get the number of products to show on each page
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Get a filtered selection of products given a page number and a pair of filters
     * @param pageNumber the page numnber
     * @param search the search keyword
     * @param category the category name
     * @return the filtered product list
     */
    public List<Product> filteredProducts(int pageNumber, String search, String category) {
        pageNumber = pageNumber > 0 ? pageNumber : 1;

        return productFacade.findAllActiveSortByClosingTime(search, category, pageNumber, pageSize);
    }

    /**
     * Returns a helper list to generate a page navigation buttons
     * @param search the search keyword
     * @param category the category name
     * @return list of page numbers
     */
    public List<String> getPagesHelperList(String search, String category) {
        List<String> helperList = new ArrayList<>();
        long prodCount = getProductCount(search, category);
        long amountOfPages =  prodCount % pageSize > 0 ? prodCount / pageSize + 1 : prodCount / pageSize;

        for (int i = 0; i < amountOfPages ; i++) {
            helperList.add(Integer.toString(i+1));
        }

        return helperList;
    }

    /**
     * Get the number of products matching the filters
     * @param search the search keyword
     * @param category the category name
     * @return the number of products
     */
    private long getProductCount(String search, String category) {
        return productFacade.activeCount(search, category);
    }
}