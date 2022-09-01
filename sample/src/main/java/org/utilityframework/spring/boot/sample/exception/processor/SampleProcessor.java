package org.utilityframework.spring.boot.sample.exception.processor;

import org.springframework.stereotype.Component;
import org.utilityframework.spring.boot.core.error.ErrorPostProcessor;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Component
public class SampleProcessor implements ErrorPostProcessor {


    @Override
    public void processAfterOccurrence(Throwable error) {
        System.out.println("sample processor");
    }
}
