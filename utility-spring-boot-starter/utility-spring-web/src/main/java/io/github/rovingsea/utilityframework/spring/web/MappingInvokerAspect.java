package io.github.rovingsea.utilityframework.spring.web;

import io.github.rovingsea.utilityframework.spring.web.utils.HttpServletRequestUtils;
import io.github.rovingsea.utilityframework.spring.web.validator.ValidatorInvoker;
import io.github.rovingsea.utilityframework.spring.web.validator.ValidatorLoader;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping)  || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void intercept(JoinPoint jp) throws Throwable {
        HttpServletRequest request = HttpServletRequestUtils.instance();
        String requestURI = request.getRequestURI();
        if (this.validatorLoader.getValidatePaths().stream().noneMatch(requestURI::contains)) {
            return;
        }
        ValidatorInvoker validatorInvoker = validatorLoader.getValidatorInvoker(requestURI);
        Object[] args = jp.getArgs();
        validatorInvoker.invoke(args);
    }

}

