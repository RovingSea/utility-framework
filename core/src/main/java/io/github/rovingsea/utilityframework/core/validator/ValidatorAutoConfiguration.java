package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Configuration
public class ValidatorAutoConfiguration {

    @Bean
    public ValidatorLoader validatorLoader(ApplicationContext context) {
        return new ValidatorLoader(context);
    }

    @Bean
    public MappingInvokerAspect mappingInterceptor(ApplicationContext context) {
        return new MappingInvokerAspect(context);
    }

}
