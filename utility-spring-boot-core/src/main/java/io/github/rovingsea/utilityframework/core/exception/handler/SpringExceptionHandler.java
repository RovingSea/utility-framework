package io.github.rovingsea.utilityframework.core.exception.handler;

import io.github.rovingsea.utilityframework.core.exception.ExceptionDispatcher;
import io.github.rovingsea.utilityframework.core.response.ControllerExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.NestedExceptionUtils;
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

    protected Logger logger = LoggerFactory.getLogger(getClass());

    static {
        // Eagerly load the NestedExceptionUtils class to avoid classloader deadlock
        // issues on OSGi when calling getMessage(). Reported by Don Brown; SPR-5607.
        NestedExceptionUtils.class.getName();
    }

    public SpringExceptionHandler(ApplicationContext context) {
        super(context.getBean(ControllerExceptionResponse.class));
    }

    @Override
    public void doHandle(Map<String, Object> responseBody, Map<String, String> responseHeader,
                           HttpServletRequest request, HttpServletResponse response,
                           Throwable throwable) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(throwable);
        logger.error(NestedExceptionUtils.buildMessage(throwable.getMessage(), rootCause));
        if (throwable instanceof HttpMediaTypeException) {
            responseBody.put("code", HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
            responseBody.put("message", HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
            response.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        } else if (throwable instanceof HttpRequestMethodNotSupportedException) {
            responseBody.put("code", HttpStatus.METHOD_NOT_ALLOWED.value());
            responseBody.put("message", HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
            response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        } else {
            responseBody.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseBody.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
