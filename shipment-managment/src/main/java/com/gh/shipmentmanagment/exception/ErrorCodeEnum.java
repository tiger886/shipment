package com.gh.shipmentmanagment.exception;

/**
 * System Error code definition
 * @Author: Tiger
 * @Date: 2020/10/10
 */

public enum ErrorCodeEnum {

    UNKONW_ERROR(0, "Unknown System error"),

    PARAM_ERROR(1000, "参数异常"),
    JSON_FORMAT_ERROR(1001, "JSON 格式错误"),

    ORDER_NOT_EXITS(2000, "订单不存在"),
    ORDER_TON_VALID_ERROR(2001, "订单货物实际重量不符"),
    ORDER_TON_ADJUST_ERROR(2002, "调整订单货物重量失败，计算结果无法精确处理"),

    SHIPMENT_ID_NOT_EXITS(3000,"物流参数ID不存在"),
    SHIPMENT_SPLIT_TON_VALID_ERROR(3001,"物流分拆实际重量不符"),
    SHIPMENT_MERGE_ID_VALID_ERROR(3002,"物流合并参数ID不存在"),
    SHIPMENT_HAS_EXITS(3003,"物流已存在，请勿重复操作");

    private Integer code;
    private String msg;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
