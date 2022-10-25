package io.github.rovingsea.utilityframework.spring.web.sample.config;

import io.github.rovingsea.utilityframework.spring.web.exception.ExpectedException;
import io.github.rovingsea.utilityframework.spring.web.response.ControllerExceptionResponse;
import io.github.rovingsea.utilityframework.spring.web.response.ControllerReturnResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Configuration
public class ControllerResponseConfiguration {

    @Bean
    public ControllerExceptionResponse controllerExceptionResponse() {
        return new ControllerExceptionResponse() {

            private final Logger logger = LoggerFactory.getLogger(getClass());

            @Override
            public void setResponseBody(Map<String, Object> responseBody, ExpectedException e, HttpServletRequest request, HttpServletResponse response) {
                Throwable rootCause = NestedExceptionUtils.getRootCause(e);
                logger.error(NestedExceptionUtils.buildMessage(e.getMessage(), rootCause));
                responseBody.put("code", e.getCode());
                responseBody.put("message", e.getMessage());
            }

            @Override
            public void setResponseHeader(Map<String, String> responseHeader, ExpectedException e, HttpServletRequest request, HttpServletResponse response) {
                ControllerExceptionResponse.super.setResponseHeader(responseHeader, e, request, response);
            }
        };
    }

    @Bean
    public ControllerReturnResponse controllerReturnResponse() {
        return new ControllerReturnResponse() {
            @Override
            public void setResponseBody(Map<String, Object> responseBody, Object returnValue, ServerHttpRequest request, ServerHttpResponse response) {
                responseBody.put("code", 200000);
                responseBody.put("message", "success");
                responseBody.put("data", returnValue);
                responseBody.put("time", new Date());
            }

            @Override
            public void setResponseHeader(Map<String, String> responseHeader, Object returnValue, ServerHttpRequest request, ServerHttpResponse response) {
                ControllerReturnResponse.super.setResponseHeader(responseHeader, returnValue, request, response);
            }
        };
    }

}
