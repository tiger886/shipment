package com.gh.shipmentmanagment.util;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * Util for double calc
 *
 * @Author: Tiger
 * @Date: 2020/10/10
 */

public class DoubleUtil {
    private static final Integer DEF_DIV_SCALE = 2;

    /**
     * double add
     *
     * @param value1
     * @param value2
     * @return sum
     */
    public static Double add(Double value1, Double value2) {
        BigDecimal b1;
        b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2;
        b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * double sub
     *
     * @param value1
     * @param value2
     * @return sub
     */
    public static double sub(Double value1, Double value2) {
        BigDecimal b1;
        b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2;
        b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * double multiply
     *
     * @param value1
     * @param value2
     * @return multi
     */
    public static Double mul(Double value1, Double value2) {
        BigDecimal b1;
        b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2;
        b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * divide
     *
     * @param dividend
     * @param divisor
     * @param isScale  true not scale
     * @return divide
     */
    public static Double divide(Double dividend, Double divisor, boolean isScale) {

        BigDecimal b1;
        b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2;
        b2 = new BigDecimal(Double.toString(divisor));
        if (isScale) {
            return b1.divide(b2, RoundingMode.UNNECESSARY).doubleValue();
        } else {
            return b1.divide(b2, DEF_DIV_SCALE, RoundingMode.HALF_UP).doubleValue();
        }
    }


}
