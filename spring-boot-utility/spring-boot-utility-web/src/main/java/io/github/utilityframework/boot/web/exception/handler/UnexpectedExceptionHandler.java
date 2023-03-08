package io.github.utilityframework.boot.web.exception.handler;

import io.github.utilityframework.boot.web.exception.ExpectedException;
import io.github.utilityframework.boot.web.response.DefaultResponseBody;
import io.github.utilityframework.boot.web.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;

/**
 * Except for spring exceptions and {@link ExpectedException},
 * other exceptions will be handled by it.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class UnexpectedExceptionHandler extends AbstractExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    static {
        // Eagerly load the NestedExceptionUtils class to avoid classloader deadlock
        // issues on OSGi when calling getMessage(). Reported by Don Brown; SPR-5607.
        NestedExceptionUtils.class.getName();
    }

    @Override
    public void doHandle(Response response, Throwable throwable) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(throwable);
        logger.error("This is a bug, please fix it in time:\n{}",
                NestedExceptionUtils.buildMessage(throwable.getMessage(), rootCause));
        throwable.printStackTrace();
        // Second initialization
        response.putDataOnBody(DefaultResponseBody::getCode, HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.putDataOnBody(DefaultResponseBody::getMessage, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
