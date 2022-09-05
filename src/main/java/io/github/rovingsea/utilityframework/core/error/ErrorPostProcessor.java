package io.github.rovingsea.utilityframework.core.error;

import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * <p>
 * After using, <i>error</i> will not only be used for response,
 * but also for additional handling.
 * </p>
 * <p>
 * Programmers can process it by judging whether it is an instance of a class.
 * </p>
 *
 * @author Haixin Wu
 * @see BeanPostProcessor
 * @since 1.0.0
 */
public interface ErrorPostProcessor {

    /**
     * AOP
     * @param error An exception class with the annotation {@link Error}
     */
    default void processAfterOccurrence(Throwable error) {

    }

}
