package io.github.utilityframework.boot.web.validator;

import io.github.utilityframework.boot.web.MappingInvokerAspect;
import org.springframework.context.ApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * Information about {@link Validator} is loaded.
 * These information will be used by required objects, for example, {@link MappingInvokerAspect}
 * needs to use this information to invoke function that validate parameters before
 * executing the controller method.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ValidatorLoader {

    private final ApplicationContext context;

    private final Map<Class<?>, Object> validators = new LinkedHashMap<>();

    public ValidatorLoader(ApplicationContext context) {
        this.context = context;
        initValidators(context);
    }

    public void initValidators(ApplicationContext context) {
        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(Validator.class);
        for (String beanName : beansWithAnnotation.keySet()) {
            Object validator = beansWithAnnotation.get(beanName);
            Class<?> validatorClass = validator.getClass();
            Validator validatorAnnotation = validatorClass.getAnnotation(Validator.class);
            this.validators.put(validatorAnnotation.value(), validator);
        }
    }

    public Map<Class<?>, Object> getValidators() {
        return validators;
    }

}

