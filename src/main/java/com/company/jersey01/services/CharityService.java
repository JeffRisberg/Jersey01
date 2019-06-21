package com.company.jersey01.services;

import com.company.common.FilterDesc;
import com.company.jersey01.models.CharityEntity;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
@Singleton
public class CharityService {

  protected List<CharityEntity> charities = new ArrayList<CharityEntity>();

  public CharityService() {
    charities.add(new CharityEntity(1L, "Red Cross", "66-555555", "www.redcross.org"));
    charities.add(new CharityEntity(2L, "ASPCA", "99-555555", "www.aspca.org"));
    charities.add(new CharityEntity(3L, "United Way", "33-555555", "www.unitedway.org"));
    charities.add(new CharityEntity(4L, "American Heart Assoc", "55-555555", "www.aha.org"));
    charities.add(new CharityEntity(5L, "Polar Bear Assoc", "45-555555", "www.polarbears.org"));
    charities.add(new CharityEntity(6L, "Stanford University", "37-555555", "www.stanford.edu"));
    charities.add(new CharityEntity(7L, "University of Southern California", "88-555555", "www.usc.edu"));
    charities.add(new CharityEntity(8L, "Scripps College", "88-321458", "www.scrippscollege.edu"));
  }

  public CharityEntity getCharity(long id) {
    for (CharityEntity charityEntity : charities) {
      if (charityEntity.getId() == id)
        return charityEntity;
    }
    return null;
  }

  public List<CharityEntity> getCharities(int limit, int offset, List<FilterDesc> filterDescs) {
    List<CharityEntity> result = applyFilter(filterDescs);

    if (offset > 0 && offset >= result.size())
      result = new ArrayList<CharityEntity>();
    else if (offset > 0)
      result = result.subList(offset, result.size());

    return result;
  }

  public long getCharitiesCount(List<FilterDesc> filterDescs) {
    List<CharityEntity> result = applyFilter(filterDescs);

    return (long) result.size();
  }

  private List<CharityEntity> applyFilter(List<FilterDesc> filterDescs) {
    List<CharityEntity> result = new ArrayList<CharityEntity>();

    for (CharityEntity charityEntity : charities) {
      boolean accepted = true;

      if (filterDescs != null) {
        for (FilterDesc filterDesc : filterDescs) {
          switch (filterDesc.getField().getName()) {
            case "name":
              if (!charityEntity.getName().equalsIgnoreCase((String) filterDesc.getValue()))
                accepted = false;
              break;
            case "ein":
              if (!charityEntity.getEin().equals(filterDesc.getValue()))
                accepted = false;
              break;
            case "website":
              if (!charityEntity.getWebsite().equalsIgnoreCase((String) filterDesc.getValue()))
                accepted = false;
              break;
          }
        }
      }

      if (accepted)
        result.add(charityEntity);
    }
    return result;
  }
}
