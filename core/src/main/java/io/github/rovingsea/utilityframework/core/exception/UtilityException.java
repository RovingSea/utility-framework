package io.github.rovingsea.utilityframework.core.exception;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public abstract class UtilityException extends RuntimeException {

    protected final int code;

    protected final String message;

    public UtilityException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
