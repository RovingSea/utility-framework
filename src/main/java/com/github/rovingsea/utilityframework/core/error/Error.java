package com.github.rovingsea.utilityframework.core.error;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.github.rovingsea.utilityframework.core.error.handler.SpringErrorHandler;

import java.lang.annotation.*;

/**
 * <p>
 * It can only be used in subclasses of {@link Throwable}. After using, it will become a
 * prototype bean for the spring framework, and will be handled by the error module in the
 * <b>Utility framework</b>.
 * </p>
 * <p>
 * When the exception decorated by <i>Error</i> is thrown, if it is not a special exception
 * object, such as the exception object handled by {@link SpringErrorHandler}, it will arrive
 * at {@link ErrorHandlerDispatcher} for unified processing. Therefore programmers do not need
 * to use a large number of <b>try/catch</b> statements to capture.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Inherited
@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public @interface Error {

    /**
     * It must follow {@link HttpStatus}, because it will be set to the request header
     */
    HttpStatus status() default HttpStatus.INTERNAL_SERVER_ERROR;

    /**
     * It can be customized by programmers, but it should be a detailed description of
     * {@link Error#status()} and it will be set to the request body <br>
     * For example, When {@link Error#status()} is set to {@link HttpStatus#BAD_REQUEST},
     * the code could be
     * <ul>
     *     <li>4001</li>
     *     <li>4002</li>
     *     <li>......</li>
     * </ul>
     */
    int code() default Integer.MIN_VALUE;

    /**
     * It can be customized by programmers, but it should be a detailed description of
     * {@link Error#status()} and it will be set to the request body <br>
     * For example, When {@link Error#status()} is set to {@link HttpStatus#BAD_REQUEST},
     * the message could be
     * <ul>
     *     <li>'username' cannot be empty</li>
     *     <li>'password' cannot be empty</li>
     *     <li>......</li>
     * </ul>
     */
    String message() default "";

}
