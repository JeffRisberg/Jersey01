package com.incra.jersey01.controllers;

import com.incra.jersey01.common.model.jooq.query.FilterDesc;
import com.incra.jersey01.common.model.jooq.query.SortDesc;
import com.incra.jersey01.models.Donor;
import com.incra.jersey01.services.DonorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

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
            @DefaultValue("50") @QueryParam("limit") int limit,
            @DefaultValue("0") @QueryParam("offset") int offset,
            @DefaultValue("") @QueryParam("sort") String sortStr,
            @Context UriInfo uriInfo) {

        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        List<FilterDesc> filterDescs = this.parseFiltering(queryParams);
        List<SortDesc> sortDescs = this.parseSortStr(sortStr);

        List<Donor> data = donorService.getDonors(limit, offset, sortDescs, filterDescs);
        long totalCount = donorService.getDonorsCount(filterDescs);

        return createEntityListResponse(data, totalCount, limit, offset);
    }
}
