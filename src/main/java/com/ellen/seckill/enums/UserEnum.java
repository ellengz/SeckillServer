package com.ellen.seckill.enums;

public enum UserEnum {
    SUCCESS(0, "Welcome"),
    NO_MATCH(100, "Username or password is wrong"),
    REPEAT_USERNAME(200, "Username already exists, please choose another name"),
    ;

    private Integer code;
    private String msg;

    UserEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
