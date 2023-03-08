package io.github.utilityframework.boot.web.response;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Constructor;

/**
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResponseFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static Response createExceptionResponse(Throwable t) {
        ResponseProperties properties = getProperties();
        Object type = properties.getType();
        try {
            Class<?> responseTypeImpl = Class.forName(type.toString());
            Constructor<?> constructor = responseTypeImpl.getConstructor(boolean.class);
            Response response = (Response) constructor.newInstance(true);
            getResponseInitializer().initResponse(response, properties, t);
            return response;
        } catch (Throwable e) {
            throw new WebResponseException(String.format(
                    "Unable to create %s type of exception response.", type), e);
        }
    }

    public static Response createReturnResponse() {
        ResponseProperties properties = getProperties();
        Object type = properties.getType();
        try {
            Class<?> responseTypeImpl = Class.forName(type.toString());
            Constructor<?> constructor = responseTypeImpl.getConstructor(boolean.class);
            Response response = (Response) constructor.newInstance(false);
            getResponseInitializer().initResponse(response, properties);
            return response;
        } catch (Throwable e) {
            throw new WebResponseException(String.format(
                    "Unable to create %s type of return response.", type), e);
        }
    }

    private static ResponseProperties getProperties() {
        return applicationContext.getBean(ResponseProperties.class);
    }

    private static ResponseInitializer getResponseInitializer() {
        return applicationContext.getBean(ResponseInitializer.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ResponseFactory.applicationContext = applicationContext;
    }
}
