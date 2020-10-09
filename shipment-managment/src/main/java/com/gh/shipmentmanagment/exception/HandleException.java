package com.gh.shipmentmanagment.exception;

import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * System exception handler
 * @Author: Tiger
 * @Date: 2020/10/10
 */

@Slf4j
@RestControllerAdvice
public class HandleException {

    /**
     * common exception handler
     */
    @ExceptionHandler(value = {Exception.class})
    public MessageResult handleException(Exception e) {

        if (e instanceof TemplateException) {
            TemplateException customException = (TemplateException) e;
            return MessageResultUtil.setErrorResult(customException.getCode(), customException.getMessage());

        } else if (e instanceof MethodArgumentNotValidException) {
            return MessageResultUtil.setErrorResult(ErrorCodeEnum.PARAM_ERROR.getCode(), ErrorCodeEnum.PARAM_ERROR.getMsg());

        } else if(e instanceof JsonParseException){
            return MessageResultUtil.setErrorResult(ErrorCodeEnum.JSON_FORMAT_ERROR.getCode(), ErrorCodeEnum.JSON_FORMAT_ERROR.getMsg());

        }
        else {//system pre define exception
            log.info("system error {}", e);
            return MessageResultUtil.setErrorResult(ErrorCodeEnum.UNKONW_ERROR.getCode(), ErrorCodeEnum.UNKONW_ERROR.getMsg());
        }
    }
}
