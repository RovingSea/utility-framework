package io.github.rovingsea.utilityframework.core;

import io.github.rovingsea.utilityframework.core.validator.ValidatorInvoker;
import io.github.rovingsea.utilityframework.core.validator.ValidatorLoader;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * A aspect method for all methods annotated with {@link RequestMapping},
 * {@link GetMapping}, {@link PostMapping}.
 * </p>
 * <p>
 * Complete parameter validation and exception capture in the {@link Around}.
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
            "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void intercept(JoinPoint jp) throws Throwable {
        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return;
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        if (this.validatorLoader.getValidatePaths().stream().noneMatch(requestURI::contains)) {
            return;
        }
        ValidatorInvoker validatorInvoker = validatorLoader.getValidatorInvoker(requestURI);
        Object[] args = jp.getArgs();
        try {
            validatorInvoker.invoke(args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw e.getCause();
        }
    }

}

