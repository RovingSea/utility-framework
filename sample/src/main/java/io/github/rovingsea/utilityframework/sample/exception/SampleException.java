package io.github.rovingsea.utilityframework.sample.exception;

import io.github.rovingsea.utilityframework.core.error.Error;
import org.springframework.http.HttpStatus;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Error(status = HttpStatus.BAD_REQUEST, code = 4001, message = "sample")
public class SampleException extends RuntimeException {


    public SampleException() {
    }

    public SampleException(String message) {
        super(message);
    }

    public SampleException(String message, Throwable cause) {
        super(message, cause);
    }
}
