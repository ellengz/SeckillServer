package com.ellen.seckill.exception;

import com.ellen.seckill.enums.SeckillStateEnum;

/**
 * a seckill-related exception class created based on UserStateEnum
 */
public class SeckillException extends BaseException{

    public SeckillException(SeckillStateEnum seckillStateEnum) {
        super(seckillStateEnum.getMsg());
        this.code = seckillStateEnum.getCode();
    }

}
