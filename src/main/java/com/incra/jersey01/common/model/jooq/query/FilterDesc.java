package com.incra.jersey01.common.model.jooq.query;

import org.jooq.TableField;

/**
 * @author Jeff Risberg
 * @since 11/24/16
 */
public class FilterDesc {
    private TableField field;
    private FilterOperator operator;
    private Object value;

    public FilterDesc(TableField field, FilterOperator operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public TableField getField() {
        return field;
    }

    public FilterOperator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }
}
