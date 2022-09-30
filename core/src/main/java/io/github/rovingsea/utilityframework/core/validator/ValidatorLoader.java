package io.github.rovingsea.utilityframework.core.validator;

import io.github.rovingsea.utilityframework.core.UtilityContextException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ValidatorLoader {

    private final ApplicationContext context;

    private Map<String, Object> validators;

    public ValidatorLoader(ApplicationContext context) {
        this.context = context;
    }

    @PostConstruct
    public void initialize() {
        loadValidators(this.context);
    }

    private void loadValidators(ApplicationContext context) {
        Map<String, Object> beansWithValidatorAnnotation
                = this.context.getBeansWithAnnotation(Validator.class);

        AbstractHandlerMethodMapping<?> handlerMethodMapping
                = this.context.getBean(AbstractHandlerMethodMapping.class);
        // todo set validators
        for (String beanName : beansWithValidatorAnnotation.keySet()) {
            Class<?> validatorClazz = beansWithValidatorAnnotation.get(beanName).getClass();
            Validator validator = validatorClazz.getAnnotation(Validator.class);

            String pathPrefix = validator.value();
            List<String> path = getPath(validatorClazz, pathPrefix);

            List<HandlerMethod> handlerMethodList
                    = handlerMethodMapping.getHandlerMethodsForMappingName(pathPrefix);

            if (CollectionUtils.isEmpty(handlerMethodList)) {
                throw new UtilityContextException("Invalid validating path, " +
                        "please check whether there is this path in Controllers");
            }

            Map<?, HandlerMethod> handlerMethodMap = handlerMethodMapping.getHandlerMethods();

        }
    }

    private List<String> getPath(Class<?> validatorClazz, String pathPrefix) {
        List<String> pathList = new ArrayList<>();
        for (Method method : validatorClazz.getMethods()) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            if (requestMapping != null) {
                pathList.add(pathPrefix + requestMapping.name());
            }
            GetMapping getMapping = method.getAnnotation(GetMapping.class);
            if (getMapping != null) {
                pathList.add(pathPrefix + getMapping.name());
            }
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            if (postMapping != null) {
                pathList.add(pathPrefix + postMapping.name());
            }
        }
        return pathList;
    }

}
