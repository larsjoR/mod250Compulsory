package no.hib.mod250.anthrax.presentation;

import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Bean for managing product page operations.s
 */
@ManagedBean
public class ProductPageBean {
    @EJB
    private ProductFacade productFacade;
    private Product product;

    public ProductPageBean() {

    }

    /**
     * Gets a product based on an id parameter in the request
     * @return the product
     * @throws IOException
     */
    public Product getProduct() throws IOException {
        if (product == null) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                    .getRequest();

            String idString = request.getParameter("id");


            if (productIdIsValid(idString)) {
                product = productFacade.find(Integer.valueOf(idString));
            }
        }


        return product;
    }

    /**
     * Sets the product
     * @param product the product to be set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Checks if the product id is a valid number
     * @param idString the string value of the id
     * @return true if it's a valid number, false otherwise
     */
    public boolean productIdIsValid(String idString) {
        int id = 0;

        try {
            id = Integer.valueOf(idString);
        } catch (NumberFormatException e) {
            return false;
        }

        return productFacade.find(id) != null;
    }


    /**
     * Converts a timestamp to a custom formatted string
     * @param timestamp The timestamp to be formatted
     * @return a formatted string
     */
    public String convertTimestamp(Date timestamp) {

        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(timestamp);
    }


}
