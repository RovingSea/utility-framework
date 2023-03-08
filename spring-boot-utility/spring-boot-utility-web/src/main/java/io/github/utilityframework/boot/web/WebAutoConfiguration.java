package io.github.utilityframework.boot.web;

import io.github.utilityframework.boot.web.exception.ExceptionDispatcher;
import io.github.utilityframework.boot.web.exception.handler.ExpectedExceptionHandler;
import io.github.utilityframework.boot.web.exception.handler.SpringExceptionHandler;
import io.github.utilityframework.boot.web.exception.handler.UnexpectedExceptionHandler;
import io.github.utilityframework.boot.web.response.*;
import io.github.utilityframework.boot.web.utils.SpringBeanUtils;
import io.github.utilityframework.boot.web.validator.ValidatorLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(ResponseProperties.class)
public class WebAutoConfiguration {

    // Exception

    @Bean
    public ExceptionDispatcher exceptionDispatcher(ApplicationContext context) {
        return new ExceptionDispatcher(context);
    }

    @Bean
    @ConditionalOnMissingBean(SpringExceptionHandler.class)
    public SpringExceptionHandler springExceptionHandler(ApplicationContext context) {
        return new SpringExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean(UnexpectedExceptionHandler.class)
    public UnexpectedExceptionHandler unexpectedExceptionHandler(ApplicationContext context) {
        return new UnexpectedExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean(ExpectedExceptionHandler.class)
    public ExpectedExceptionHandler expectedExceptionHandler(ApplicationContext context) {
        return new ExpectedExceptionHandler(context);
    }

    // Response

    @Bean
    public StringResponseBodyAdvice stringResponseBodyAdvice(ApplicationContext context) {
        return new StringResponseBodyAdvice(context);
    }

    @Bean
    public JsonResponseBodyAdvice jsonResponseBodyAdvice(ApplicationContext context) {
        return new JsonResponseBodyAdvice(context);
    }

    @Bean
    @ConditionalOnMissingBean(ResponseInitializer.class)
    public ResponseInitializer defaultResponseInitialized() {
        return new DefaultResponseInitializer();
    }

    @Bean
    @ConditionalOnBean(ResponseInitializer.class)
    public ResponseFactory responseFactory() {
        return new ResponseFactory();
    }


    // Utils

    @Bean
    public SpringBeanUtils springBeanUtils(ApplicationContext context) {
        return new SpringBeanUtils(context);
    }

    // Validator

    @Bean
    public ValidatorLoader validatorLoader(ApplicationContext context) {
        return new ValidatorLoader(context);
    }

    @Bean
    public MappingInvokerAspect mappingInterceptor(ApplicationContext context) {
        return new MappingInvokerAspect(context);
    }

}
