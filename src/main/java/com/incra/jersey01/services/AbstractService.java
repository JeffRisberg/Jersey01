package com.incra.jersey01.services;

import com.incra.jersey01.common.model.jooq.query.FieldDesc;
import com.incra.jersey01.common.model.jooq.query.SortDesc;
import com.incra.jersey01.common.model.jooq.query.SortDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeff Risberg
 * @since 10/30/17
 */
public class AbstractService {

    /**
     * Generate a sorting specification from the given sort string, such as "-field1,field2".
     *
     * @param sortStr
     * @return
     */
    protected List<SortDesc> parseSortStr(String sortStr) {
        List<SortDesc> sortDescs = new ArrayList<SortDesc>();

        String[] fragments = sortStr.split(",");

        for (int i = 0; i < fragments.length; i++) {
            String fieldName = fragments[i];
            SortDirection sortDir = SortDirection.Ascending;

            if (fieldName.startsWith("-")) {
                fieldName = fieldName.substring(1);
                sortDir = SortDirection.Descending;

                sortDescs.add(new SortDesc(new FieldDesc(fieldName), sortDir));
            }
        }
        return sortDescs;
    }
}
