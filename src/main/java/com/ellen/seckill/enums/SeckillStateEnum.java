package com.ellen.seckill.enums;

public enum SeckillStateEnum {
    SUCCESS(200, "Order created"),
    END(201, "Seckill ended"),
    REPEATED_ORDER(400, "Repeated order, you can only kill a product once"),
    DATA_REWRITE(401, "Illegal operation, please try again"),
    INNER_ERROR(500, "System error, please try again later"),

    ;

    private Integer code;
    private String msg;

    SeckillStateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static SeckillStateEnum stateOf (Integer code) {
        for (SeckillStateEnum stateEnum : values()) {
            if (stateEnum.getCode() == code) {
                return stateEnum;
            }
        }
        return null;
    }
}
