package io.github.rovingsea.utilityframework.core.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Haixin Wu
 * @since 1.0.1
 */
public abstract class HttpServletResponseUtils {

    public static HttpServletResponse instance() {
        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            throw new IllegalThreadStateException("The current thread cannot get a response object");
        }
        return servletRequestAttributes.getResponse();
    }

}
