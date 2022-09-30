package io.github.rovingsea.utilityframework.core.exception.handler;

import io.github.rovingsea.utilityframework.core.exception.ExceptionDispatcher;
import io.github.rovingsea.utilityframework.core.response.ControllerExceptionResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>
 * In order to avoid conflict with the exception handling method of the
 * spring framework itself, it will handle it instead of the spring framework.
 * </p>
 * <p>
 * The reason why we do this is that in the utility framework, all exceptions
 * will eventually reach {@link ExceptionDispatcher}, but {@link HttpMediaTypeException}
 * and {@link HttpRequestMethodNotSupportedException} are not within our scope.
 * </p>
 * <p>
 * Therefore, I can only create a new class to handle Spring exceptions.
 * If there is a better solution, you are welcome to contribute.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class SpringExceptionHandler extends AbstractExceptionHandler {

    public SpringExceptionHandler(ApplicationContext context) {
        super(context.getBean(ControllerExceptionResponse.class));
    }

    @Override
    public void doHandle(Map<String, Object> responseBody, Map<String, String> responseHeader,
                           HttpServletRequest request, HttpServletResponse response,
                           Throwable throwable) {
        if (throwable instanceof HttpMediaTypeException) {
            responseBody.put("code", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
            responseBody.put("message", HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
            response.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        }
        if (throwable instanceof HttpRequestMethodNotSupportedException) {
            responseBody.put("code", HttpStatus.METHOD_NOT_ALLOWED.value());
            responseBody.put("message", HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
            response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        }
    }
}
