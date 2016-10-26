package no.hib.mod250.anthrax.boundary;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import no.hib.mod250.anthrax.exception.IncorrectUsernameOrPasswordException;
import no.hib.mod250.anthrax.exception.UserAlreadyExistsException;
import no.hib.mod250.anthrax.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * The user User facade with the injected persitence unit.
 *
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "AnthraxPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    /**
     * Creates a new user if it does not exist.
     * @param user the user
     * @return the created user
     * @throws UserAlreadyExistsException
     */
    public User createNewUser(User user) throws UserAlreadyExistsException {
        if (this.find(user.getUserName()) != null) {
            throw new UserAlreadyExistsException();
        }

        this.create(user);
        return user;
    }

    /**
     * Checks if the input credentials matches the db values
     * @param username the username
     * @param password the password
     * @return The user if credentials are correct
     * @throws IncorrectUsernameOrPasswordException
     */
    public User login(String username, String password) throws IncorrectUsernameOrPasswordException {
        User user = this.find(username);

        if (user == null || !user.getPassword().equals(password)) {
            throw new IncorrectUsernameOrPasswordException();
        }
        return user;
    }


}
