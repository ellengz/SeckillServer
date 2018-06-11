package com.ellen.seckill.enums;

/**
 * identified states for user-related behaviours
 */
public enum UserStateEnum {
    SUCCESS(200, "Welcome"),
    NO_MATCH(400, "Username or password is wrong"),
    REPEAT_USERNAME(401, "Username already exists, please choose another name"),
    ;

    private Integer code;
    private String msg;

    UserStateEnum(Integer code, String msg) {
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
