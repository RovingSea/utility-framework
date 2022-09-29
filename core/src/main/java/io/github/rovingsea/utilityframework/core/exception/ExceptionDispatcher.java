package io.github.rovingsea.utilityframework.core.exception;

import io.github.rovingsea.utilityframework.core.exception.handler.AbstractExceptionHandler;
import io.github.rovingsea.utilityframework.core.exception.handler.SpringExceptionHandler;
import io.github.rovingsea.utilityframework.core.exception.handler.UnexpectedExceptionHandler;
import io.github.rovingsea.utilityframework.core.exception.handler.UtilityExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestControllerAdvice
public class ExceptionDispatcher {

    private final ApplicationContext context;


    public ExceptionDispatcher(ApplicationContext context) {
        this.context = context;
    }

    @ExceptionHandler
    public Object doDispatch(Throwable throwable) {
        AbstractExceptionHandler exceptionHandler = getExceptionHandler(throwable);
        Map<String, Object> responseBody = newResponseBody();
        Map<String, String> responseHeader = newResponseHeader();
        HttpServletRequest request = getHttpServletRequest();
        HttpServletResponse response = getHttpServletResponse();
        exceptionHandler.doHandle(responseBody, responseHeader, request, response, throwable);
        return responseBody;
    }

    private AbstractExceptionHandler getExceptionHandler(Throwable throwable) {
        AbstractExceptionHandler exceptionHandler = this.context.getBean(UnexpectedExceptionHandler.class);
        if (throwable instanceof ServletException) {
            exceptionHandler = this.context.getBean(SpringExceptionHandler.class);
        }
        if (throwable instanceof UtilityException) {
            exceptionHandler = this.context.getBean(UtilityExceptionHandler.class);
        }
        return exceptionHandler;
    }

    private HttpServletResponse getHttpServletResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = null;
        if (attributes != null) {
            response = attributes.getResponse();
        }
        if (response == null) {
            throw new IllegalThreadStateException("The current thread cannot get a response object");
        }
        return response;
    }

    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        if (request == null) {
            throw new IllegalThreadStateException("The current thread cannot get a response object");
        }
        return request;
    }

    private Map<String, String> newResponseHeader() {
        return new LinkedHashMap<>();
    }

    private Map<String, Object> newResponseBody() {
        return new LinkedHashMap<>();
    }

}
