package io.github.rovingsea.utilityframework.core.exception.handler;

import io.github.rovingsea.utilityframework.core.UtilityContextException;
import io.github.rovingsea.utilityframework.core.exception.ExceptionDispatcher;
import io.github.rovingsea.utilityframework.core.response.ControllerExceptionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * The base class of the exception handler, <br>
 * its subclasses are used to handle exceptions received by {@link ExceptionDispatcher}.
 * @author Haixin Wu
 * @since 1.0.0
 */
public abstract class AbstractExceptionHandler {

    /**
     * Giving subclasses the ability to set responses
     */
    protected final ControllerExceptionResponse controllerExceptionResponse;

    protected AbstractExceptionHandler(ControllerExceptionResponse controllerExceptionResponse) {
        if (controllerExceptionResponse == null) {
            throw new UtilityContextException("Response configuration needs to be set");
        }
        this.controllerExceptionResponse = controllerExceptionResponse;
    }

    /**
     * As an exception handling entry in {@link ExceptionDispatcher}.
     * @param responseBody response-body
     * @param responseHeader response-header
     * @param request entire request object
     * @param response entire response object
     * @param throwable exception received by {@link ExceptionDispatcher}
     */
    public abstract void doHandle(Map<String, Object> responseBody, Map<String, String> responseHeader,
                                        HttpServletRequest request, HttpServletResponse response,
                                        Throwable throwable);

    public void setResponseHeader(Map<String, String> responseHeader, HttpServletResponse response) {
        responseHeader.keySet().forEach(k -> response.addHeader(k, responseHeader.get(k)));
    }

}
