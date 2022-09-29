package io.github.rovingsea.utilityframework.sample.config;

import io.github.rovingsea.utilityframework.core.response.ControllerReturnResponse;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Component
public class SampleControllerReturnResponse implements ControllerReturnResponse {

    @Override
    public void setResponseBody(Map<String, Object> responseBody, Object returnValue,
                                ServerHttpRequest request, ServerHttpResponse response) {
        responseBody.put("code", 200000);
        responseBody.put("message", "success");
        responseBody.put("data", returnValue);
        responseBody.put("time", new Date());

    }

    @Override
    public void setResponseHeader(Map<String, String> responseHeader, Object returnValue,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        ControllerReturnResponse.super.setResponseHeader(responseHeader, returnValue, request, response);
    }
}
