package com.ellen.seckill.util;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.exception.BaseException;
import com.ellen.seckill.exception.SeckillException;
import com.ellen.seckill.exception.UserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create a result when an exception is thrown
 */
@ControllerAdvice
public class ExceptionUtil {

    Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if(e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return ResultUtil.exception(baseException);
        } else {
            logger.error("[SYSTEM_ERROR]", e);
            return ResultUtil.unknownException();
        }
    }
}
