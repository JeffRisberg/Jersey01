package com.incra.jersey01.services;

import com.google.inject.Singleton;
import com.incra.jersey01.models.Charity;
import com.incra.jersey01.models.Donor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
@Singleton
public class DonorService {

    protected static List<Donor> donors = new ArrayList<Donor>();

    public DonorService() {
        donors.add(new Donor("John", "Smith"));
        donors.add(new Donor("Bill", "Jones"));
    }

    public Donor getDonor() {
        return donors.get(0);
    }

    public List<Donor> getDonors() {
        return donors;
    }
}
