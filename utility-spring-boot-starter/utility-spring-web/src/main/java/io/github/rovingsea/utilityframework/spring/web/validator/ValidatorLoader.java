package io.github.rovingsea.utilityframework.spring.web.validator;

import io.github.rovingsea.utilityframework.spring.web.MappingInvokerAspect;
import io.github.rovingsea.utilityframework.spring.web.UtilityContextException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * Information about {@link Validator} is loaded, such as path, object, and invoker.
 * These information will  e used by required objects, for example, {@link MappingInvokerAspect}
 * needs to use this information to invoke function that validate parameters before
 * executing the controller method.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ValidatorLoader {

    private final ApplicationContext context;

    /**
     * Each {@link Validator}.
     */
    private final Map<String, Object> validatorMap;
    /**
     * Path requiring parameter verification.
     */
    private final List<String> validatePaths;
    /**
     * {@link ValidatorInvoker} corresponding to each path.
     */
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

    public ValidatorInvoker getValidatorInvoker(String requestURI) {
        String uri = this.validatePaths.stream().filter(requestURI::equals).findAny().orElse(null);
        if (StringUtils.isEmpty(uri)) {
            throw new UtilityContextException("The verification " +
                    "path does not match a verification method");
        }
        return this.validatorMethodMap.get(uri);
    }

}
