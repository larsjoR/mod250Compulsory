package no.hib.mod250.anthrax.presentation;

import javax.faces.bean.ManagedBean;

/**
 * The bean to facilitate implementation of the search feature.
 */
@ManagedBean
public class SearchInputBean {
    private String search;
    private String category;


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
