package io.github.utilityframework.boot.web.exception;

import io.github.utilityframework.boot.web.exception.handler.AbstractExceptionHandler;
import io.github.utilityframework.boot.web.exception.handler.ExpectedExceptionHandler;
import io.github.utilityframework.boot.web.exception.handler.SpringExceptionHandler;
import io.github.utilityframework.boot.web.exception.handler.UnexpectedExceptionHandler;
import io.github.utilityframework.boot.web.response.Response;
import io.github.utilityframework.boot.web.response.ResponseFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private final Map<Class<? extends Exception>, AbstractExceptionHandler> handlerSelector;

    public ExceptionDispatcher(ApplicationContext context) {
        this.context = context;
        handlerSelector = new ConcurrentHashMap<>();
        handlerSelector.put(ServletException.class, context.getBean(SpringExceptionHandler.class));
        handlerSelector.put(ExpectedException.class, context.getBean(ExpectedExceptionHandler.class));
    }

    /**
     * The entry where all exceptions occurred in the Controller are handled.
     *
     * @param throwable exception occurred in the Controller
     * @return response-body
     */
    @ExceptionHandler
    public Response doDispatch(Throwable throwable) {
        AbstractExceptionHandler exceptionHandler = getExceptionHandler(throwable);
        Response exceptionResponse = ResponseFactory.createExceptionResponse(throwable);
        exceptionHandler.doHandle(exceptionResponse, throwable);
        return exceptionResponse;
    }

    /**
     * Get the exception handler for by exception type
     *
     * @param throwable exception occurred in the Controller
     * @return subclass of {@link AbstractExceptionHandler}
     */
    private AbstractExceptionHandler getExceptionHandler(Throwable throwable) {
        return this.handlerSelector.getOrDefault(throwable.getClass(),
                this.context.getBean(UnexpectedExceptionHandler.class));
    }

}
