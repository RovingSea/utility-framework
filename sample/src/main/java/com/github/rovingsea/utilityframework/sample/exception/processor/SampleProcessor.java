package com.github.rovingsea.utilityframework.sample.exception.processor;

import com.github.rovingsea.utilityframework.core.error.ErrorPostProcessor;
import org.springframework.stereotype.Component;

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
