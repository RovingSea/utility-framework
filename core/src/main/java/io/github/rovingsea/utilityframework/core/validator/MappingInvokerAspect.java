package io.github.rovingsea.utilityframework.core.validator;

import io.github.rovingsea.utilityframework.core.exception.ExceptionDispatcher;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

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

    private final ExceptionDispatcher exceptionDispatcher;

    public MappingInvokerAspect(ApplicationContext context) {
        this.validatorLoader = context.getBean(ValidatorLoader.class);
        this.exceptionDispatcher = context.getBean(ExceptionDispatcher.class);
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object intercept(ProceedingJoinPoint pjp) {
        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Object result;
        try {
            if (servletRequestAttributes == null) {
                return null;
            }
            HttpServletRequest request = servletRequestAttributes.getRequest();
            String requestURI = request.getRequestURI();
            if (!this.validatorLoader.getValidatePaths().contains(requestURI)) {
                result = pjp.proceed();
                return result;
            }
            Map<String, ValidatorInvoker> validatorMethods
                    = this.validatorLoader.getValidatorMethodMap();
            ValidatorInvoker validatorInvoker = validatorMethods.get(requestURI);
            Object[] args = pjp.getArgs();
            try {
                validatorInvoker.invoke(args);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw e.getCause();
            }
            result = pjp.proceed();
        } catch (Throwable e) {
            result = exceptionDispatcher.doDispatch(e);
        }
        return result;
    }

}

