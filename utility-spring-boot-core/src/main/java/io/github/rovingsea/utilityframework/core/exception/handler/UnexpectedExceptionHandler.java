package io.github.rovingsea.utilityframework.core.exception.handler;

import io.github.rovingsea.utilityframework.core.exception.UtilityException;
import io.github.rovingsea.utilityframework.core.response.ControllerExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Except for spring exceptions and {@link UtilityException},
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

    public UnexpectedExceptionHandler(ApplicationContext context) {
        super(context.getBean(ControllerExceptionResponse.class));
    }

    @Override
    public void doHandle(Map<String, Object> responseBody, Map<String, String> responseHeader,
                         HttpServletRequest request, HttpServletResponse response,
                         Throwable throwable) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(throwable);
        logger.error(NestedExceptionUtils.buildMessage(throwable.getMessage(), rootCause));
        throwable.printStackTrace();
        responseBody.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseBody.put("message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
