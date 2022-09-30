package io.github.rovingsea.utilityframework.core.exception;

import org.springframework.http.HttpStatus;

/**
 * Its subclasses will be processed by the module of Utility exception.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public abstract class UtilityException extends RuntimeException {

    /**
     * It can be customized by programmers, but it should be a detailed description of
     * {@link HttpStatus#value()} and it will be set to the request body <br>
     * For example, When response-header is set to {@link HttpStatus#BAD_REQUEST},
     * the code could be
     * <ul>
     *     <li>400001</li>
     *     <li>400002</li>
     *     <li>......</li>
     * </ul>
     */
    protected final int code;

    /**
     * It can be customized by programmers, but it should be a detailed description of
     * {@link HttpStatus#getReasonPhrase()} and it will be set to the request body <br>
     * For example, When response-header is set to {@link HttpStatus#BAD_REQUEST},
     * the message could be
     * <ul>
     *     <li>Parameter 'id' cannot be empty</li>
     *     <li>Parameter 'password' cannot be empty</li>
     *     <li>......</li>
     * </ul>
     */
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
