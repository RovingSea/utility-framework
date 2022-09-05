package com.github.rovingsea.utilityframework.exception;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class UsingErrorAnnotationException extends RuntimeException {

    private String beanName;

    public UsingErrorAnnotationException(String message) {
        super(message);
    }

    public UsingErrorAnnotationException(String beanName, String message) {
        super(message);
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
