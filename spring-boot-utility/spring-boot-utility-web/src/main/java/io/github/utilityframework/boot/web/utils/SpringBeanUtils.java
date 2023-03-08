package io.github.utilityframework.boot.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class SpringBeanUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContextTemp;

    public SpringBeanUtils(ApplicationContext context) {
        setApplicationContext(context);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        applicationContextTemp = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContextTemp;
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
