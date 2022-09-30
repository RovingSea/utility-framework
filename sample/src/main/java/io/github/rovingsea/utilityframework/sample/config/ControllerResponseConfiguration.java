package io.github.rovingsea.utilityframework.sample.config;

import io.github.rovingsea.utilityframework.core.response.ControllerExceptionResponse;
import io.github.rovingsea.utilityframework.core.response.ControllerReturnResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Configuration
public class ControllerResponseConfiguration {

    @Bean
    public ControllerExceptionResponse controllerExceptionResponse() {
        return new SampleControllerExceptionResponse();
    }

    @Bean
    public ControllerReturnResponse controllerResponseConfiguration() {
        return new SampleControllerReturnResponse();
    }

}
