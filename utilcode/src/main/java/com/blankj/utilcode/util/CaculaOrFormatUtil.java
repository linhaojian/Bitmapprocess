package com.blankj.utilcode.util;

import java.math.BigDecimal;

/**
 * Created by NongYudi on 2017/12/7.
 */

public class CaculaOrFormatUtil {
    public static double add(double t,double t2){
        return new BigDecimal(t).add(new BigDecimal(t2)).doubleValue();
    }
    public static double add(String t,String t2){
        return new BigDecimal(t).add(new BigDecimal(t2)).doubleValue();
    }
    public static double sub(String t,String t2){
        return new BigDecimal(t).subtract(new BigDecimal(t2)).doubleValue();
    }
    public static double sub(double t,double t2){
        return new BigDecimal(t).subtract(new BigDecimal(t2)).doubleValue();
    }

    /***
     * 保留两位小数
     * @param t
     * @return
     */
    public static double setScale2(double t){
        return new BigDecimal(t).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
    }
    public static double setScale2(String t){
        return new BigDecimal(t).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
    }
}
