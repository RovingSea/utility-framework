package io.github.rovingsea.utilityframework.sample.exception;

import io.github.rovingsea.utilityframework.core.exception.BaseEnum;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public enum StudentError implements BaseEnum {

    QUERY_BY_ID(4001, "id cannot be less than 0"),

    QUERY_BY_AGE(4002, "age cannot be less than 0 or more than 150");

    private final int code;

    private final String message;

    StudentError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

