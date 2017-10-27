package com.incra.jersey01.models;

/**
 * @author jeff
 * @since 10/26/17
 */
public class Donor {
    protected String firstName;
    protected String lastName;

    public Donor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
