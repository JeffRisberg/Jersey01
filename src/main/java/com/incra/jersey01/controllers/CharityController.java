package com.incra.jersey01.controllers;

import com.google.inject.Inject;
import com.incra.jersey01.models.Charity;
import com.incra.jersey01.services.CharityService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "charity" path)
 */
@Path("charity")
public class CharityController {

    protected CharityService charityService;

    @Inject
    public CharityController(CharityService charityService) {
        this.charityService = charityService;
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

        Charity charity = new Charity("Red Cross", "88-555555", "www.redcross.org");
        System.out.println(charity);

        return "Charity: Red Cross";
    }
}
