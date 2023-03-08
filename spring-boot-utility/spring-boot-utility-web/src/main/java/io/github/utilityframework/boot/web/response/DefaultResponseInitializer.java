package io.github.utilityframework.boot.web.response;

import io.github.utilityframework.boot.web.exception.ExpectedException;

/**
 * <p>
 * Default subclass of {@link ResponseInitializer}, When another subclass exists
 * in the spring container, it becomes invalid.
 * </p>
 *
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultResponseInitializer extends ResponseInitializer {

    @Override
    protected void initExpectedExceptionResponse(Response response, ExpectedException ee) {
        response.putDataOnBody(DefaultResponseBody::getCode, ee.getCode());
        response.putDataOnBody(DefaultResponseBody::getMessage, ee.getMessage());
//        response.putDataOnHeader(DefaultResponseHeaderExtra::nothing, nothing);
    }

    @Override
    protected void initReturnResponse(Response response) {
        response.putDataOnBody(DefaultResponseBody::getCode, 200);
        response.putDataOnBody(DefaultResponseBody::getMessage, "success");
//        response.putDataOnHeader(DefaultResponseHeaderExtra::nothing, nothing);
    }
}
