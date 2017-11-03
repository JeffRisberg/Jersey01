package com.incra.jersey01.services;

import com.google.inject.Singleton;
import com.incra.jersey01.models.Charity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
@Singleton
public class CharityService {

    protected static List<Charity> charities = new ArrayList<Charity>();

    public CharityService() {
        charities.add(new Charity(1, "Red Cross", "88-555555", "www.redcross.org"));
        charities.add(new Charity(2, "ASPCA", "88-555555", "www.aspca.org"));
        charities.add(new Charity(3, "United Way", "88-555555", "www.unitedway.org"));
        charities.add(new Charity(4, "American Heart Assoc", "88-555555", "www.aha.org"));
    }

    public Charity getCharity(int id) {
        for (Charity charity : charities) {
            if (charity.getId() == id)
                return charity;
        }
        return null;
    }

    public List<Charity> getCharities() {
        return charities;
    }
}
