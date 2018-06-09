package com.ellen.seckill.util;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.exception.UserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if(e instanceof UserException) {
            UserException userException = (UserException) e;
            return ResultUtil.userException(userException);
        } else {
            // TODO system logger needed
            return ResultUtil.unknownException();
        }
    }
}
