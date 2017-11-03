package com.incra.jersey01.controllers;

import com.incra.jersey01.models.Charity;
import com.incra.jersey01.models.Donor;
import com.incra.jersey01.services.CharityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("charities")
public class CharityController extends AbstractController {

    protected CharityService charityService;

    @Inject
    public CharityController(CharityService charityService) {
        this.charityService = charityService;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetch(@PathParam("id") Integer id) {

        Charity data = charityService.getCharity(id);

        return createEntityResponse(data);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchList(
            @DefaultValue("") @QueryParam("sort") String sortStr,
            @DefaultValue("50") @QueryParam("limit") int limit,
            @DefaultValue("0") @QueryParam("offset") int offset) {

        List<Charity> data = charityService.getCharities(sortStr, limit, offset);

        return createEntityListResponse(data, limit, offset);
    }
}
