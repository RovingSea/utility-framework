package io.github.rovingsea.utilityframework.core.exception.handler;

import io.github.rovingsea.utilityframework.core.exception.UtilityException;
import io.github.rovingsea.utilityframework.core.response.ControllerExceptionResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Except for spring exceptions and {@link UtilityException},
 * other exceptions will be handled by it.
 * @author Haixin Wu
 * @since 1.0.0
 */
public class UnexpectedExceptionHandler extends AbstractExceptionHandler {

    public UnexpectedExceptionHandler(ApplicationContext context) {
        super(context.getBean(ControllerExceptionResponse.class));
    }

    @Override
    public void doHandle(Map<String, Object> responseBody, Map<String, String> responseHeader,
                         HttpServletRequest request, HttpServletResponse response,
                         Throwable throwable) {
        responseBody.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseBody.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
