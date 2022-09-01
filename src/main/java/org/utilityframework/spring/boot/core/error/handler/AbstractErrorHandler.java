package org.utilityframework.spring.boot.core.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.utilityframework.spring.boot.core.error.Error;
import org.utilityframework.spring.boot.core.response.Response;
import org.utilityframework.spring.boot.util.ErrorHandlerUtils;
import org.utilityframework.spring.boot.util.ErrorUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * It is the parent class of all annotated {@link ErrorHandler}.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public abstract class AbstractErrorHandler {


    /**
     * If you implement this method with the annotation {@link ErrorHandler},
     * it will replace the handling of {@link DefaultGlobalErrorHandler}.
     *
     * @param error An exception class with the annotation {@link Error}
     * @return The response body content
     */
    public abstract Response<String> handle(Throwable error);


    /**
     * Get the {@link HttpStatus} of the exception class with the annotation {@link Error}
     *
     * @param error An exception class with the annotation {@link Error}
     * @return {@link Error#status()}
     */
    protected HttpStatus getHttpStatus(Throwable error) {
        return ErrorUtils.getHttpStatus(error);
    }

    /**
     * set the {@link Error#status()}
     *
     * @param status An enum of {@link HttpStatus}
     */
    protected void setHttpStatus(HttpStatus status) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = null;
        if (attributes != null) {
            response = attributes.getResponse();
        }
        if (response == null) {
            throw new IllegalThreadStateException("The current thread cannot get a response object");
        }
        response.setStatus(status.value());
    }

    /**
     * Does it support handling this exception object ?
     *
     * @param error An exception class
     * @return support ?
     */
    public boolean supports(Throwable error) {
        List<? extends Throwable> errors = ErrorHandlerUtils.getErrors(this);
        for (Throwable e : errors) {
            Class<? extends Throwable> occurredErrorClass = error.getClass();
            Class<? extends Throwable> errorClassOnHandler = e.getClass();
            if (occurredErrorClass == errorClassOnHandler) {
                return true;
            }
        }
        return false;
    }

}
