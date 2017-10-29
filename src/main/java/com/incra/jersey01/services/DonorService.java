package com.incra.jersey01.services;

import com.incra.jersey01.models.Donor;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
public class DonorService {

    public Donor getDonor() {
        Donor donor = new Donor("John", "Smith");

        return donor;
    }
}
