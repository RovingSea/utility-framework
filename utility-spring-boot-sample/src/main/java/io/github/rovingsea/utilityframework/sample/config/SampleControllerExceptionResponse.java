package io.github.rovingsea.utilityframework.sample.config;

import io.github.rovingsea.utilityframework.core.exception.UtilityException;
import io.github.rovingsea.utilityframework.core.response.ControllerExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class SampleControllerExceptionResponse implements ControllerExceptionResponse {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    static {
        // Eagerly load the NestedExceptionUtils class to avoid classloader deadlock
        // issues on OSGi when calling getMessage(). Reported by Don Brown; SPR-5607.
        NestedExceptionUtils.class.getName();
    }

    @Override
    public void setResponseBody(Map<String, Object> responseBody,
                                Throwable throwable,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(throwable);
        logger.error(NestedExceptionUtils.buildMessage(throwable.getMessage(), rootCause));
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