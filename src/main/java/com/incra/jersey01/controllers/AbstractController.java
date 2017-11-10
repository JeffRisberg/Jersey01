package com.incra.jersey01.controllers;

import com.incra.jersey01.common.model.jooq.query.*;

import javax.ws.rs.WebApplicationException;
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
     * @return list of Sort Descriptors
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

    /**
     * Generate a filtering specification from the query params of the request, of the
     * form "fieldname=fieldValue".
     *
     * @param queryParams
     * @return list of Filter Descriptors
     */
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

    /**
     * Generate the response for a fetch of a single entity.
     *
     * @param data if null, triggers 404 status code.
     * @return Response
     */
    protected Response createEntityResponse(Object data, List<Error> errors) {
        List<Error> resultErrors = new ArrayList<Error>();

        Envelope envelope = new Envelope(data, resultErrors);

        if (errors != null) {
            for (Error error : errors) {
                resultErrors.add(error);
            }
        }

        if (data == null) {
            resultErrors.add(new Error("Not found"));

            return Response.status(Response.Status.NOT_FOUND).entity(envelope).build();
        } else {
            return Response.status(Response.Status.OK).entity(envelope).build();
        }
    }

    /**
     * Generate the response for a fetch of a collection of entities.
     *
     * @param data
     * @param totalCount
     * @param limit
     * @param offset
     * @return Response
     */
    protected Response createEntityListResponse(
            List data,
            long totalCount,
            int limit,
            int offset,
            List<Error> errors) {
        if (data == null) {
            throw new IllegalArgumentException("missing data");
        }

        List<Error> resultErrors = new ArrayList<Error>();
        Envelope envelope = new Envelope(data, totalCount, limit, offset, errors);

        if (errors != null) {
            for (Error error : errors) {
                resultErrors.add(error);
            }
        }

        return Response.status(Response.Status.OK).entity(envelope).build();
    }

    /**
     * Generate the response for a delete.
     *
     * @param errors
     * @return
     */
    protected Response createDeleteResponse(Object data, List<Error> errors)
            throws WebApplicationException {
        List<Error> resultErrors = new ArrayList<Error>();
        Map result = new HashMap();

        result.put("errors", resultErrors);

        if (errors != null) {
            for (Error error : errors) {
                resultErrors.add(error);
            }
        }

        if (data == null) {
            resultErrors.add(new Error("Not found"));

            throw new WebApplicationException(
                    Response.status(Response.Status.NOT_FOUND).entity(result).build());
        } else {
            return Response.status(Response.Status.OK).entity(result).build();
        }
    }
}
