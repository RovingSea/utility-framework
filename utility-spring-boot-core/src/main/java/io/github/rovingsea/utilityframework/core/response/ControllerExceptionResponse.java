package io.github.rovingsea.utilityframework.core.response;

import io.github.rovingsea.utilityframework.core.exception.UtilityException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Perform unified processing when an exception occurs in the controller.
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface ControllerExceptionResponse {

    /**
     * Setting response-body
     * @param responseBody response-body
     * @param e exception occurs in the controller
     * @param request entire request object
     * @param response entire response object
     */
    default void setResponseBody(Map<String, Object> responseBody, UtilityException e,
                                 HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * Setting response-header
     * @param responseHeader response-header
     * @param e exception occurs in the controller
     * @param request entire request object
     * @param response entire response object
     */
    default void setResponseHeader(Map<String, String> responseHeader, UtilityException e,
                                   HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(e.getHttpStatus().value());
    }

}
