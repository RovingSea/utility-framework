package io.github.utilityframework.boot.web.exception.handler;

import io.github.utilityframework.boot.web.exception.ExceptionEnum;
import io.github.utilityframework.boot.web.exception.ExpectedException;
import io.github.utilityframework.boot.web.response.Response;
import io.github.utilityframework.boot.web.response.ResponseInitializer;
import org.springframework.context.ApplicationContext;

/**
 * <p>
 * Specially handle {@link ExpectedException}, which will be handled in combination with
 * the {@link ResponseInitializer} provided to the programmer, and trigger the
 * {@link ExceptionEnum#postProcessAfterThrow(Object)}.
 * </p>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ExpectedExceptionHandler extends AbstractExceptionHandler {

    private ResponseInitializer responseInitializer;

    public ExpectedExceptionHandler(ApplicationContext context) {
        this.responseInitializer = context.getBean(ResponseInitializer.class);
    }

    @Override
    public void doHandle(Response response, Throwable throwable) {
        ExpectedException expectedException = (ExpectedException) throwable;
        expectedException.doProcess();
    }
}
