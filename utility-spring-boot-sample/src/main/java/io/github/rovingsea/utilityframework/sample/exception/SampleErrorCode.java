package io.github.rovingsea.utilityframework.sample.exception;

import io.github.rovingsea.utilityframework.core.exception.BaseEnum;

/**
 *
 * @author Haixin Wu
 * @since 1.0.1
 */
public enum SampleErrorCode implements BaseEnum {
    /**
     * age is incorrect.
     */
    AGE_INCORRECTNESS(400001, "age is incorrect"),
    /**
     * name is incorrect.
     */
    NAME_INCORRECTNESS(400002, "name is incorrect"),
    /**
     * test inter server error.
     */
    TEST_INTER_SERVER_ERROR(500001, "test inter server error");


    private final int code;

    private final String message;
    SampleErrorCode(int code, String message) {
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
