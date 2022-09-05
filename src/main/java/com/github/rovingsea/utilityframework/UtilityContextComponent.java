package com.github.rovingsea.utilityframework;

import com.github.rovingsea.utilityframework.core.error.ErrorHandlerDispatcher;
import com.github.rovingsea.utilityframework.core.error.ErrorHandlerManager;
import com.github.rovingsea.utilityframework.core.error.ErrorPostProcessorRegistry;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * It is the base class for all spring bean
 * objects that exist in the <i>utility</i> container.
 *
 * @author Haixin Wu
 * @since 1.0.0
 * @see ErrorHandlerDispatcher
 * @see ErrorHandlerManager
 * @see ErrorPostProcessorRegistry
 */
public abstract class UtilityContextComponent implements ApplicationContextAware,
        InitializeComponent {

    @Nullable
    private ApplicationContext applicationContext;

    public UtilityContextComponent(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        if (this.applicationContext == null) {
            throw new IllegalStateException("failed to init error-context");
        }
        return applicationContext;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContextTemp)
            throws BeansException {
        this.applicationContext = applicationContextTemp;
    }

}
