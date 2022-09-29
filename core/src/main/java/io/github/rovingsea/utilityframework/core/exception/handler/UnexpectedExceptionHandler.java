package io.github.rovingsea.utilityframework.core.exception.handler;

import io.github.rovingsea.utilityframework.core.response.ControllerExceptionResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class UnexpectedExceptionHandler extends AbstractExceptionHandler {

    public UnexpectedExceptionHandler(ApplicationContext context) {
        super(context.getBean(ControllerExceptionResponse.class));
    }

    @Override
    public Object doHandle(Map<String, Object> responseBody, Map<String, String> responseHeader,
                           HttpServletRequest request, HttpServletResponse response,
                           Throwable throwable) {
        responseBody.put("code", HttpStatus.METHOD_NOT_ALLOWED.value());
        responseBody.put("message", HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        return responseBody;
    }
}
