package com.gh.shipmentmanagment.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  Rest api response Util
 * @Author: Tiger
 * @Date: 2020/10/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResult<T> {
    /**
     * API call status,true is no error
     */
    private boolean status;
    /**
     * Error code.
     */
    private Integer code;
    /**
     * Error message.
     */
    private String msg;
    /**
     * data.
     */
    private T data;

    public MessageResult(boolean status) {
        this.status = status;
    }

}
