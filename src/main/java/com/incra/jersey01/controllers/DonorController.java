package com.incra.jersey01.controllers;

import com.incra.jersey01.models.Donor;
import com.incra.jersey01.services.DonorService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("donors")
public class DonorController {

    protected DonorService donorService;

    @Inject
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @GET
    @Path("/one")
    @Produces(MediaType.APPLICATION_JSON)
    public Donor fetchOne() {

        return donorService.getDonor();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Map fetchAll() {

        List<Donor> data = donorService.getDonors();

        Map result = new HashMap();

        result.put("data", data);
        result.put("totalCount", data.size());

        return result;
    }
}
