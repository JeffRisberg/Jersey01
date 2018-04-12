package com.company.jersey01.endpoints;

import com.company.common.FilterDesc;
import com.company.jersey01.models.Charity;
import com.company.jersey01.services.CharityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("charities")
public class CharityEndpoint extends AbstractEndpoint {

    protected CharityService charityService;

    @Inject
    public CharityEndpoint(CharityService charityService) {
        this.charityService = charityService;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetch(@PathParam("id") Integer id) {

        Charity data = charityService.getCharity(id);

        return createEntityResponse(data, null);
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

        List<Charity> data = charityService.getCharities(limit, offset, filterDescs);
        long totalCount = charityService.getCharitiesCount(filterDescs);

        return createEntityListResponse(data, totalCount, limit, offset, null);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {

        Charity data = charityService.getCharity(id);

        return createDeleteResponse(data, null);
    }
}