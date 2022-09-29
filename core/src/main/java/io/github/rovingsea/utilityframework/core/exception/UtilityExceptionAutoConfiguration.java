package io.github.rovingsea.utilityframework.core.exception;

import io.github.rovingsea.utilityframework.core.exception.handler.SpringExceptionHandler;
import io.github.rovingsea.utilityframework.core.exception.handler.UnexpectedExceptionHandler;
import io.github.rovingsea.utilityframework.core.exception.handler.UtilityExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Configuration
public class UtilityExceptionAutoConfiguration {

    @Bean
    public ExceptionDispatcher exceptionDispatcher(ApplicationContext context) {
        return new ExceptionDispatcher(context);
    }

    @Bean
    public SpringExceptionHandler springExceptionHandler(ApplicationContext context) {
        return new SpringExceptionHandler(context);
    }

    @Bean
    public UnexpectedExceptionHandler unexpectedExceptionHandler(ApplicationContext context) {
        return new UnexpectedExceptionHandler(context);
    }

    @Bean
    public UtilityExceptionHandler utilityExceptionHandler(ApplicationContext context) {
        return new UtilityExceptionHandler(context);
    }

}
