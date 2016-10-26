package no.hib.mod250.anthrax.presentation;

import no.hib.mod250.anthrax.boundary.CategoryFacade;
import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.model.Category;
import no.hib.mod250.anthrax.model.Product;
import no.hib.mod250.anthrax.model.User;
import no.hib.mod250.anthrax.util.ImageHelper;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;


/**
 * Bean to handle operations on the product.
 */
@ManagedBean(name = "productBean")
public class ProductBean {

//    @ManagedProperty("#{accountBean}")
//    private AccountBean currentUser;
    @ManagedProperty("#{categoryBean}")
    private CategoryBean categoryBean;

    private Product product;
    private String title;
    private Date closingDate;
    private Date closingTime;
    private double startBid;
    private UploadedFile file;
    private String imgpath;
    private String selectedCategory;
    private boolean newCategory;
    private String newCategoryString;

    @EJB
    private ProductFacade productFacade;
    @EJB
    private CategoryFacade categoryFacade;

    public ProductBean() {
        product = new Product();
        newCategory = false;
    }

    public boolean isNewCategory() {
        return newCategory;
    }

    public void setNewCategory(boolean newCategory) {
        this.newCategory = newCategory;
    }

    public String getNewCategoryString() {
        return newCategoryString;
    }

    public void setNewCategoryString(String newCategoryString) {
        this.newCategoryString = newCategoryString;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public CategoryBean getCategoryBean() {
        return categoryBean;
    }

    public void setCategoryBean(CategoryBean categoryBean) {
        this.categoryBean = categoryBean;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public double getStartBid() {
        return startBid;
    }

    public void setStartBid(double startBid) {
        this.startBid = startBid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Date currentDate() {
        return Date.from(Instant.now());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    /**
     * Adds a product based on form input
     * @throws IOException
     */
    public void addProduct() throws IOException {
        setEndTime();
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        if (product.getPublished()) {
            product.setPublishedTime(new Timestamp(currentDate().getTime()));
        }

        product.setSeller(new User(username, null, null, null));
        product.setRating(null);
        product.setImgPath(file.getFileName());
        product.setBids(null);

        // Check if user has selected a custom category. If not, persist the category
        // selected in the dropdown menu.
        if (selectedCategory != null) {
            Category cat = categoryFacade.find(selectedCategory);
            product.setCategory(cat);
        }

        // Check if the user wants to create a new custom category, and add this.
        if (newCategory && !newCategoryString.equals("")) {
            Category c = new Category(newCategoryString);
            categoryFacade.create(c);
            product.setCategory(c);
        }

        product = productFacade.addProduct(product);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        ec.redirect(ec.getApplicationContextPath() + "/user/portfolio.xhtml");
    }

    /**
     * Sets the end time
     */
    @SuppressWarnings("deprecation")
    public void setEndTime() {
        product.setEndTime(Timestamp.from(closingDate.toInstant()));
    }

    /**
     * Uploads a new picture to server
     */
    public void upload() {
        ImageHelper ih = new ImageHelper();

        String filename = ih.getFileNameFromFullPath(file.getFileName());
        String path = "C:\\Temp\\AnthraxMedia\\images\\";
        try {
            ih.copyFile(path, filename, file.getInputstream());
            product.setImgPath(path + filename);
        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Could not upload file");
            FacesContext.getCurrentInstance().addMessage("newProductForm:uploadFile", message);
        }
    }

    /**
     * Method to combine upload of picture and adding a product on submit form.
     */
    public void uploadAndAdd() {
        upload();
        try {
            addProduct();
        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Error during add new product");
            FacesContext.getCurrentInstance().addMessage("newProductForm:addProduct", message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        upload();
    }

    /**
     * Method to clear bean / form info
     */
    public void cancel() {
        selectedCategory = null;
        closingDate = null;
        closingTime = null;
        this.product = new Product();
    }


}
