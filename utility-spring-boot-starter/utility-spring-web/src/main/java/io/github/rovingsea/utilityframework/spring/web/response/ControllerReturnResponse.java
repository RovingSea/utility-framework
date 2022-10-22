package io.github.rovingsea.utilityframework.spring.web.response;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;

import java.util.Date;
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


    /**
     * Setting response-body
     * @param responseBody response-body
     * @param returnValue The results returned by the Controller
     * @param request entire request object
     * @param response entire response object
     */
    default void setResponseBody(Map<String, Object> responseBody, Object returnValue,
                                 ServerHttpRequest request, ServerHttpResponse response) {
        responseBody.put("code", 200000);
        responseBody.put("message", "success");
        responseBody.put("data", returnValue);
        responseBody.put("time", new Date());
    }

    /**
     * Setting response-header
     * @param responseHeader response-header
     * @param returnValue The results returned by the Controller
     * @param request entire request object
     * @param response entire response object
     */
    default void setResponseHeader(Map<String, String> responseHeader, Object returnValue,
                                   ServerHttpRequest request, ServerHttpResponse response) {

    }

}

