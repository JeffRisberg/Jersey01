package com.company.jersey01.endpoints;

import com.company.common.FilterDesc;
import com.company.jersey01.models.DonationEntity;
import com.company.jersey01.services.DonationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("donationEntities")
public class Donations extends AbstractEndpoint {

  protected DonationService donationService;

  @Inject
  public Donations(DonationService donationService) {
    this.donationService = donationService;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response fetch(@PathParam("id") Integer id) {

    DonationEntity data = donationService.getDonation(id);

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

    List<DonationEntity> data = donationService.getDonations(limit, offset, filterDescs);
    long totalCount = donationService.getDonationsCount(filterDescs);

    return createEntityListResponse(data, totalCount, limit, offset, null);
  }

  @DELETE
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response delete(@PathParam("id") Integer id) {

    DonationEntity data = donationService.getDonation(id);

    return createDeleteResponse(data, null);
  }
}
