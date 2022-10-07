package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ValidatorHandlerInterceptor implements HandlerInterceptor {

    private final ApplicationContext context;

    private final ValidatorLoader validatorLoader;

    public ValidatorHandlerInterceptor(ApplicationContext context) {
        this.context = context;
        this.validatorLoader = context.getBean(ValidatorLoader.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (!this.validatorLoader.getValidatingPaths().contains(requestURI)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?>[] parameterTypes = handlerMethod.getMethod().getParameterTypes();
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        Object[] args = new Object[methodParameters.length];
        Method method = validatorLoader.getValidatorMethodMap().get(requestURI);
        Object o = validatorLoader.getValidatorMap().get(requestURI);
        // todo resolve the MethodParameter
        // args = ?
        method.invoke(o, args);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}

