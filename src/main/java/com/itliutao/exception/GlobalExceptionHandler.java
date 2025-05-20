package com.itliutao.exception;

import com.itliutao.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handlerException(Exception e) {
        log.error("服务器异常:{}",e);
        return Result.error("服务器异常");
    }
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库异常:{}",e);
        String msg = e.getMessage();
        int duplicateEntry = msg.indexOf("Duplicate entry");
        String []arr = msg.substring(duplicateEntry).split(" ");
        return Result.error("用户名"+arr[2]+"已存在");
    }
}
