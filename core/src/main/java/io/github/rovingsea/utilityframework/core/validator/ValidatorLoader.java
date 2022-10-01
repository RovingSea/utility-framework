package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        loadValidatingPaths(this.context);
        loadValidatorMethodMap(this.context);
    }

    //todo  Bind the validating path with the validating method
    private void loadValidatorMethodMap(ApplicationContext context) {
        for (String beanName : this.validatorMap.keySet()) {
            Class<?> validatorClazz = this.validatorMap.get(beanName).getClass();
            Validator validator = validatorClazz.getAnnotation(Validator.class);
            String pathPrefix = validator.value();
            for (String validatingPath : this.validatingPaths) {
                String methodValidPath = validatingPath.replaceFirst(pathPrefix, "");
                for (Method method : validatorClazz.getMethods()) {
//                    method.getAnnotation()
                }
            }
        }
    }

    private void loadValidatingPaths(ApplicationContext context) {
        for (String beanName : this.validatorMap.keySet()) {
            Class<?> validatorClazz = this.validatorMap.get(beanName).getClass();
            Validator validator = validatorClazz.getAnnotation(Validator.class);
            String pathPrefix = validator.value();
            setValidatingPaths(validatorClazz, pathPrefix);
        }
    }

    private void loadValidatorMap(ApplicationContext context) {
        this.validatorMap.putAll(context.getBeansWithAnnotation(Validator.class));
    }

    private void setValidatingPaths(Class<?> validatorClazz, String pathPrefix) {
        for (Method method : validatorClazz.getMethods()) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping != null) {
                addPath(requestMapping.value(), pathPrefix);
            }
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            if (getMapping != null) {
                addPath(getMapping.value(), pathPrefix);
            }
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            if (postMapping != null) {
                addPath(postMapping.value(), pathPrefix);
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

    public List<String> getValidatingPaths() {
        return validatingPaths;
    }


}
