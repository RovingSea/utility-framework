package io.github.utilityframework.boot.web.response;

import io.github.utilityframework.boot.web.exception.ExpectedException;
import io.github.utilityframework.boot.web.exception.handler.SpringExceptionHandler;
import io.github.utilityframework.boot.web.exception.handler.UnexpectedExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;

/**
 * <p>
 * Initializing the {@link Response} created by the {@link ResponseFactory}.
 * </p>
 * <p>
 * It can be inherited to implement two abstract methods,
 * {@link #initExpectedExceptionResponse(Response, ExpectedException)}
 * and {@link #initReturnResponse(Response)} to complete the content of the customized response.
 * </p>
 *
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class ResponseInitializer {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void initResponse(Response response, ResponseProperties properties) {
        initResponse(response, properties, null);
    }

    public void initResponse(Response response, ResponseProperties properties, Throwable t) {
        prepareBodyValues(response, properties);
        prepareHeaderValues(response, properties);
        prepareHttpStatus(response);
        if (t == null) {
            initReturnResponse(response);
        } else {
            if (t instanceof ExpectedException) {
                initExpectedExceptionResponse(response, (ExpectedException) t);
            } else {
                initUnExpectedExceptionResponse(response, t);
            }
        }
    }

    /**
     * <p>
     * For the initialization of unexpected exceptions, we do not do any processing here,
     * but move to {@link SpringExceptionHandler} and {@link UnexpectedExceptionHandler}
     * for special processing.
     * </p>
     * <p>
     * The reason why this method is specifically stated here is to remind engineers watching
     * the source code that we will handle unexpected exceptions
     * </p>
     *
     * @param response response
     * @param t        unexpected exception
     */
    protected void initUnExpectedExceptionResponse(Response response, Throwable t) {
        logger.debug("An unexpected exception was found during initialization response.");
    }

    protected void prepareBodyValues(Response response, ResponseProperties properties) {
        for (Field bodyExtraField : getBodyFields(properties)) {
            response.putDataOnBody(bodyExtraField.getName(), null);
        }
    }

    protected void prepareHeaderValues(Response response, ResponseProperties properties) {
        for (Field headerExtraField : getHeaderFields(properties)) {
            response.putDataOnHeader(headerExtraField.getName(), null);
        }
    }

    protected void prepareHttpStatus(Response response) {
        if (response.isExceptionType()) {
            response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response.setHttpStatus(HttpStatus.OK);
        }
    }

    protected Field[] getBodyFields(ResponseProperties properties) {
        String bodyClassName = properties.getBodyClassName();
        try {
            Class<?> bodyImpl = Class.forName(bodyClassName);
            return bodyImpl.getDeclaredFields();
        } catch (Throwable e) {
            throw new WebResponseException(String.format("Unable to get fields of %s.", bodyClassName), e);
        }
    }

    protected Field[] getHeaderFields(ResponseProperties properties) {
        String headerClassName = properties.getHeaderClassName();
        try {
            Class<?> headerImpl = Class.forName(headerClassName);
            return headerImpl.getDeclaredFields();
        } catch (Throwable e) {
            throw new WebResponseException(String.format("Unable to get fields of %s.", headerClassName), e);
        }
    }

    /**
     * For the initialization of expected exceptions, we provide the default processing object
     * {@link DefaultResponseInitializer}, and support engineers to
     * inherit {@link ResponseInitializer} to override for customization.
     *
     * @param response response
     * @param ee       expected exception
     */
    protected abstract void initExpectedExceptionResponse(Response response, ExpectedException ee);


    /**
     * For the initialization of return result, we provide the default processing object
     * {@link DefaultResponseInitializer}, and support engineers to
     * inherit {@link ResponseInitializer} to override for customization.
     *
     * @param response response
     */
    protected abstract void initReturnResponse(Response response);

}
