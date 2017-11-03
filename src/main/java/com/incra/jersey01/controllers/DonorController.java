package com.incra.jersey01.controllers;

import com.incra.jersey01.models.Donor;
import com.incra.jersey01.services.DonorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("donors")
public class DonorController extends AbstractController {

    protected DonorService donorService;

    @Inject
    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetch(@PathParam("id") Integer id) {

        Donor data = donorService.getDonor(id);

        return createEntityResponse(data);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchList(
            @DefaultValue("") @QueryParam("sort") String sortStr,
            @DefaultValue("50") @QueryParam("limit") int limit,
            @DefaultValue("0") @QueryParam("offset") int offset) {

        List<Donor> data = donorService.getDonors(sortStr, limit, offset);

        return createEntityListResponse(data, limit, offset);
    }
}
