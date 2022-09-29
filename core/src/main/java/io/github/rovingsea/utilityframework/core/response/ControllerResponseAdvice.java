package io.github.rovingsea.utilityframework.core.response;

import io.github.rovingsea.utilityframework.core.UtilityContextException;
import io.github.rovingsea.utilityframework.core.exception.ExceptionDispatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Executable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Perform unified processing operations after the {@link Controller} returns the results.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ControllerReturnResponse controllerReturnResponse;

    public ControllerResponseAdvice(ApplicationContext context) {
        ControllerReturnResponse controllerReturnResponse = context.getBean(ControllerReturnResponse.class);
        if (controllerReturnResponse == null) {
            throw new UtilityContextException("Response configuration needs to be set");
        }
        this.controllerReturnResponse = controllerReturnResponse;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Executable executable = returnType.getExecutable();
        Class<?> declaringClass = executable.getDeclaringClass();
        String name = executable.getName();
        return declaringClass != ExceptionDispatcher.class;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        Map<String, String> responseHeader = new LinkedHashMap<>();
        this.controllerReturnResponse.setResponseBody(responseBody, body, request, response);
        this.controllerReturnResponse.setResponseHeader(responseHeader, body, request, response);
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setAll(responseHeader);
        return responseBody;
    }
}

