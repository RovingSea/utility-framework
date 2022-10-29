package io.github.rovingsea.utilityframework.spring.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * <p>
 * A convenience annotation that is itself annotated with {@link Component},
 * it is used to validate parameter for mapping of the {@link Controller}.
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

    Class<?> value();

}
