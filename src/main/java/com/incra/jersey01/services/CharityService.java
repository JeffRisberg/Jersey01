package com.incra.jersey01.services;

import com.incra.jersey01.models.Charity;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
public class CharityService {

    public Charity getCharity() {
        Charity charity = new Charity("Red Cross", "88-555555", "www.redcross.org");

        return charity;
    }
}
