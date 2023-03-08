package io.github.utilityframework.boot.example.web.response;

import io.github.utilityframework.boot.share.response.Data;
import io.github.utilityframework.boot.share.response.ResponseBody;
import io.github.utilityframework.boot.web.response.JsonResponse;

import java.util.Date;

/**
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
@ResponseBody(type = JsonResponse.class)
public class ExampleResponseBody {

    private String code;

    private String message;

    private Date date;

    @Data
    private Object data;

    public ExampleResponseBody() {
    }

    public ExampleResponseBody(String code, String message, Date date, Object data) {
        this.code = code;
        this.message = message;
        this.date = date;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
