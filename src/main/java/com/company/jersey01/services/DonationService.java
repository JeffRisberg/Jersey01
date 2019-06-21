package com.company.jersey01.services;

import com.company.common.FilterDesc;
import com.company.jersey01.models.Donation;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
@Singleton
public class DonationService {

  protected List<Donation> donations = new ArrayList<Donation>();

  public DonationService() {
  }

  public Donation getDonation(long id) {
    for (Donation donation : donations) {
      if (donation.getId() == id)
        return donation;
    }
    return null;
  }

  public List<Donation> getDonations(int limit, int offset, List<FilterDesc> filterDescs) {
    List<Donation> result = applyFilter(filterDescs);

    if (offset > 0 && offset >= result.size())
      result = new ArrayList<Donation>();
    else if (offset > 0)
      result = result.subList(offset, result.size());

    return result;
  }

  public long getDonationsCount(List<FilterDesc> filterDescs) {
    List<Donation> result = applyFilter(filterDescs);

    return (long) result.size();
  }

  private List<Donation> applyFilter(List<FilterDesc> filterDescs) {
    List<Donation> result = new ArrayList<Donation>();

    for (Donation donation : donations) {
      boolean accepted = true;

      if (filterDescs != null) {
        for (FilterDesc filterDesc : filterDescs) {
          switch (filterDesc.getField().getName()) {
            /*
            case "amount":
              if (!donation.getAmount().equalsIgnoreCase((String) filterDesc.getValue()))
                accepted = false;
              break;
              */
          }
        }
      }

      if (accepted)
        result.add(donation);
    }

    return result;
  }
}
