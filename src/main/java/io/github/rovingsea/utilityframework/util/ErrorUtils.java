package io.github.rovingsea.utilityframework.util;

import io.github.rovingsea.utilityframework.core.error.Error;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * Static convenience methods for errors : for getting {@link Error},
 * getting {@link HttpStatus} etc.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public abstract class ErrorUtils {

    /**
     * Convenience method to judge whether this error can be supported.
     *
     * @param error target error
     * @return whether this error can be supported
     */
    public static boolean supports(Throwable error) {
        Error annotation = error.getClass().getAnnotation(Error.class);
        return annotation != null;
    }

    /**
     * Convenience method to get {@link HttpStatus} of error
     *
     * @param error target error
     * @return {@link HttpStatus}
     */
    public static HttpStatus getHttpStatus(Throwable error) {
        if (!supports(error)) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        Error errorAnnotation = error.getClass().getAnnotation(Error.class);
        return errorAnnotation.status();
    }

    /**
     * Convenience method to get {@link Error} of error
     *
     * @param error target error
     * @return {@link Error}
     */
    public static Error getAnnotation(Throwable error) {
        if (!supports(error)) {
            return null;
        }
        return error.getClass().getAnnotation(Error.class);
    }

}
