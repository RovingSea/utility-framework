package io.github.utilityframework.boot.share.response;

import java.lang.annotation.*;

/**
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseBody {


    Class<?> type();


}
