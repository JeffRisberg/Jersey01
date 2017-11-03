package com.incra.jersey01.controllers;

import com.incra.jersey01.models.Charity;
import com.incra.jersey01.models.Donor;
import com.incra.jersey01.services.DonorService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchOne(@PathParam("id") Integer id) {

        Donor data = donorService.getDonor(id);

        Map result = new HashMap();

        result.put("data", data);
        result.put("errors", new ArrayList());

        return Response.ok(data, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map fetchAll() {

        List<Donor> data = donorService.getDonors();

        Map result = new HashMap();

        result.put("data", data);
        result.put("totalCount", data.size());
        result.put("errors", new ArrayList());

        return result;
    }
}
