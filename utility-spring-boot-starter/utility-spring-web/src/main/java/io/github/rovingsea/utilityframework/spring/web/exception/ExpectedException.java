package io.github.rovingsea.utilityframework.spring.web.exception;

import io.github.rovingsea.utilityframework.spring.web.exception.handler.ExpectedExceptionHandler;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * Its subclasses will be processed by the module of Utility exception.
 * </p>
 * <p>
 * After using <i>utility framework</i>, the base class of the exception thrown manually
 * by the programmer must be it.
 * The {@link ExceptionDispatcher} will select {@link ExpectedExceptionHandler} to handle it.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ExpectedException extends RuntimeException {

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
    /**
     * The associated exception enumeration when this exception is thrown,
     * its existence will provide <i>code</i>, <i>message</i> and processing method.
     */
    protected final ExceptionEnum exceptionEnum;
    /**
     * Its existence provides context information when an exception is thrown.
     * <p>
     * For example, I found meat I didn't like, but now I have two pets.
     * The opinion is to decide which pet to eat.
     * </p>
     */
    protected final Object opinion;

    /**
     * Trigger the {@link ExceptionEnum#postProcessAfterThrow(Object)}.
     */
    public void doProcess() {
        if (getBaseEnum() == null || getOpinion() == null) {
            return;
        }
        getBaseEnum().postProcessAfterThrow(getOpinion());
    }

    public ExpectedException(ExceptionEnum exceptionEnum) {
        this(exceptionEnum, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ExpectedException(ExceptionEnum exceptionEnum, HttpStatus httpStatus) {
        this(exceptionEnum, httpStatus, null);
    }

    public ExpectedException(ExceptionEnum exceptionEnum, HttpStatus httpStatus, Object opinion) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
        this.exceptionEnum = exceptionEnum;
        this.httpStatus = httpStatus;
        this.opinion = opinion;
    }

    public ExpectedException(int code, String message) {
        this(code, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ExpectedException(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
        this.exceptionEnum = null;
        this.opinion = null;
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

    public ExceptionEnum getBaseEnum() {
        return exceptionEnum;
    }

    public Object getOpinion() {
        return opinion;
    }

}
