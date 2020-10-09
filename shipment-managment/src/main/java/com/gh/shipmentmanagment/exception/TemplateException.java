package com.gh.shipmentmanagment.exception;

/**
 * custom exception
 *
 * @Author: Tiger
 * @Date: 2020/10/10
 */
public class TemplateException extends RuntimeException {
    private static final long serialVersionUID = -922044584332038225L;
    public Integer code;

    public TemplateException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        code = errorCodeEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
