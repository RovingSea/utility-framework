package io.github.rovingsea.utilityframework.spring.web;

import io.github.rovingsea.utilityframework.spring.web.validator.ValidatorInvoker;
import io.github.rovingsea.utilityframework.spring.web.validator.ValidatorLoader;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

    private final List<Class<? extends Annotation>> mappingList;

    public MappingInvokerAspect(ApplicationContext context) {
        this.validatorLoader = context.getBean(ValidatorLoader.class);
        mappingList = new ArrayList<>();
        mappingList.add(RequestMapping.class);
        mappingList.add(GetMapping.class);
        mappingList.add(PostMapping.class);
        mappingList.add(DeleteMapping.class);
        mappingList.add(PutMapping.class);
        mappingList.add(PatchMapping.class);
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
        String[] path = getMappingPath(jp);
        List<String> validatePaths = this.validatorLoader.getValidatePaths();
        if (path == null) {
            return;
        }
        for (String p : path) {
            if (validatePaths.contains(p)) {
                ValidatorInvoker validatorInvoker = validatorLoader.getValidatorInvoker(p);
                Object[] args = jp.getArgs();
                validatorInvoker.invoke(args);
                return;
            }
        }
    }

    private String[] getMappingPath(JoinPoint jp) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Annotation annotation = null;
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        for (Class<? extends Annotation> mappingAnnotationClass : this.mappingList) {
            annotation = method.getAnnotation(mappingAnnotationClass);
            if (annotation != null) {
                break;
            }
        }
        if (annotation == null) {
            return null;
        }
        Method value = annotation.annotationType().getDeclaredMethod("value");
        String[] invoke = (String[]) value.invoke(annotation);
        RequestMapping annotationOnClass = jp.getTarget().getClass()
                .getAnnotation(RequestMapping.class);
        String[] pathPrefixes = annotationOnClass.value();

        List<String> path = new ArrayList<>();
        for (String pathPrefix : pathPrefixes) {
            for (String s : invoke) {
                path.add(pathPrefix + s);
            }
        }
        return path.toArray(new String[0]);
    }

}

