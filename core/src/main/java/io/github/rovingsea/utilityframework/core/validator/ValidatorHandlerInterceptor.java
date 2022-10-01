package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ValidatorHandlerInterceptor implements HandlerInterceptor {

    private final ApplicationContext context;

    public ValidatorHandlerInterceptor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?>[] parameterTypes = handlerMethod.getMethod().getParameterTypes();
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}

