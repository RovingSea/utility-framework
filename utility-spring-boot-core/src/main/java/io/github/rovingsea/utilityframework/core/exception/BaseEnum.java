package io.github.rovingsea.utilityframework.core.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Haixin Wu
 * @since 1.0.1
 */
public interface BaseEnum {

    default int getCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    };

    default String getMessage() {
        return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    }

}
