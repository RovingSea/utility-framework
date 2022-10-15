package io.github.rovingsea.utilityframework.springboot.constrast.exception.handler;

import io.github.rovingsea.utilityframework.springboot.constrast.entity.ResponseResult;
import io.github.rovingsea.utilityframework.springboot.constrast.exception.BusinessException;
import io.github.rovingsea.utilityframework.springboot.constrast.exception.ParamException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(BusinessException.class)
    public ResponseResult<String> handleBizException(BusinessException businessException) {
        return ResponseResult.fail(businessException.getCode(), businessException.getMessage());
    }

    @ResponseBody
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(ParamException.class)
    public ResponseResult<String> handleParamException(ParamException paramException) {
        return ResponseResult.fail(paramException.getCode(), paramException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler
    public ResponseResult<String> handleBug(Throwable bug) {
        bug.printStackTrace();
        return ResponseResult.fail(bug.getMessage());
    }

}

