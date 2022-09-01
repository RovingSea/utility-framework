package org.utilityframework.spring.boot.core.error;

import org.springframework.context.ApplicationContext;
import org.utilityframework.spring.boot.UtilityContextComponent;
import org.utilityframework.spring.boot.exception.UsingErrorAnnotationException;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * <p>
 * All {@link Error} objects will be registered here.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ErrorRegistry extends UtilityContextComponent {
    /**
     * The map of all {@link Error}s
     */
    private Map<String, Object> errors;

    public ErrorRegistry(ApplicationContext applicationContext) {
        super(applicationContext);
    }


    @Override
    @PostConstruct
    public void initialize() {
        Map<String, Object> errorMap = getApplicationContext()
                .getBeansWithAnnotation(Error.class);
        for (String errorBeanName : errorMap.keySet()) {
            Object error = errorMap.get(errorBeanName);
            if (!(error instanceof Throwable)) {
                throw new UsingErrorAnnotationException(errorBeanName,
                        "@Error is not allowed to be added to " + errorBeanName + " class, " +
                                "because " + errorBeanName + "is not a subclass of Throwable");
            }
        }
        setErrors(errorMap);
    }

    /**
     * Does the registry have this error.
     *
     * @param error an exception
     * @return have ?
     */
    public boolean contains(Throwable error) {
        if (error == null) {
            return false;
        }
        Class<? extends Throwable> occurredErrorClass = error.getClass();
        for (String errorName : getErrors().keySet()) {
            Class<?> registeredErrorClass = getErrors().get(errorName).getClass();
            if (occurredErrorClass == registeredErrorClass) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Object> getErrors() {
        return this.errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }

}
