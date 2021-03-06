package no.hib.mod250.anthrax.presentation;

import no.hib.mod250.anthrax.boundary.BidFacade;
import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Managed bean for displaying the "product portfolio"
 */
@ManagedBean
public class ProductPortfolioBean {
    private final int pageSize = 10;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private BidFacade bidFacade;


    /**
     * Get the number of products to display on each page
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Get product list for the current user
     * @return list containing products
     */
    public long getProductCount() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        return productFacade.countByUser(username);
    }

    /**
     * Get the set of products for a page
     * @param i the page number
     * @return the products to present
     */
    public List<Product> getPage(int i) {
        int page = i > 0 ? i : 1;
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        return productFacade.findAllByUser(username, page, pageSize);
    }

    /**
     * Returns a helper list to generate a page navigation buttons
     * @return list of page numbers
     */
    public List<String> getPagesHelperList() {
        List<String> helperList = new ArrayList<>();
        long prodCount = getProductCount();
        long amountOfPages =  prodCount % pageSize > 0 ? prodCount / pageSize + 1 : prodCount / pageSize;

        for (int i = 0; i < amountOfPages ; i++) {
            helperList.add(Integer.toString(i+1));
        }

        return helperList;
    }

    /**
     * Sets a product's status to "published"
     * @param id the product's id
     */
    public void setPublished(String id) {
        Integer pid = Integer.parseInt(id);
        Product product = productFacade.find(pid);
        productFacade.setPublished(product);
    }

    /**
     * Get a formatted date string, given a product id
     * @param id the product id
     * @return the formatted string
     * @throws Exception throws if invalid id
     */
    public String getFormattedDateString(String id) throws Exception {
        Integer pid = Integer.parseInt(id);
        Product product = productFacade.find(pid);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(product.getEndTime());
    }
    

}
