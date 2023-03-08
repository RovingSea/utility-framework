package io.github.utilityframework.boot.web.exception.handler;

import io.github.utilityframework.boot.web.exception.ExceptionDispatcher;
import io.github.utilityframework.boot.web.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * In order to avoid conflict with the exception handling method of the
 * spring framework itself, it will handle it instead of the spring framework.
 * </p>
 * <p>
 * The reason why we do this is that in the spring boot utility web, all exceptions
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

    public SpringExceptionHandler() {
        httpStatusMap = new ConcurrentHashMap<>();
        httpStatusMap.put(HttpMediaTypeException.class, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        httpStatusMap.put(HttpRequestMethodNotSupportedException.class, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    public void doHandle(Response response, Throwable throwable) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(throwable);
        logger.error(NestedExceptionUtils.buildMessage(throwable.getMessage(), rootCause));
        HttpStatus httpStatus = this.httpStatusMap.getOrDefault(throwable.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        // Second initialization
        response.getBody().put("code", httpStatus.value());
        response.getBody().put("message", httpStatus.getReasonPhrase());
        response.setHttpStatus(httpStatus);
    }

}
