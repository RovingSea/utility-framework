package org.utilityframework.spring.boot.core.error.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.utilityframework.spring.boot.UtilityContextComponent;
import org.utilityframework.spring.boot.core.error.*;
import org.utilityframework.spring.boot.core.error.handler.DefaultGlobalErrorHandler;
import org.utilityframework.spring.boot.core.error.handler.SpringErrorHandler;

/**
 * Combined with <i>spring.factories</i> autowired some {@link UtilityContextComponent}
 * in utility into spring-IOC.
 *
 * @author Haixin Wu
 * @since 1.0
 */
@Configuration
public class UtilityErrorAutoConfiguration {

    @Bean
    public DefaultGlobalErrorHandler defaultGlobalErrorHandler() {
        return new DefaultGlobalErrorHandler();
    }

    @Bean
    public SpringErrorHandler springErrorHandler() {
        return new SpringErrorHandler();
    }

    @Bean
    public ErrorHandlerDispatcher errorHandlerDispatcher(ApplicationContext applicationContext) {
        return new ErrorHandlerDispatcher(applicationContext);
    }

    @Bean
    public ErrorHandlerManager errorHandlerManager(ApplicationContext applicationContext) {
        return new ErrorHandlerManager(applicationContext);
    }

    @Bean
    public ErrorHandlerSelector errorHandlerSelector(ApplicationContext applicationContext) {
        return new ErrorHandlerSelector(applicationContext);
    }

    @Bean
    public ErrorPostProcessorRegistry errorPostProcessorRegistry(ApplicationContext applicationContext) {
        return new ErrorPostProcessorRegistry(applicationContext);
    }

    @Bean
    public ErrorRegistry errorRegistry(ApplicationContext applicationContext) {
        return new ErrorRegistry(applicationContext);
    }

}

