package io.github.rovingsea.utilityframework.core.response;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 * Configuration interface is needs to be implemented, it provides the operation
 * of setting the request header and request body after the {@link Controller}
 * returns the result.
 * </p>
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface ControllerReturnResponse {


    default void setResponseBody(Map<String, Object> responseBody, Object returnValue,
                                 ServerHttpRequest request, ServerHttpResponse response) {

    }

    default void setResponseHeader(Map<String, String> responseHeader, Object returnValue,
                                   ServerHttpRequest request, ServerHttpResponse response) {

    }

}

