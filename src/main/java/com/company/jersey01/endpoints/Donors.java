package com.company.jersey01.endpoints;

import com.company.common.FilterDescription;
import com.company.jersey01.models.DonorEntity;
import com.company.jersey01.services.DonorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("donors")
public class Donors extends AbstractEndpoint {

  protected DonorService donorService;

  @Inject
  public Donors(DonorService donorService) {
    this.donorService = donorService;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response fetch(@PathParam("id") Integer id) {

    DonorEntity data = donorService.getDonor(id);

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
    List<FilterDescription> filterDescs = this.parseFiltering(queryParams);

    List<DonorEntity> data = donorService.getDonors(limit, offset, filterDescs);
    long totalCount = donorService.getDonorsCount(filterDescs);

    return createEntityListResponse(data, totalCount, limit, offset, null);
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response delete(@PathParam("id") Integer id) {

    DonorEntity data = donorService.getDonor(id);

    return createDeleteResponse(data, null);
  }
}
