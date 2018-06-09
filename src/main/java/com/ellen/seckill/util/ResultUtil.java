package com.ellen.seckill.util;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.enums.UserStateEnum;
import com.ellen.seckill.exception.UserException;

/**
 * encapsulate a result
 */
public class ResultUtil {

    /**
     * return a result with received object to front end when succeed
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
     * return exception info to front end
     * called by exception handler (not service)
     * @param userException
     * @return result
     */
    public static Result userException(UserException userException) {
        Result result = new Result();
        result.setCode(userException.getCode());
        result.setMsg(userException.getMessage());
        return result;
    }

    /**
     * return info to let front end know the error is unidentified
     * @return result
     */
    public static Result unknownException() {
        Result result = new Result();
        result.setCode(-1);
        result.setMsg("UNKNOWN_SYSTEM_ERROR");
        return result;
    }
}
