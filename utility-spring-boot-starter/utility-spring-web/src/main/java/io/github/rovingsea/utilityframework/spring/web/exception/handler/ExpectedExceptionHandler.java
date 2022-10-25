package io.github.rovingsea.utilityframework.spring.web.exception.handler;

import io.github.rovingsea.utilityframework.spring.web.exception.ExpectedException;
import io.github.rovingsea.utilityframework.spring.web.response.ControllerExceptionResponse;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Specially handle {@link ExpectedException}, which will be handled in combination with
 * the {@link ControllerExceptionResponse} provided to the programmer.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class ExpectedExceptionHandler extends AbstractExceptionHandler {

    public ExpectedExceptionHandler(ApplicationContext context) {
        super(context.getBean(ControllerExceptionResponse.class));
    }

    @Override
    public void doHandle(Map<String, Object> responseBody, Map<String, String> responseHeader,
                         HttpServletRequest request, HttpServletResponse response,
                         Throwable throwable) {
        ExpectedException expectedException = (ExpectedException) throwable;
        expectedException.doProcess();
        this.controllerExceptionResponse.setResponseBody(responseBody, expectedException, request, response);
        this.controllerExceptionResponse.setResponseHeader(responseHeader, expectedException, request, response);
    }
}
