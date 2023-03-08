package io.github.utilityframework.boot.web.response;

/**
 * The Exception to web response of <i>Spring Boot Utility</i>.
 *
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
public class WebResponseException extends RuntimeException {

    public WebResponseException(Throwable t) {
        super(t);
    }

    public WebResponseException(String message, Throwable t) {
        super(message, t);
    }

}
