package com.company.jersey01.services;

import com.company.common.FilterDescription;
import com.company.jersey01.models.CharityEntity;
import com.company.jersey01.models.DonationEntity;
import com.company.jersey01.models.DonorEntity;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
@Singleton
public class DonationService {

    CharityService charityService;
    DonorService donorService;

    protected List<DonationEntity> donations = new ArrayList<DonationEntity>();

    @Inject
    public DonationService(CharityService charityService, DonorService donorService) {
        this.charityService = charityService;
        this.donorService = donorService;

        CharityEntity charity = charityService.getCharity(1L);
        DonorEntity donor = donorService.getDonor(1L);

        donations.add(new DonationEntity(1L, donor, charity, 1000.0f));
    }

    public DonationEntity getDonation(long id) {
        for (DonationEntity donationEntity : donations) {
            if (donationEntity.getId() == id)
                return donationEntity;
        }
        return null;
    }

    public List<DonationEntity> getDonations(int limit, int offset, List<FilterDescription> filterDescs) {
        List<DonationEntity> result = applyFilter(filterDescs);

        if (offset > 0 && offset >= result.size())
            result = new ArrayList<DonationEntity>();
        else if (offset > 0)
            result = result.subList(offset, result.size());

        return result;
    }

    public long getDonationsCount(List<FilterDescription> filterDescs) {
        List<DonationEntity> result = applyFilter(filterDescs);

        return (long) result.size();
    }

    private List<DonationEntity> applyFilter(List<FilterDescription> filterDescs) {
        List<DonationEntity> result = new ArrayList<DonationEntity>();

        for (DonationEntity donation : donations) {
            boolean accepted = true;

            if (filterDescs != null) {
                for (FilterDescription filterDesc : filterDescs) {
                    switch (filterDesc.getField()) {
            /*
            case "amount":
              if (!donationEntity.getAmount().equalsIgnoreCase((String) filterDesc.getValue()))
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
