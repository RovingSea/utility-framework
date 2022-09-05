package io.github.rovingsea.utilityframework.core.error.handler;


import io.github.rovingsea.utilityframework.core.error.Error;
import io.github.rovingsea.utilityframework.core.response.Response;
import io.github.rovingsea.utilityframework.util.ErrorUtils;
import org.springframework.http.HttpStatus;

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
