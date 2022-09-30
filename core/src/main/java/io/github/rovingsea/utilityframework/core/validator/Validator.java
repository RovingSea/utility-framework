package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Validator {

    String value() default "";

}
