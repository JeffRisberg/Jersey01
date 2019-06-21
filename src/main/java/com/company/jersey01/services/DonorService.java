package com.company.jersey01.services;

import com.company.common.FilterDesc;
import com.company.jersey01.models.DonorEntity;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
@Singleton
public class DonorService {

  protected List<DonorEntity> donors = new ArrayList<DonorEntity>();

  public DonorService() {
    donors.add(new DonorEntity(1L, "John", "Smith"));
    donors.add(new DonorEntity(2L, "Bill", "Jones"));
    donors.add(new DonorEntity(3L, "Tom", "Kennedy"));
    donors.add(new DonorEntity(4L, "Jack", "Underhill"));
    donors.add(new DonorEntity(5L, "Sally", "Starr"));
    donors.add(new DonorEntity(6L, "Henry", "Adams"));
    donors.add(new DonorEntity(7L, "Paul", "Jones"));
    donors.add(new DonorEntity(8L, "Steven", "Hawking"));
  }

  public DonorEntity getDonor(long id) {
    for (DonorEntity donor : donors) {
      if (donor.getId() == id)
        return donor;
    }
    return null;
  }

  public List<DonorEntity> getDonors(int limit, int offset, List<FilterDesc> filterDescs) {
    List<DonorEntity> result = applyFilter(filterDescs);

    if (offset > 0 && offset >= result.size())
      result = new ArrayList<DonorEntity>();
    else if (offset > 0)
      result = result.subList(offset, result.size());

    return result;
  }

  public long getDonorsCount(List<FilterDesc> filterDescs) {
    List<DonorEntity> result = applyFilter(filterDescs);

    return (long) result.size();
  }

  private List<DonorEntity> applyFilter(List<FilterDesc> filterDescs) {
    List<DonorEntity> result = new ArrayList<DonorEntity>();

    for (DonorEntity donor : donors) {
      boolean accepted = true;

      if (filterDescs != null) {
        for (FilterDesc filterDesc : filterDescs) {
          switch (filterDesc.getField().getName()) {
            case "firstName":
              if (!donor.getFirstName().equalsIgnoreCase((String) filterDesc.getValue()))
                accepted = false;
              break;
            case "lastName":
              if (!donor.getLastName().equalsIgnoreCase((String) filterDesc.getValue()))
                accepted = false;
              break;
          }
        }
      }

      if (accepted)
        result.add(donor);
    }

    return result;
  }
}
