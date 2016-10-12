package no.hib.mod250.anthrax.presentation;

import no.hib.mod250.anthrax.exception.IncorrectUsernameOrPasswordException;
import no.hib.mod250.anthrax.exception.UserAlreadyExistsException;
import no.hib.mod250.anthrax.boundary.UserFacade;
import no.hib.mod250.anthrax.model.User;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Bean to handle authentication and user management
 */
@ManagedBean(name = "accountBean")
@SessionScoped
public class AccountBean {
    User user;
    @EJB
    private UserFacade userFacade;
    private boolean loggedIn;
    private String repeatPassword;

    public AccountBean() {
        user = new User();
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * Creates a new user account in the system.
     * Error handling applied to check if username aleardy exists in db.
     */
    public void signUp() {
        //TODO repeatpassword check
        try {
            user = userFacade.createNewUser(user);
            loggedIn = true;
        } catch (UserAlreadyExistsException e) {
            FacesContext.getCurrentInstance().addMessage("signupform:username", new FacesMessage("Username already exists"));
            return;
        }

        //Redirect to index
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("../index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }    }

    /**
     * Logs in the current user
     */
    public void login() {
        try {
            user = userFacade.login(user.getUserName(), user.getPassword());
            loggedIn = true;

        } catch (IncorrectUsernameOrPasswordException e) {
            FacesContext.getCurrentInstance().addMessage("loginform", new FacesMessage("Bad username or password"));
            return;
        }

        //Redirect to index
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("requestedURL"))
            {
                context.getExternalContext().redirect((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("requestedURL"));
            } else {
                context.getExternalContext().redirect("../index.xhtml");
            }

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

}
