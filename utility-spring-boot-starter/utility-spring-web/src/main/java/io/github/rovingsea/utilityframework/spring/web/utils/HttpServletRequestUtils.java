package io.github.rovingsea.utilityframework.spring.web.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Haixin Wu
 * @since 1.0.1
 */
public abstract class HttpServletRequestUtils {

    public static HttpServletRequest instance() {
        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            throw new IllegalThreadStateException("The current thread cannot get a request object");
        }
        return servletRequestAttributes.getRequest();
    }

}
