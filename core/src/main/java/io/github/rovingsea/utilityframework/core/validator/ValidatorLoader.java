package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ValidatorLoader {

    private final ApplicationContext context;

    private final Map<String, Object> validatorMap;

    private final List<String> validatePaths;

    private final Map<String, ValidatorInvoker> validatorMethodMap;

    public ValidatorLoader(ApplicationContext context) {
        this.context = context;
        this.validatorMap = new HashMap<>();
        this.validatePaths = new ArrayList<>();
        this.validatorMethodMap = new ConcurrentHashMap<>();
    }

    @PostConstruct
    public void initialize() {
        loadValidatorMap(this.context);
        loadValidatingPathsAndValidatorMethods();
    }

    private void loadValidatingPathsAndValidatorMethods() {
        for (String beanName : this.validatorMap.keySet()) {
            Object validator = this.validatorMap.get(beanName);
            Class<?> validatorClazz = validator.getClass();
            Validator annotation = validatorClazz.getAnnotation(Validator.class);
            String pathPrefix = annotation.value();
            addValidatingPathAndValidatorMethod(validator, pathPrefix);
        }
    }

    private void addValidatingPathAndValidatorMethod(Object validator, String pathPrefix) {
        for (Method method : validator.getClass().getMethods()) {
            ValidateMapping validateMapping = method.getAnnotation(ValidateMapping.class);
            if (validateMapping == null) {
                continue;
            }
            String[] values = validateMapping.value();
            if (values == null) {
                continue;
            }
            for (String value : values) {
                String path = pathPrefix + value;
                this.validatePaths.add(path);
                this.validatorMethodMap.put(path,
                        new ValidatorInvoker(validator, pathPrefix, value, method));
            }
        }
    }

    private void loadValidatorMap(ApplicationContext context) {
        this.validatorMap.putAll(context.getBeansWithAnnotation(Validator.class));
    }

    public List<String> getValidatePaths() {
        return this.validatePaths;
    }

    public Map<String, ValidatorInvoker> getValidatorMethodMap() {
        return this.validatorMethodMap;
    }

}
