package io.github.rovingsea.utilityframework.spring.web.exception;

import io.github.rovingsea.utilityframework.spring.web.utils.Throw;
import io.github.rovingsea.utilityframework.spring.web.exception.handler.ExpectedExceptionHandler;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * An enumeration base interface is provided. It exists for use with {@link Throw}.
 * All its implementation classes can be used as parameters in methods of {@link Throw},
 * and then {@link ExpectedException} is constructed by obtaining {@link #getCode()} and {@link #getMessage()},
 * and then {@link ExpectedException} is dispatched to {@link ExpectedExceptionHandler}
 * for processing through {@link ExceptionDispatcher}.
 * Finally, {@link #getCode()} and {@link #getMessage()} are returned as the contents
 * of the response body.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.1
 */
public interface ExceptionEnum extends ExceptionPostProcessor {

    /**
     * The exception code accompanying the exception,
     * which is usually used as the response value in the response body
     *
     * @return The exception code, default value is {@link HttpStatus#INTERNAL_SERVER_ERROR}
     */
    default int getCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    /**
     * The exception message accompanying the exception,
     * which is usually used as the response value in the response body
     *
     * @return The exception message, default value is{@link HttpStatus#INTERNAL_SERVER_ERROR}
     */
    default String getMessage() {
        return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    }

}
