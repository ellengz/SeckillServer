package com.ellen.seckill.util;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.exception.SeckillException;
import com.ellen.seckill.exception.UserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create a result when an exception is thrown
 */
@ControllerAdvice
public class ExceptionUtil {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if(e instanceof UserException) {
            UserException userException = (UserException) e;
            return ResultUtil.userException(userException);
        } else if(e instanceof SeckillException) {
            SeckillException seckillException = (SeckillException) e;
            return ResultUtil.seckillException(seckillException);
        } else {
            // TODO system logger needed
            return ResultUtil.unknownException();
        }
    }
}
