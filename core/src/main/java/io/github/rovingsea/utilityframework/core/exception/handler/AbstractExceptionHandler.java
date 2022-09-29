package io.github.rovingsea.utilityframework.core.exception.handler;

import io.github.rovingsea.utilityframework.core.UtilityContextException;
import io.github.rovingsea.utilityframework.core.response.ControllerExceptionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public abstract class AbstractExceptionHandler {

    protected final ControllerExceptionResponse controllerExceptionResponse;

    protected AbstractExceptionHandler(ControllerExceptionResponse controllerExceptionResponse) {
        if (controllerExceptionResponse == null) {
            throw new UtilityContextException("Response configuration needs to be set");
        }
        this.controllerExceptionResponse = controllerExceptionResponse;
    }

    public abstract Object doHandle(Map<String, Object> responseBody, Map<String, String> responseHeader,
                                        HttpServletRequest request, HttpServletResponse response,
                                        Throwable throwable);

}
