package no.hib.mod250.anthrax.presentation;

import no.hib.mod250.anthrax.boundary.BidFacade;
import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.model.Bid;
import no.hib.mod250.anthrax.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.IntegerConverter;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Managed bean for displaying the "product portfolio"
 */
@ManagedBean
public class ProductPortfolioBean {
    private final int pageSize = 10;
    @ManagedProperty("#{accountBean}")
    private AccountBean accountBean;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private BidFacade bidFacade;


    public void setAccountBean(AccountBean accountBean) {
        this.accountBean = accountBean;
    }

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
        return productFacade.countByUser(accountBean.getUser());
    }

    /**
     * Get the set of products for a page
     * @param i the page number
     * @return the products to present
     */
    public List<Product> getPage(int i) {
        int page = i > 0 ? i : 1;

        return productFacade.findAllByUser(accountBean.getUser(), page, pageSize);
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
