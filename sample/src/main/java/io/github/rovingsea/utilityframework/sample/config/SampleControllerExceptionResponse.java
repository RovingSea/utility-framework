package io.github.rovingsea.utilityframework.sample.config;

import io.github.rovingsea.utilityframework.core.exception.UtilityException;
import io.github.rovingsea.utilityframework.core.response.ControllerExceptionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class SampleControllerExceptionResponse implements ControllerExceptionResponse {

    @Override
    public void setResponseBody(Map<String, Object> responseBody,
                                Throwable throwable,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        UtilityException exception = (UtilityException) throwable;
        responseBody.put("code", exception.getCode());
        responseBody.put("message", exception.getMessage());
    }

    @Override
    public void setResponseHeader(Map<String, String> responseHeader,
                                  Throwable throwable,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        ControllerExceptionResponse.super
                .setResponseHeader(responseHeader, throwable, request, response);
    }
}
