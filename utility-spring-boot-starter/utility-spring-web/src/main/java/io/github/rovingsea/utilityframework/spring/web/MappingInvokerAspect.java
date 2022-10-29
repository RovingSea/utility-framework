package io.github.rovingsea.utilityframework.spring.web;

import io.github.rovingsea.utilityframework.spring.web.validator.ValidatorLoader;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>
 * A aspect method for all methods annotated with
 * <ul>
 *     <li>{@link RequestMapping}</li>
 *     <li>{@link GetMapping}</li>
 *     <li>{@link PostMapping}</li>
 *     <li>{@link DeleteMapping}</li>
 *     <li>{@link PutMapping}</li>
 *     <li>{@link PatchMapping}</li>
 * </ul>
 * </p>
 * <p>
 * Complete parameter validation and exception capture in the {@link Before}.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Aspect
public class MappingInvokerAspect {

    private final ValidatorLoader validatorLoader;

    public MappingInvokerAspect(ApplicationContext context) {
        this.validatorLoader = context.getBean(ValidatorLoader.class);
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping)  || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void intercept(JoinPoint jp) throws Throwable {
        Class<?> targetClass = jp.getTarget().getClass();
        Object validator = this.validatorLoader.getValidators().get(targetClass);
        if (validator == null) {
            return;
        }
        Method targetMethod = getTargetMethod(jp);
        Method validatorMethod = getValidatorMethod(validator, targetMethod);
        if (validatorMethod == null) {
            return;
        }
        Object[] args = jp.getArgs();
        invokeValidatorMethod(validatorMethod, validator, args);
    }

    private void invokeValidatorMethod(Method validatorMethod, Object validator, Object[] args)
            throws Throwable {
        try {
            validatorMethod.invoke(validator, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private Method getValidatorMethod(Object validator, Method targetMethod) {
        try {
            return validator.getClass().getDeclaredMethod(
                    targetMethod.getName(), targetMethod.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private Method getTargetMethod(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        return signature.getMethod();
    }

}

