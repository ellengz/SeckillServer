package com.ellen.seckill.exception;
import com.ellen.seckill.enums.UserStateEnum;

/**
 * a user-related exception class created based on UserStateEnum
 */
public class UserException extends BaseException{

    public UserException(UserStateEnum userStateEnum) {
        super(userStateEnum.getMsg());
        this.code = userStateEnum.getCode();
    }
}
