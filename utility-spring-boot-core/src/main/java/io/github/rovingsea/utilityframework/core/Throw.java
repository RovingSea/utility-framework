package io.github.rovingsea.utilityframework.core;

import io.github.rovingsea.utilityframework.core.exception.BaseEnum;
import io.github.rovingsea.utilityframework.core.exception.UtilityException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Haixin Wu
 * @since 1.0.1
 */
public abstract class Throw {

    public static void badRequest(Enum<? extends BaseEnum> en) {
        BaseEnum baseEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        badRequest(baseEnum.getCode(), baseEnum.getMessage());
    }

    public static void badRequest(int code, String message) {
        throw new UtilityException(code, message, HttpStatus.BAD_REQUEST);
    }

    public static void internalServerError(Enum<? extends BaseEnum> en) {
        BaseEnum baseEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
        internalServerError(baseEnum.getCode(), baseEnum.getMessage());
    }

    public static void internalServerError(int code, String message) {
        throw new UtilityException(code, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
