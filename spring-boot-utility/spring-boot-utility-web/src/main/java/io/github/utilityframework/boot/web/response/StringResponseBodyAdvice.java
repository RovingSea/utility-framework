package io.github.utilityframework.boot.web.response;

import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Perform unified processing operations after the {@link Controller} that return type
 * is {@link String}.
 *
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
@RestControllerAdvice
public class StringResponseBodyAdvice implements ResponseBodyAdvice<String> {

    private final ResponseProperties properties;

    public StringResponseBodyAdvice(ApplicationContext context) {
        this.properties = context.getBean(ResponseProperties.class);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (converterType == StringHttpMessageConverter.class) {
            return true;
        }
        return false;
    }

    @Override
    public String beforeBodyWrite(String body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        Response returnResponse = ResponseFactory.createReturnResponse();
        returnResponse.getBody().put(this.properties.getStorageDataName(), body);
        return JSON.toJSONString(returnResponse.getBody());
    }
}
