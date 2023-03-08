package io.github.utilityframework.boot.web.response;

import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>
 * Perform unified processing operations after the {@link Controller} returns the results.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 * @version 1.0.0
 */
@RestControllerAdvice
public class JsonResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final ResponseProperties properties;

    public JsonResponseBodyAdvice(ApplicationContext context) {
        this.properties = context.getBean(ResponseProperties.class);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (converterType == MappingJackson2HttpMessageConverter.class) {
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
        if (body instanceof Response && ((Response) body).isExceptionType()) {
            return ((Response) body).write(request, response);
        }
        Response returnResponse = ResponseFactory.createReturnResponse();
        returnResponse.getBody().put(this.properties.getStorageDataName(), body);
        return returnResponse.write(request, response);
    }

}

