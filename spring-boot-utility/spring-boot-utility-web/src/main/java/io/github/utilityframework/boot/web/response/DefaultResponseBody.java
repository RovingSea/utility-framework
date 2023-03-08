package io.github.utilityframework.boot.web.response;

/**
 * <p>
 * Default response body, if the unified response type is {@link JsonResponse}, its format is:<pre>
 * {@code
 *      {
 *          'code' : '',
 *          'message' : '',
 *          'data' : ''
 *      }
 * }
 * </pre>
 * </p>
 *
 * @author Haixin Wu
 * @version 1.0.0
 * @since 1.0.0
 */
public class DefaultResponseBody {

    private String code;

    private String message;

    private String data;

    public DefaultResponseBody() {
    }

    public DefaultResponseBody(String code, String message) {
        this.code = code;
        this.message = message;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
