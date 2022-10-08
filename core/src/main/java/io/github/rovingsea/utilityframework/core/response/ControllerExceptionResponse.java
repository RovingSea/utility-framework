package io.github.rovingsea.utilityframework.core.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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
     * @param throwable exception occurs in the controller
     * @param request entire request object
     * @param response entire response object
     */
    default void setResponseBody(Map<String, Object> responseBody, Throwable throwable,
                                 HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * Setting response-header
     * @param responseHeader response-header
     * @param throwable exception occurs in the controller
     * @param request entire request object
     * @param response entire response object
     */
    default void setResponseHeader(Map<String, String> responseHeader, Throwable throwable,
                                   HttpServletRequest request, HttpServletResponse response) {
        ResponseStatus annotation = throwable.getClass().getAnnotation(ResponseStatus.class);
        if (annotation != null) {
            response.setStatus(annotation.value().value());
            return;
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
