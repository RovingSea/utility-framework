package io.github.rovingsea.utilityframework.spring.web.response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Container of utility response, it has been registered in spring.factories.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Configuration
public class UtilityResponseAutoConfiguration {

    @Bean
    public ControllerResponseAdvice controllerResponseAdvice(ApplicationContext context) {
        return new ControllerResponseAdvice(context);
    }
}

