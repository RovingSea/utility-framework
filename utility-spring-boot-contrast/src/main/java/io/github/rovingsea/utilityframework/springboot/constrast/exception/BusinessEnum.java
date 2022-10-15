package io.github.rovingsea.utilityframework.springboot.constrast.exception;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public enum BusinessEnum {

    STUDENT_QUERY_BY_ID(4001, "id cannot be less than 0"),
    STUDENT_QUERY_BY_AGE(4002, "age cannot be less than 0 or more than 150");


    private int code;

    private String message;

    BusinessEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

