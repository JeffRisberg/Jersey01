package com.incra.jersey01.controllers;

import com.google.inject.Inject;
import com.incra.jersey01.models.Charity;
import com.incra.jersey01.models.Donor;
import com.incra.jersey01.services.CharityService;
import com.incra.jersey01.services.DonorService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "donor" path)
 */
@Path("donor")
public class DonorController {

    protected DonorService donorService;

    @Inject
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {

        Donor donor = donorService.getDonor();
        System.out.println(donor);

        return "Donor: John Smith";
    }
}
