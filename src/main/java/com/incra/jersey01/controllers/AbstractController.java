package com.incra.jersey01.controllers;

import com.incra.jersey01.common.model.jooq.query.*;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jeff Risberg
 * @since 11/02/17
 */
public class AbstractController {

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
            }

                sortDescs.add(new SortDesc(new FieldDesc(fieldName), sortDir));
        }
        return sortDescs;
    }

    protected List<FilterDesc> parseFiltering(MultivaluedMap<String, String> queryParams) {
        List<FilterDesc> filterDescs = new ArrayList<FilterDesc>();

        for (Map.Entry<String, List<String>> entrySet : queryParams.entrySet()) {
            String fieldName = entrySet.getKey();
            List<String> fieldValues = entrySet.getValue();

            if (fieldValues.size() > 0) {
                filterDescs.add(new FilterDesc(new FieldDesc(fieldName), FilterOperator.Equal, fieldValues.get(0)));
            }
        }

        return filterDescs;
    }

    protected Response createEntityResponse(Object data) {
        List<String> errors = new ArrayList();
        Map result = new HashMap();

        result.put("data", data);
        result.put("errors", errors);

        if (data == null) {
            errors.add("Not found");

            return Response.status(Response.Status.NOT_FOUND).entity(result).build();
        } else {
            return Response.status(Response.Status.OK).entity(result).build();
        }
    }

    protected Response createEntityListResponse(List data, long totalCount, int limit, int offset) {
        List<String> errors = new ArrayList();
        Map result = new HashMap();

        result.put("data", data);
        result.put("totalCount", totalCount);
        result.put("limit", limit);
        result.put("offset", offset);
        result.put("errors", errors);

        return Response.status(Response.Status.OK).entity(result).build();
    }
}
