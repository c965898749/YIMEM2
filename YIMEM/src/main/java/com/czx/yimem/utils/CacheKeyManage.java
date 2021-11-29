package com.czx.yimem.utils;

public class CacheKeyManage {

    /**
     * 获取月浏览量key 值
     * @param merchantid
     * @param year
     * @param month
     * @return
     */
    public static String getMonthPvKey(String merchantid,String year,String month){
        return "M"+merchantid+year+month;
    }

    /**
     * 获取会员id key
     * @param gid
     * @return
     */
    public static String getMemId(String gid){
        return "mem"+gid;
    };

    /**
     * 会话id key
     * @param gid
     * @return
     */
    public static String getOpenSessionId(String gid){
        return "opensessionid"+gid;
    };

    /**
     * 验证码key
     * @param vcode
     * @return
     */
    public static String getVcode(String vcode){
        return "vcode"+vcode;
    };

    /**
     * 接入平台gid
     * @param gid
     * @return
     */
    public static String getAccessplat(String gid){
        return "accessplat"+gid;
    };

}
