package com.incra.jersey01.models;

/**
 * @author jeff
 * @since 10/26/17
 */
public class Charity {
    protected String name;
    protected String ein;
    protected String website;

    public Charity(String name, String ein, String website) {
        this.name = name;
        this.ein = ein;
        this.website = website;
    }
}
