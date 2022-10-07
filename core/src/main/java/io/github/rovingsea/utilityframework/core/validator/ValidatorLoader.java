package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ValidatorLoader {

    private final ApplicationContext context;

    private final Map<String, Object> validatorMap;

    private final List<String> validatingPaths;

    private final Map<String, Method> validatorMethodMap;

    public ValidatorLoader(ApplicationContext context) {
        this.context = context;
        this.validatorMap = new HashMap<>();
        this.validatingPaths = new ArrayList<>();
        this.validatorMethodMap = new ConcurrentHashMap<>();
    }

    @PostConstruct
    public void initialize() {
        loadValidatorMap(this.context);
        loadValidatingPaths();
    }

    private void loadValidatingPaths() {
        for (String beanName : this.validatorMap.keySet()) {
            Class<?> validatorClazz = this.validatorMap.get(beanName).getClass();
            Validator validator = validatorClazz.getAnnotation(Validator.class);
            String pathPrefix = validator.value();
            setValidatingPaths(validatorClazz, pathPrefix);
        }
    }

    private void setValidatingPaths(Class<?> validatorClazz, String pathPrefix) {
        for (Method method : validatorClazz.getMethods()) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping != null) {
                addPath(requestMapping.value(), pathPrefix);
                bindPathWithMethod(requestMapping.value(), pathPrefix, method);
            }
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            if (getMapping != null) {
                addPath(getMapping.value(), pathPrefix);
                bindPathWithMethod(getMapping.value(), pathPrefix, method);
            }
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            if (postMapping != null) {
                addPath(postMapping.value(), pathPrefix);
                bindPathWithMethod(postMapping.value(), pathPrefix, method);
            }
        }
    }

    private void addPath(String[] values, String pathPrefix) {
        if (values == null) {
            return;
        }
        for (String value : values) {
            this.validatingPaths.add(pathPrefix + value);
        }
    }

    private void bindPathWithMethod(String[] values, String pathPrefix, Method method) {
        if (values == null) {
            return;
        }
        for (String value : values) {
            this.validatorMethodMap.put(value + pathPrefix, method);
        }
    }

    private void loadValidatorMap(ApplicationContext context) {
        this.validatorMap.putAll(context.getBeansWithAnnotation(Validator.class));
    }

    public List<String> getValidatingPaths() {
        return this.validatingPaths;
    }

    public Map<String, Method> getValidatorMethodMap() {
        return validatorMethodMap;
    }

    public Map<String, Object> getValidatorMap() {
        return validatorMap;
    }

}
