package io.github.rovingsea.utilityframework.core.response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface ControllerExceptionResponse {

    default void setResponseBody(Map<String, Object> responseBody, Throwable throwable,
                                 HttpServletRequest request, HttpServletResponse response) {

    }

    default void setResponseHeader(Map<String, String> responseHeader, Throwable throwable,
                                   HttpServletRequest request, HttpServletResponse response) {

    }

}
