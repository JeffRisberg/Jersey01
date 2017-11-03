package com.incra.jersey01.common.model.jooq.query;

/**
 * @author Jeff Risberg
 * @since 11/02/17
 */
public class FieldDesc {
    protected String name;

    public FieldDesc(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
