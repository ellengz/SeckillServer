package com.ellen.seckill.exception;

/**
 * a base exception class
 */
public class BaseException extends RuntimeException {

    protected Integer code;

    public BaseException(String msg) {
        super(msg);
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
}
