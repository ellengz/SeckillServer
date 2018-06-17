package com.ellen.seckill.util;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.enums.SeckillStateEnum;
import com.ellen.seckill.enums.UserStateEnum;
import com.ellen.seckill.exception.BaseException;
import com.ellen.seckill.exception.SeckillException;
import com.ellen.seckill.exception.UserException;

/**
 * encapsulate a result
 */
public class ResultUtil {

    /**
     * return a result with received object to front end when succeed
     *
     * @param object
     * @return result
     */
    public static Result userSuccess(Object object) {
        Result result = new Result();
        result.setCode(UserStateEnum.SUCCESS.getCode());
        result.setMsg(UserStateEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * when no return content is needed
     */
    public static Result userSuccess() {
        return userSuccess(null);
    }

    /**
     * return a result with received object to front end when succeed
     *
     * @param object
     * @return result
     */
    public static Result seckillSuccess(Object object) {
        Result result = new Result();
        result.setCode(SeckillStateEnum.SUCCESS.getCode());
        result.setMsg(SeckillStateEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * return an exception
     *
     * @param e
     * @return
     */
    public static Result exception(BaseException e) {
        Result result = new Result();
        result.setCode(e.getCode());
        result.setMsg(e.getMessage());
        return result;
    }

    /**
     * return info to let front end know the error is unidentified
     *
     * @return result
     */
    public static Result unknownException() {
        Result result = new Result();
        result.setCode(-1);
        result.setMsg("UNKNOWN_SYSTEM_ERROR");
        return result;
    }
}
