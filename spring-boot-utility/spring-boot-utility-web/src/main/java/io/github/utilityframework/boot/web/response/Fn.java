package io.github.utilityframework.boot.web.response;

import java.io.Serializable;
import java.util.function.Function;

/**
 *
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Fn<T, R> extends Function<T, R>, Serializable {
}
