package org.utilityframework.spring.boot.core.error;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.utilityframework.spring.boot.UtilityContextComponent;
import org.utilityframework.spring.boot.core.error.handler.SpringErrorHandler;
import org.utilityframework.spring.boot.core.response.Response;

import javax.annotation.PostConstruct;

/**
 * <p>
 * If this exception object is not special, such as the exception object
 * handled by {@link SpringErrorHandler}. Then, these exception objects
 * with {@link Error} will be uniformly entered into this class to be dispatched.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestControllerAdvice
public class ErrorHandlerDispatcher extends UtilityContextComponent {

    private final Log logger = LogFactory.getLog(getClass());

    /**
     * Used to determine whether the thrown exception is an <i>error</i>
     */
    private ErrorRegistry errorRegistry;

    /**
     * Used to determine which <i>error handler</i> will handle <i>error</i>
     */
    private ErrorHandlerManager errorHandlerManager;

    public ErrorHandlerDispatcher(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    /**
     * Initializing {@link #errorRegistry} and {@link ErrorHandlerManager}
     */
    @Override
    @PostConstruct
    public void initialize() {
        initErrorBeanRegistry(getApplicationContext());
        initErrorHandlerManager(getApplicationContext());
    }

    /**
     * @param context Context coexisting with spring-application
     */
    private void initErrorHandlerManager(ApplicationContext context) {
        this.errorHandlerManager = BeanFactoryUtils.beanOfType(context, ErrorHandlerManager.class);
    }

    /**
     * @param context Context coexisting with spring-application
     */
    private void initErrorBeanRegistry(ApplicationContext context) {
        this.errorRegistry = BeanFactoryUtils.beanOfType(context, ErrorRegistry.class);
    }

    /**
     * These exception objects with {@link Error} will
     * be uniformly entered into this method to be dispatched.
     *
     * @param error An exception class with the annotation {@link Error}
     * @return The Response body content
     */
    @ExceptionHandler
    public Response<String> doHandle(Throwable error) {
        if (!this.errorRegistry.contains(error)) {
            logger.warn("A non error that named " +
                    error.getClass().getSimpleName() + " was accepted");
            return Response.failure();
        }
        ErrorHandlerSelector errorHandlerSelector
                = this.errorHandlerManager.getErrorHandlerSelector(error);
        return errorHandlerSelector.handle(error);
    }

}
