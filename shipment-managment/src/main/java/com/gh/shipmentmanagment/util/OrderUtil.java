package com.gh.shipmentmanagment.util;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * order util
 *
 * @Author: Tiger
 * @Date: 2020/10/10
 */

public class OrderUtil {
    /**
     * create order no ,len=14
     *
     * @return
     */
    public static String createOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        return newDate;

    }
}
