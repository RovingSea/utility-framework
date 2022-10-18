package io.github.rovingsea.utilityframework.core.exception;

import io.github.rovingsea.utilityframework.core.response.ControllerResponseAdvice;

import java.io.Serializable;
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

    private final Map<String, String> responseHeader;

    public HandlingExceptionResult(Map<String, Object> responseBody, Map<String, String> responseHeader) {
        this.responseBody = responseBody;
        this.responseHeader = responseHeader;
    }

    public static HandlingExceptionResult init(Map<String, Object> responseBody,
                                               Map<String, String> responseHeader) {
        return new HandlingExceptionResult(responseBody, responseHeader);
    }

    public Map<String, Object> getResponseBody() {
        return responseBody;
    }

    public Map<String, String> getResponseHeader() {
        return responseHeader;
    }

    @Override
    public String toString() {
        return "HandlingExceptionResult{" +
                "responseBody=" + responseBody +
                '}';
    }
}

