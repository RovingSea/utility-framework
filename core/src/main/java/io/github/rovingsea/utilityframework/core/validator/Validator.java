package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * <p>
 * A convenience annotation that is itself annotated with {@link Component},
 * it is used to validate parameter for mapping of the {@link Controller}.
 * </p>
 * <p>
 * Its usage is very similar to that of {@link Controller} and {@link RequestMapping}
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Validator {

    /**
     * Mapping prefix path to be validated.
     */
    String value() default "";

}
