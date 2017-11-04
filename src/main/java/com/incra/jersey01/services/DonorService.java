package com.incra.jersey01.services;

import com.google.inject.Singleton;
import com.incra.jersey01.common.model.jooq.query.FilterDesc;
import com.incra.jersey01.common.model.jooq.query.SortDesc;
import com.incra.jersey01.common.model.jooq.query.SortDirection;
import com.incra.jersey01.models.Charity;
import com.incra.jersey01.models.Donor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeff Risberg
 * @since 10/26/17
 */
@Singleton
public class DonorService {

    protected static List<Donor> donors = new ArrayList<Donor>();

    public DonorService() {
        donors.add(new Donor(1, "John", "Smith"));
        donors.add(new Donor(2, "Bill", "Jones"));
        donors.add(new Donor(3, "Tom", "Kennedy"));
        donors.add(new Donor(4, "Jack", "Underhill"));
        donors.add(new Donor(5, "Ringo", "Starr"));
        donors.add(new Donor(6, "Paul", "Smith"));
    }

    public Donor getDonor(int id) {
        for (Donor donor : donors) {
            if (donor.getId() == id)
                return donor;
        }
        return null;
    }

    public List<Donor> getDonors(int limit, int offset, List<SortDesc> sortDescs, List<FilterDesc> filterDescs) {
        List<Donor> result = applyFilter(filterDescs);

        if (sortDescs.size() > 0) {
            SortDesc sortDesc = sortDescs.get(0);

            if (sortDesc.getDirection() == SortDirection.Ascending) {
                if (sortDesc.getField().getName().equals("firstName"))
                    result.sort((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()));
                if (sortDesc.getField().getName().equals("lastName"))
                    result.sort((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
            } else {
                if (sortDesc.getField().getName().equals("firstName"))
                    result.sort((p1, p2) -> p2.getFirstName().compareTo(p1.getFirstName()));
                if (sortDesc.getField().getName().equals("lastName"))
                    result.sort((p1, p2) -> p2.getLastName().compareTo(p1.getLastName()));
            }
        }

        if (offset > 0 && offset >= result.size())
            result = new ArrayList<Donor>();
        else if (offset > 0)
            result = result.subList(offset, result.size());

        return result;
    }

    public long getDonorsCount(List<FilterDesc> filterDescs) {
        List<Donor> result = applyFilter(filterDescs);

        return (long) result.size();
    }

    private List<Donor> applyFilter(List<FilterDesc> filterDescs) {
        List<Donor> result = new ArrayList<Donor>();

        for (Donor donor : donors) {
            boolean accepted = true;

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

            if (accepted)
                result.add(donor);
        }

        return result;
    }
}
