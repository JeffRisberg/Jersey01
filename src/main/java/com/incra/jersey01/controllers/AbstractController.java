package com.incra.jersey01.controllers;

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

    protected Response createEntityListResponse(List data, int limit, int offset) {
        List<String> errors = new ArrayList();
        Map result = new HashMap();

        result.put("data", data);
        result.put("totalCount", data.size());
        result.put("limit", limit);
        result.put("offset", offset);
        result.put("errors", errors);

        return Response.status(Response.Status.OK).entity(result).build();
    }
}
