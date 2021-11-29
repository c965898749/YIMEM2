package com.czx.yimem.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/14 0014 18:05
 */
public class OpenidUtill {

    public static JSONObject getSessionKeyOrOpenId(String code) {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(WxUtils.getOpenidandSessionKey(code));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
