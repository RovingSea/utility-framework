package io.github.rovingsea.utilityframework.core.validator;

import java.lang.annotation.*;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateMapping {

    String[] value() default {};

}

