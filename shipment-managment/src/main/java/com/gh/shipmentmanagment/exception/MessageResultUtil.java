package com.gh.shipmentmanagment.exception;


/**
 *  Rest api response Util
 * @Author: Tiger
 * @Date: 2020/10/10
 */

public class MessageResultUtil {
    public static <T> MessageResult<T> setSuccessResult(T data) {
        MessageResult<T> result = new MessageResult<T>(true);
        result.setData(data);
        return result;
    }

    public static MessageResult setErrorResult(ErrorCodeEnum errorCodeEnum) {
        MessageResult result = new MessageResult(false);
        result.setData(null);
        result.setCode(errorCodeEnum.getCode());
        result.setMsg(errorCodeEnum.getMsg());
        return result;
    }

    public static MessageResult setErrorResult(Integer errorCode, String errorMessage) {
        MessageResult result = new MessageResult(false);
        result.setData(null);
        result.setCode(errorCode);
        result.setMsg(errorMessage);
        return result;
    }
}
