package io.github.rovingsea.utilityframework.core.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ValidatorInvoker {

    private final Object validator;

    private final String pathPrefix;

    private final String mappingPath;

    private final Method method;

    public ValidatorInvoker(Object validator, String pathPrefix, String mappingPath, Method method) {
        this.validator = validator;
        this.pathPrefix = pathPrefix;
        this.mappingPath = mappingPath;
        this.method = method;
    }

    public Object invoke(Object... args) throws InvocationTargetException, IllegalAccessException {
        return this.method.invoke(getValidator(), args);
    }

    public Object getValidator() {
        return validator;
    }

    public String getPathPrefix() {
        return pathPrefix;
    }

    public String getMappingPath() {
        return mappingPath;
    }

    public Method getMethod() {
        return method;
    }
}

