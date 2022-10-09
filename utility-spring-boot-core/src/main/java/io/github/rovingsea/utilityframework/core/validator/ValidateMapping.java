package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * <p>
 * Used in combination with {@link Validator}, it is only used on methods.
 * It will be combined with {@link Validator#value()} to form a path that
 * needs to be validated.
 * </p>
 * <p>
 * Its usage is very similar to that of {@link Controller} and {@link RequestMapping}
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateMapping {

    String[] value() default {};

}

