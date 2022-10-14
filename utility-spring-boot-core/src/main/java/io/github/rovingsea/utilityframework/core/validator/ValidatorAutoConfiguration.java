package io.github.rovingsea.utilityframework.core.validator;

import io.github.rovingsea.utilityframework.core.MappingInvokerAspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Container of utility validator, it has been registered in spring.factories.
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
