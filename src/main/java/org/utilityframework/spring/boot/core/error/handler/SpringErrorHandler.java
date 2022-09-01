package org.utilityframework.spring.boot.core.error.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.utilityframework.spring.boot.core.error.ErrorHandlerDispatcher;
import org.utilityframework.spring.boot.core.response.Response;

/**
 * <p>
 * In order to avoid conflict with the exception handling method of the
 * spring framework itself, it will handle it instead of the spring framework.
 * </p>
 * <p>
 * The reason why we do this is that in the utility framework, all exceptions
 * will eventually reach {@link ErrorHandlerDispatcher}, but {@link HttpMediaTypeException}
 * and {@link HttpRequestMethodNotSupportedException} are not within our scope.
 * </p>
 * <p>
 * Therefore, I can only raise its priority so that it can be handled of in
 * advance before it reaches {@link ErrorHandlerDispatcher}.
 * If there is a better solution, you are welcome to contribute.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringErrorHandler {

    @ExceptionHandler({HttpMediaTypeException.class})
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Response<String> handleHttpMediaTypeException(Throwable err) {
        return Response.failure(HttpStatus.UNSUPPORTED_MEDIA_TYPE, err.getMessage());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Response<String> handleHttpRequestMethodNotSupportedException(Throwable err) {
        return Response.failure(HttpStatus.METHOD_NOT_ALLOWED, err.getMessage());
    }

}
