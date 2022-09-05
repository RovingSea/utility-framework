package com.github.rovingsea.utilityframework.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * Current unified response object.
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<E> implements Serializable {


    /**
     * Developer defined response code, it should not be HTTP-Code,
     * because HTTP-Code will be set in the request header in this framework,
     * and it should be applied to more detailed scenarios.
     */
    private int code;

    /**
     * Its usage is consistent with code, but it is a description of code.
     */
    private String message;

    /**
     * Data content
     */
    private E data;

    public static <F> Response<F> failure() {
        return failure(null);
    }

    public static <F> Response<F> failure(F data) {
        return failure(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), data);
    }

    public static <F> Response<F> failure(String message, F data) {
        return failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, data);
    }

    public static <F> Response<F> failure(int code, String message, F data) {
        return new Response<>(code, message, data);
    }

    public static <F> Response<F> failure(HttpStatus httpStatus, F data) {
        return new Response<>(httpStatus.value(), httpStatus.getReasonPhrase(), data);
    }

    public static <F> Response<F> success() {
        return success(null);
    }

    public static <F> Response<F> success(F data) {
        return success(HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <F> Response<F> success(String message, F data) {
        return success(HttpStatus.OK.value(), message, data);
    }

    public static <F> Response<F> success(int code, String message, F data) {
        return new Response<>(code, message, data);
    }

    public static <F> Response<F> success(HttpStatus httpStatus, F data) {
        return new Response<>(httpStatus.value(), httpStatus.getReasonPhrase(), data);
    }

}