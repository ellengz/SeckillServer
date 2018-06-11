package com.ellen.seckill.enums;

/**
 * identified states for seckill-related behaviours
 */
public enum SeckillStateEnum {
    SUCCESS(200, "All good"),
    END(400, "Seckill ended"),
    NOT_START(401, "Not start yet"),
    REPEATED_ORDER(402, "Repeated order, you can only kill a product once"),
    DATA_REWRITE(403, "Illegal operation, please try again"),
    NO_STOCK(404, "No stock remaining"),
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
