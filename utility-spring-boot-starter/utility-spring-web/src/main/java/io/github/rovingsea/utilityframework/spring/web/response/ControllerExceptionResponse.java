package io.github.rovingsea.utilityframework.spring.web.response;

import io.github.rovingsea.utilityframework.spring.web.exception.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Perform unified processing when an exception occurs in the controller.
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface ControllerExceptionResponse {

    Logger logger = LoggerFactory.getLogger(ControllerExceptionResponse.class);
    /**
     * Setting response-body
     * @param responseBody response-body
     * @param e exception occurs in the controller
     * @param request entire request object
     * @param response entire response object
     */
    default void setResponseBody(Map<String, Object> responseBody, ExpectedException e,
                                 HttpServletRequest request, HttpServletResponse response) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(e);
        logger.error(NestedExceptionUtils.buildMessage(e.getMessage(), rootCause));
        responseBody.put("code", e.getCode());
        responseBody.put("message", e.getMessage());
    }

    /**
     * Setting response-header
     * @param responseHeader response-header
     * @param e exception occurs in the controller
     * @param request entire request object
     * @param response entire response object
     */
    default void setResponseHeader(Map<String, String> responseHeader, ExpectedException e,
                                   HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(e.getHttpStatus().value());
    }

}
