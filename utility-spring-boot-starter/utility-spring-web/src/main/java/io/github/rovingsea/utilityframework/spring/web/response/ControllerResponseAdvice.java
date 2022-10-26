package io.github.rovingsea.utilityframework.spring.web.response;

import com.alibaba.fastjson.JSON;
import io.github.rovingsea.utilityframework.spring.web.UtilityContextException;
import io.github.rovingsea.utilityframework.spring.web.exception.HandlingExceptionResult;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

    /**
     * Giving the ability to set responses
     */
    private final ControllerReturnResponse controllerReturnResponse;

    public ControllerResponseAdvice(ApplicationContext context) {
        ControllerReturnResponse controllerReturnResponse
                = context.getBean(ControllerReturnResponse.class);
        if (controllerReturnResponse == null) {
            throw new UtilityContextException("Response configuration needs to be set");
        }
        this.controllerReturnResponse = controllerReturnResponse;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (converterType == MappingJackson2HttpMessageConverter.class ||
                converterType == StringHttpMessageConverter.class) {
            return true;
        }
        return false;
    }

    /**
     * The results returned by the Controller will be processed here again,
     * and the final results will be used as the response-body
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return response-body
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        Map<String, String> responseHeader = new LinkedHashMap<>();
        if (body instanceof HandlingExceptionResult) {
            responseBody = ((HandlingExceptionResult) body).getResponseBody();
            responseHeader = ((HandlingExceptionResult) body).getResponseHeader();
            response.getHeaders().setAll(responseHeader);
            return JSON.toJSON(responseBody);
        }
        this.controllerReturnResponse.setResponseBody(responseBody, body, request, response);
        this.controllerReturnResponse.setResponseHeader(responseHeader, body, request, response);
        response.getHeaders().setAll(responseHeader);
        return body instanceof String ? JSON.toJSONString(responseBody) : JSON.toJSON(responseBody);
    }
}

