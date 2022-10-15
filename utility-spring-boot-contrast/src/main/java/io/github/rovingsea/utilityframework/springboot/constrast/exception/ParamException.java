package io.github.rovingsea.utilityframework.springboot.constrast.exception;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ParamException extends RuntimeException {

    private final int code;

    private final String message;

    public ParamException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ParamException(BusinessEnum businessEnum) {
        this.code = businessEnum.getCode();
        this.message = businessEnum.getMessage();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

