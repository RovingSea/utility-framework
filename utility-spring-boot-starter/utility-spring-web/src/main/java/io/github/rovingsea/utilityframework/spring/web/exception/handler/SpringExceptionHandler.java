package io.github.rovingsea.utilityframework.spring.web.exception.handler;

import io.github.rovingsea.utilityframework.spring.web.exception.ExceptionDispatcher;
import io.github.rovingsea.utilityframework.spring.web.response.ControllerExceptionResponse;
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
import java.util.concurrent.ConcurrentHashMap;

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

    private final Map<Class<? extends Exception>, HttpStatus> httpStatusMap;

    static {
        // Eagerly load the NestedExceptionUtils class to avoid classloader deadlock
        // issues on OSGi when calling getMessage(). Reported by Don Brown; SPR-5607.
        NestedExceptionUtils.class.getName();
    }

    public SpringExceptionHandler(ApplicationContext context) {
        super(context.getBean(ControllerExceptionResponse.class));
        httpStatusMap = new ConcurrentHashMap<>();
        httpStatusMap.put(HttpMediaTypeException.class, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        httpStatusMap.put(HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public void doHandle(Map<String, Object> responseBody, Map<String, String> responseHeader,
                         HttpServletRequest request, HttpServletResponse response,
                         Throwable throwable) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(throwable);
        logger.error(NestedExceptionUtils.buildMessage(throwable.getMessage(), rootCause));
        HttpStatus httpStatus = this.httpStatusMap.getOrDefault(throwable.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        setResponse(responseBody, response, httpStatus);
    }

    private void setResponse(Map<String, Object> responseBody, HttpServletResponse response,
                             HttpStatus httpStatus) {
        responseBody.put("code", httpStatus.value());
        responseBody.put("message", httpStatus.getReasonPhrase());
        response.setStatus(httpStatus.value());
    }
}
