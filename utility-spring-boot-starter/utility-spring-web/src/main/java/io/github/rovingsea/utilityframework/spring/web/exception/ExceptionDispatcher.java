package io.github.rovingsea.utilityframework.spring.web.exception;

import io.github.rovingsea.utilityframework.spring.web.utils.HttpServletRequestUtils;
import io.github.rovingsea.utilityframework.spring.web.utils.HttpServletResponseUtils;
import io.github.rovingsea.utilityframework.spring.web.exception.handler.AbstractExceptionHandler;
import io.github.rovingsea.utilityframework.spring.web.exception.handler.SpringExceptionHandler;
import io.github.rovingsea.utilityframework.spring.web.exception.handler.UnexpectedExceptionHandler;
import io.github.rovingsea.utilityframework.spring.web.exception.handler.ExpectedExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * When any exception occurs in the Controller,
 * it will be received and the response will be set finally.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestControllerAdvice
public class ExceptionDispatcher {

    private final ApplicationContext context;

    public ExceptionDispatcher(ApplicationContext context) {
        this.context = context;
    }

    /**
     * The entry where all exceptions occurred in the Controller are handled.
     *
     * @param throwable exception occurred in the Controller
     * @return response-body
     */
    @ExceptionHandler
    public HandlingExceptionResult doDispatch(Throwable throwable) {
        AbstractExceptionHandler exceptionHandler = getExceptionHandler(throwable);
        Map<String, Object> responseBody = newResponseBody();
        Map<String, String> responseHeader = newResponseHeader();
        HttpServletRequest request = getHttpServletRequest();
        HttpServletResponse response = getHttpServletResponse();
        exceptionHandler.doHandle(responseBody, responseHeader, request, response, throwable);
        exceptionHandler.setResponseHeader(responseHeader, response);
        return HandlingExceptionResult.init(responseBody, responseHeader);
    }

    /**
     * Get the exception handler for by exception type
     *
     * @param throwable exception occurred in the Controller
     * @return subclass of {@link AbstractExceptionHandler}
     */
    private AbstractExceptionHandler getExceptionHandler(Throwable throwable) {
        AbstractExceptionHandler exceptionHandler
                = this.context.getBean(UnexpectedExceptionHandler.class);
        if (throwable instanceof ServletException) {
            exceptionHandler = this.context.getBean(SpringExceptionHandler.class);
        }
        if (throwable instanceof ExpectedException) {
            exceptionHandler = this.context.getBean(ExpectedExceptionHandler.class);
        }
        return exceptionHandler;
    }

    private HttpServletResponse getHttpServletResponse() {
        return HttpServletResponseUtils.instance();
    }

    private HttpServletRequest getHttpServletRequest() {
        return HttpServletRequestUtils.instance();
    }

    private Map<String, String> newResponseHeader() {
        return new LinkedHashMap<>();
    }

    private Map<String, Object> newResponseBody() {
        return new LinkedHashMap<>();
    }

}
