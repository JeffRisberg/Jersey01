package com.incra.jersey01.controllers;

import com.incra.jersey01.models.Charity;
import com.incra.jersey01.services.CharityService;

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

@Path("charities")
public class CharityController {

    protected CharityService charityService;

    @Inject
    public CharityController(CharityService charityService) {
        this.charityService = charityService;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchOne(@PathParam("id") Integer id) {

        Charity data = charityService.getCharity(id);

        Map result = new HashMap();

        result.put("data", data);
        result.put("errors", new ArrayList());

        return Response.ok(data, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map fetchAll() {

        List<Charity> data = charityService.getCharities();

        Map result = new HashMap();

        result.put("data", data);
        result.put("totalCount", data.size());
        result.put("errors", new ArrayList());

        return result;
    }
}
