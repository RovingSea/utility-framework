package org.utilityframework.spring.boot.core.error.handler;


import org.springframework.http.HttpStatus;
import org.utilityframework.spring.boot.core.error.Error;
import org.utilityframework.spring.boot.core.response.Response;
import org.utilityframework.spring.boot.util.ErrorUtils;

/**
 * Without any <i>ErrorHandlers</i>, all <i>Errors</i> will be handled by it.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class DefaultGlobalErrorHandler extends AbstractErrorHandler {

    /**
     * Handle by default
     *
     * @param error An exception class with the annotation {@link Error}
     * @return The Response body content
     */
    @Override
    public Response<String> handle(Throwable error) {
        HttpStatus httpStatus = getHttpStatus(error);
        setHttpStatus(httpStatus);

        Error annotation = ErrorUtils.getAnnotation(error);
        assert annotation != null;
        return Response.failure(annotation.code(), annotation.message(), error.getMessage());
    }
}
