package io.github.rovingsea.utilityframework.core;

import io.github.rovingsea.utilityframework.core.exception.BaseEnum;
import io.github.rovingsea.utilityframework.core.exception.ExceptionDispatcher;
import io.github.rovingsea.utilityframework.core.exception.UtilityException;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * Throw the exception information conveniently and concretely,
 * including the response code and the information in the response body.
 * </p>
 * <p>
 * As there are too many methods involved, here is an example to summarize:
 * {@code
 *  <pre>
 *     public static void badRequest(Enum<? extends BaseEnum> en) {
 *         BaseEnum baseEnum = Enum.valueOf(en.getDeclaringClass(), en.name());
 *         badRequest(baseEnum.getCode(), baseEnum.getMessage());
 *     }
 *  </pre>
 * }
 * The above code is obtained by passing the implementation class of {@link BaseEnum}
 * to obtain its {@link BaseEnum#getCode()} and {@link BaseEnum#getMessage()},
 * and then passed to the following code:
 * {@code
 *  <pre>
 *     public static void badRequest(int code, String message) {
 *         throw new UtilityException(code, message, HttpStatus.BAD_REQUEST);
 *     }
 *  </pre>
 * }
 * The above code will throw an exception containing {@link HttpStatus}
 * through {@link BaseEnum#getCode()} and {@link BaseEnum#getMessage()}.
 * </p>
 * <p>
 * Finally, the thrown exception will be handled in {@link ExceptionDispatcher}
 * </p>
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
