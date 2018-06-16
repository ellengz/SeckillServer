package com.ellen.seckill.domain;

import java.io.Serializable;

public class Result<T> implements Serializable{

    /** code **/
    private Integer code;
    /** message **/
    private String msg;
    /** content **/
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
