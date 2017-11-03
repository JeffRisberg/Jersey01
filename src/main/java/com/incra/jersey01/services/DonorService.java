package com.incra.jersey01.services;

import com.google.inject.Singleton;
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
        donors.add(new Donor(1, "John", "Smith"));
        donors.add(new Donor(2, "Bill", "Jones"));
    }

    public Donor getDonor(int id) {
        for (Donor donor : donors) {
            if (donor.getId() == id)
                return donor;
        }
        return null;
    }

    public List<Donor> getDonors(int limit, int offset) {
        return donors;
    }
}
