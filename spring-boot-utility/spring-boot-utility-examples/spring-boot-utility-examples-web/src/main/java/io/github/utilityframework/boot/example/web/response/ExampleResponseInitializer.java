package io.github.utilityframework.boot.example.web.response;

import io.github.utilityframework.boot.web.exception.ExpectedException;
import io.github.utilityframework.boot.web.response.Response;
import io.github.utilityframework.boot.web.response.ResponseInitializer;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class ExampleResponseInitializer extends ResponseInitializer {
    @Override
    protected void initExpectedExceptionResponse(Response response, ExpectedException ee) {
        response.putDataOnBody(ExampleResponseBody::getCode, ee.getCode());
        response.putDataOnBody(ExampleResponseBody::getMessage, ee.getMessage());
        response.putDataOnBody(ExampleResponseBody::getDate, new Date());
        response.putDataOnHeader(ExampleResponseHeader::getProject, "example");
        response.setHttpStatus(ee.getHttpStatus());
    }

    @Override
    protected void initReturnResponse(Response response) {
        response.putDataOnBody(ExampleResponseBody::getCode, 200);
        response.putDataOnBody(ExampleResponseBody::getMessage, "success");
        response.putDataOnBody(ExampleResponseBody::getDate, new Date());
        response.putDataOnHeader(ExampleResponseHeader::getProject, "example");
    }
}
