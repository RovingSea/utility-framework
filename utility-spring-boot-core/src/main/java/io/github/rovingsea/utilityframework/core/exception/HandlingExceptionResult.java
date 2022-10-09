package io.github.rovingsea.utilityframework.core.exception;

import io.github.rovingsea.utilityframework.core.response.ControllerResponseAdvice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * It is used to distinguish between normal results and exception results in
 * {@link ControllerResponseAdvice}. If it is an exception result, return the
 * result according to other logic.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class HandlingExceptionResult implements Serializable {

    private final Map<String, Object> responseBody;

    public HandlingExceptionResult() {
        responseBody = new HashMap<>();
    }

    public HandlingExceptionResult(Map<String, Object> responseBody) {
        this.responseBody = responseBody;
    }

    public static HandlingExceptionResult init(Map<String, Object> responseBody) {
        return new HandlingExceptionResult(responseBody);
    }

    public Map<String, Object> getResponseBody() {
        return responseBody;
    }

    @Override
    public String toString() {
        return "HandlingExceptionResult{" +
                "responseBody=" + responseBody +
                '}';
    }
}

