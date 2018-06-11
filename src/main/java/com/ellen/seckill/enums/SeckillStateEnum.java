package com.ellen.seckill.enums;

/**
 * identified states for seckill-related behaviours
 */
public enum SeckillStateEnum {
    SUCCESS(200, "Order created"),
    END(400, "Seckill ended"),
    REPEATED_ORDER(401, "Repeated order, you can only kill a product once"),
    DATA_REWRITE(402, "Illegal operation, please try again"),
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
