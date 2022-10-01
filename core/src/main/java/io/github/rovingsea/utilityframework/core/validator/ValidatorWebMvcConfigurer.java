package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ValidatorWebMvcConfigurer implements WebMvcConfigurer {

    private final ApplicationContext context;

    private final ValidatorLoader validatorLoader;

    public ValidatorWebMvcConfigurer(ApplicationContext context) {
        this.context = context;
        this.validatorLoader = context.getBean(ValidatorLoader.class);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ValidatorHandlerInterceptor(context))
                .addPathPatterns(getAllValidatingPath());
    }

    private List<String> getAllValidatingPath() {
        return this.validatorLoader.getValidatingPaths();
    }
}

