package com.czx.yimem.utils;

import java.util.regex.Pattern;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/14 0014 18:19
 */
public class PhoneUtil {
    public static Boolean regular(String phone){
        Pattern pattern = Pattern.compile("^[1]\\d{10}$");
        if (!pattern.matcher(phone).matches()) {
            return false;
        }
        return true;
    }
}
