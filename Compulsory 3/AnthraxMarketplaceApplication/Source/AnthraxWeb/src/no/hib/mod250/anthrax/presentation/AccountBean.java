package no.hib.mod250.anthrax.presentation;

import no.hib.mod250.anthrax.boundary.ProductFacade;
import no.hib.mod250.anthrax.boundary.UserFacade;
import no.hib.mod250.anthrax.exception.UserAlreadyExistsException;
import no.hib.mod250.anthrax.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Bean to handle authentication and user management
 */
@ManagedBean(name = "accountBean")
@SessionScoped
public class AccountBean {
    @EJB
    private UserFacade userFacade;
    private String password;
    private String repeatPassword;
    User user;

    //TEMP
    @EJB
    private ProductFacade productFacade;


    public AccountBean() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return "";
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * Creates a new user account in the system.
     * Error handling applied to check if username aleardy exists in db.
     */
    public void signUp() {
        //TEMP
        productFacade.findAllEnded();


        if (password.equals(repeatPassword)) {
            user.setPassword(DigestUtils.sha256Hex(password));
        } else {
            FacesContext.getCurrentInstance().addMessage("signupform:password", new FacesMessage("The passwords don't match"));
            return;
        }

        try {
            user = userFacade.createNewUser(user);
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.login(user.getUserName(), password);
        } catch (UserAlreadyExistsException e) {
            FacesContext.getCurrentInstance().addMessage("signupform:username", new FacesMessage("Username already exists"));
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Redirect to index
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("../index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs the current user out of the application by invalidating the session.
     * @throws IOException
     */
    public void logout() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        ec.redirect(ec.getApplicationContextPath() + "/index.xhtml");
    }

    public String getLoggedInUsername(){
        User loggedInUser = userFacade.find(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        return loggedInUser.getUserName();
    }

    public String getLoggedInName() {
        User loggedInUser = userFacade.find(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        return loggedInUser.getName();
    }
}
