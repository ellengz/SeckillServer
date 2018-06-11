package com.ellen.seckill.exception;

import com.ellen.seckill.enums.SeckillStateEnum;

public class SeckillException extends RuntimeException{

    private Integer code;

    public SeckillException(SeckillStateEnum seckillStateEnum) {
        super(seckillStateEnum.getMsg());
        this.code = seckillStateEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
