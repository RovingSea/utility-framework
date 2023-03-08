package io.github.utilityframework.boot.web.response;

import com.alibaba.fastjson.JSON;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

/**
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
public class JsonResponse extends Response {

    public JsonResponse(boolean exceptionType) {
        super(exceptionType);
    }

    @Override
    protected Object write(ServerHttpRequest request, ServerHttpResponse response) {
        response.getHeaders().setAll(getHeader());
        response.setStatusCode(getHttpStatus());
        return JSON.toJSON(getBody());
    }
}
