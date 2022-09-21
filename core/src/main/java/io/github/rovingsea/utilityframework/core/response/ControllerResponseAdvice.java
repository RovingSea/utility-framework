package io.github.rovingsea.utilityframework.core.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Perform unified processing operations after the {@link Controller} returns the results.
 * </p>
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ResponseConfiguration responseConfiguration;

    public ControllerResponseAdvice(ResponseConfiguration responseConfiguration) {
        this.responseConfiguration = responseConfiguration;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        Map<String, String> responseHeader = new LinkedHashMap<>();
        this.responseConfiguration.setResponseBody(responseBody);
        this.responseConfiguration.setResponseHeader(responseHeader);
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setAll(responseHeader);
        return responseBody;
    }
}

