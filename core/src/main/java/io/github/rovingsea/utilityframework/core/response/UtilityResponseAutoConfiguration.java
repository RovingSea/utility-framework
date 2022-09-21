package io.github.rovingsea.utilityframework.core.response;

import io.github.rovingsea.utilityframework.core.UtilityContextException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Container of utility response, it has been registered in spring.factories.
 * @author Haixin Wu
 * @since 1.0.0
 */
@Configuration
public class UtilityResponseAutoConfiguration {

    @Bean
    public ControllerResponseAdvice controllerResponseAdvice(ConfigurableApplicationContext context) {
        ResponseConfiguration responseConfiguration = context.getBean(ResponseConfiguration.class);
        if (responseConfiguration == null) {
            throw new UtilityContextException("Response configuration needs to be set");
        }
        return new ControllerResponseAdvice(responseConfiguration);
    }
}

