package io.github.utilityframework.boot.web.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Response {

    protected boolean exceptionType;

    protected Map<String, Object> body = new LinkedHashMap<>();

    protected Map<String, String> header = new LinkedHashMap<>();

    protected HttpStatus httpStatus;

    public Response(boolean exceptionType) {
        this.exceptionType = exceptionType;
    }

    protected abstract Object write(ServerHttpRequest request, ServerHttpResponse response);

    public boolean isExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(boolean exceptionType) {
        this.exceptionType = exceptionType;
    }

    public <A, B> void putDataOnBody(Fn<A, B> getFiledMethod, Object data) {
        this.body.put(Reflections.fnToFieldName(getFiledMethod), data);
    }

    public void putDataOnBody(String dataName, Object data) {
        this.body.put(dataName, data);
    }

    public void putDataOnHeader(String headerName, String data) {
        this.header.put(headerName, data);
    }

    public <A, B> void putDataOnHeader(Fn<A, B> getFiledMethod, Object data) {
        this.header.put(Reflections.fnToFieldName(getFiledMethod), (String) data);
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
