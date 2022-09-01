package org.utilityframework.spring.boot.core.error;

import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.utilityframework.spring.boot.UtilityContextComponent;
import org.utilityframework.spring.boot.core.error.handler.AbstractErrorHandler;
import org.utilityframework.spring.boot.core.error.handler.DefaultGlobalErrorHandler;
import org.utilityframework.spring.boot.core.response.Response;

import java.util.Map;

/**
 * Used to confirm which error handle will handle the error.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ErrorHandlerSelector extends UtilityContextComponent {

    private AbstractErrorHandler abstractErrorHandler;

    public ErrorHandlerSelector(ApplicationContext applicationContext) {
        super(applicationContext);
    }

    public Response<String> handle(Throwable error) {
        return abstractErrorHandler.handle(error);
    }

    /**
     * First, judging whether the programmer has added a special <i>error handler</i> for processing.
     * If not, it will be handed over to {@link DefaultGlobalErrorHandler} for processing
     *
     * @param error An exception class with the annotation {@link Error}
     */
    public void setErrorHandler(Throwable error) {
        if (!hasErrorHandler(error)) {
            this.abstractErrorHandler
                    = getApplicationContext().getBean(DefaultGlobalErrorHandler.class);
        }
    }

    /**
     * Judging whether the programmer has added a special error handler for processing.
     *
     * @param error An exception class with the annotation {@link Error}
     * @return has ?
     */
    private boolean hasErrorHandler(Throwable error) {
        Map<String, AbstractErrorHandler> errorHandlerMap
                = getApplicationContext().getBeansOfType(AbstractErrorHandler.class);
        if (!CollectionUtils.isEmpty(errorHandlerMap) && errorHandlerMap.size() > 1) {
            for (String errorHandlerName : errorHandlerMap.keySet()) {
                AbstractErrorHandler errorHandler = errorHandlerMap.get(errorHandlerName);
                if (errorHandler.supports(error)) {
                    this.abstractErrorHandler = errorHandler;
                    return true;
                }
            }
        }
        return false;
    }

}
