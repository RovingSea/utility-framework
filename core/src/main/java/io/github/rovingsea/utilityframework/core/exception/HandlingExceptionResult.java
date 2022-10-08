package io.github.rovingsea.utilityframework.core.exception;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
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

