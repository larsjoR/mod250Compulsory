package no.hib.mod250.anthrax.boundary;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import no.hib.mod250.anthrax.model.Rating;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * The user Rating facade with the injected persitence unit.
 */
@Stateless
public class RatingFacade extends AbstractFacade<Rating> {

    @PersistenceContext(unitName = "AnthraxPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RatingFacade() {
        super(Rating.class);
    }

}
