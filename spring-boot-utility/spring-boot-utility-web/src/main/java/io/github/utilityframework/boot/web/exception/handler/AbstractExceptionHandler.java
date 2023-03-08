package io.github.utilityframework.boot.web.exception.handler;


import io.github.utilityframework.boot.web.exception.ExceptionDispatcher;
import io.github.utilityframework.boot.web.response.Response;

/**
 * The base class of the exception handler, <br>
 * its subclasses are used to handle exceptions received by {@link ExceptionDispatcher}.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public abstract class AbstractExceptionHandler {

    /**
     * As an exception handling entry in {@link ExceptionDispatcher}.
     *
     * @param response  entire response object
     * @param throwable exception received by {@link ExceptionDispatcher}
     */
    public abstract void doHandle(Response response, Throwable throwable);

}
