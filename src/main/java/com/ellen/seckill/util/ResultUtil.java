package com.ellen.seckill.util;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.enums.UserStateEnum;
import com.ellen.seckill.exception.UserException;

/**
 * encapsulate a result
 */
public class ResultUtil {

    public static Result userSuccess(Object object) {
        Result result = new Result();
        result.setCode(UserStateEnum.SUCCESS.getCode());
        result.setMsg(UserStateEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    public static Result userException(UserException userException) {
        Result result = new Result();
        result.setCode(userException.getCode());
        result.setMsg(userException.getMessage());
        return result;
    }

    public static Result unknownException() {
        Result result = new Result();
        result.setCode(-1);
        result.setMsg("UNKNOWN_SYSTEM_ERROR");
        return result;
    }
}
