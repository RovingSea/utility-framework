package io.github.rovingsea.utilityframework.core.exception.http;

import io.github.rovingsea.utilityframework.core.exception.UtilityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends UtilityException {

    public BadRequestException(int code, String message) {
        super(code, message);
    }

}

