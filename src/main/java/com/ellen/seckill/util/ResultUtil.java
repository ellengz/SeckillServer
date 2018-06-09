package com.ellen.seckill.util;

import com.ellen.seckill.domain.Result;
import com.ellen.seckill.enums.UserEnum;

/**
 * encapsulate a result
 */
public class ResultUtil {

    public static Result userSuccess(Object object) {
        Result result = new Result();
        result.setCode(UserEnum.SUCCESS.getCode());
        result.setMsg(UserEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    // TODO handle errors, exceptions needed
    public static Result userError(UserEnum userEnum) {
        Result result = new Result();
        result.setCode(userEnum.getCode());
        result.setMsg(userEnum.getMsg());
        return result;
    }
}
