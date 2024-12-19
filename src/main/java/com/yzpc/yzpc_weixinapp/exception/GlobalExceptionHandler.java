package com.yzpc.yzpc_weixinapp.exception;

import com.yzpc.yzpc_weixinapp.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wq
 * @description 全局异常处理器
 * @date 2024/12/18 15:33:56
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result ex(Exception exception){
        exception.printStackTrace();
        return Result.error("出现异常");
    }
}
