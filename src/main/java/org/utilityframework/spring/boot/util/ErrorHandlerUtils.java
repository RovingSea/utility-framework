package org.utilityframework.spring.boot.util;

import org.springframework.util.CollectionUtils;
import org.utilityframework.spring.boot.core.error.handler.AbstractErrorHandler;
import org.utilityframework.spring.boot.core.error.handler.ErrorHandler;
import org.utilityframework.spring.boot.exception.UsingErrorAnnotationException;

import java.util.List;

/**
 * Static convenience methods for error handlers: for supported errors, etc.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public abstract class ErrorHandlerUtils {

    /**
     * Convenience method to judge whether this error handler can be supported.
     *
     * @param errorHandler target error handler
     * @return whether this error handler can be supported
     */
    public static boolean supports(AbstractErrorHandler errorHandler) {
        ErrorHandler annotation = errorHandler.getClass().getAnnotation(ErrorHandler.class);
        return annotation != null;
    }


    /**
     * Convenience method to get errors handled by error handler.
     *
     * @param errorHandler target error handler
     * @return the list for storing errors
     */
    @SuppressWarnings("unchecked")
    public static List<? extends Throwable> getErrors(AbstractErrorHandler errorHandler) {
        if (!supports(errorHandler)) {
            throw new UsingErrorAnnotationException("Handling this exception is not supported");
        }
        ErrorHandler annotation = errorHandler.getClass().getAnnotation(ErrorHandler.class);
        Class<? extends Throwable>[] errors = annotation.value();
        return (List<? extends Throwable>) CollectionUtils.arrayToList(errors);
    }

}
