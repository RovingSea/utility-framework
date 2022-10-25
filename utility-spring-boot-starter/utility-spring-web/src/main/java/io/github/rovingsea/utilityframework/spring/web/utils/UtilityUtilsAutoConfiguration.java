package io.github.rovingsea.utilityframework.spring.web.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Container of utility utils, it has been registered in spring.factories.
 *
 * @author Haixin Wu
 * @since 1.0.1
 */
@Configuration
public class UtilityUtilsAutoConfiguration {

    @Bean
    public SpringBeanUtils springBeanUtils(ApplicationContext context) {
        return new SpringBeanUtils(context);
    }

}
