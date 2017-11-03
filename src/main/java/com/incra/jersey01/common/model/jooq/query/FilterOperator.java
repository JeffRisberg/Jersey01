package com.incra.jersey01.common.model.jooq.query;

/**
 * @author Jeff Risberg
 * @since 12/11/15
 */
public enum FilterOperator {
    Equal,
    NotEqual,
    Like,
    LikeIgnoreCase,
    GreaterThan,
    GreaterThanOrEqualTo,
    LessThan,
    LessThanOrEqualTo
}
