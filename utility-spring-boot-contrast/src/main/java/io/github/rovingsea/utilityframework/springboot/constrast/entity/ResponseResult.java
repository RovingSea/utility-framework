package io.github.rovingsea.utilityframework.springboot.constrast.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {

    private int code;

    private String message;

    private T data;

    public static <T> ResponseResult<T> success(T data) {
        return success(200, data);
    }

    public static <T> ResponseResult<T> success(int code, T data) {
        return new ResponseResult<>(code, "success", data);
    }

    public static <T> ResponseResult<T> fail(T data) {
        return fail(500, data);
    }

    public static <T> ResponseResult<T> fail(int code, T data) {
        return new ResponseResult<>(code, "fail", data);
    }

}

