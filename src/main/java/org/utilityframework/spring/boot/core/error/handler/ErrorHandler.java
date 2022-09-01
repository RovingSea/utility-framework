package org.utilityframework.spring.boot.core.error.handler;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * If this annotation is used and inherit {@link AbstractErrorHandler},
 * it will replace the handling of {@link DefaultGlobalErrorHandler}.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Inherited
@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public @interface ErrorHandler {

    /**
     * Which errors will be handled by it.
     */
    Class<? extends Throwable>[] value() default {};

}
