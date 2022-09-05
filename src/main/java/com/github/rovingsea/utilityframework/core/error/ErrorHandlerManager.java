package com.github.rovingsea.utilityframework.core.error;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import com.github.rovingsea.utilityframework.UtilityContextComponent;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * <p>
 * Decide which <i>error handler</i> will handle the dispatched <i>error</i>,
 * and determine whether it needs additional handling.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ErrorHandlerManager extends UtilityContextComponent {

    /**
     * Used to confirm whether the <i>error</i> requires additional handling.
     */
    private ErrorPostProcessorRegistry errorPostProcessorRegistry;
    /**
     * Used to confirm which error handle will handle the <i>error</i>.
     */
    private ErrorHandlerSelector errorHandlerSelector;

    public ErrorHandlerManager(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    /**
     * Initializing {@link ErrorPostProcessorRegistry} and {@link ErrorHandlerSelector}
     */
    @Override
    @PostConstruct
    public void initialize() {
        initErrorHandlerRegistry(getApplicationContext());
        initErrorHandlerSelector(getApplicationContext());
    }

    /**
     * @param context Context coexisting with spring-application
     */
    public void initErrorHandlerRegistry(ApplicationContext context) {
        this.errorPostProcessorRegistry =
                BeanFactoryUtils.beanOfType(context, ErrorPostProcessorRegistry.class);
    }

    /**
     * @param context Context coexisting with spring-application
     */
    public void initErrorHandlerSelector(ApplicationContext context) {
        this.errorHandlerSelector =
                BeanFactoryUtils.beanOfType(context, ErrorHandlerSelector.class);
    }

    public ErrorHandlerSelector getErrorHandlerSelector(Throwable error) {
        additionallyProcess(error);
        this.errorHandlerSelector.setErrorHandler(error);
        return this.errorHandlerSelector;
    }

    private void additionallyProcess(Throwable error) {
        Map<String, ErrorPostProcessor> errorPostProcessorMap
                = this.errorPostProcessorRegistry.getErrorPostProcessors();
        for (String errorPostProcessorName : errorPostProcessorMap.keySet()) {
            ErrorPostProcessor errorPostProcessor = errorPostProcessorMap.get(errorPostProcessorName);
            errorPostProcessor.processAfterOccurrence(error);
        }
    }


}
