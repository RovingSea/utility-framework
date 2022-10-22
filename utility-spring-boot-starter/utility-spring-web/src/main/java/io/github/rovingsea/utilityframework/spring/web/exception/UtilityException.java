package io.github.rovingsea.utilityframework.spring.web.exception;

import org.springframework.http.HttpStatus;

/**
 * Its subclasses will be processed by the module of Utility exception.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class UtilityException extends RuntimeException {

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
    /**
     * It can be customized by programmers,
     * and its value will be used in the response header of the response.
     */
    protected final HttpStatus httpStatus;

    public UtilityException(int code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public UtilityException(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}