package io.github.rovingsea.utilityframework.core.response;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 * Configuration interface is needs to be implemented, it provides the operation
 * of setting the request header and request body after the {@link Controller}
 * returns the result.
 * </p>
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface ResponseConfiguration {

    default void setResponseBody(Map<String, Object> responseBody) {

    }

    default void setResponseHeader(Map<String, String> responseHeader) {

    }

}

