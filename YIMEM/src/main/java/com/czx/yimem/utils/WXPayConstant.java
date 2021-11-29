package com.czx.yimem.utils;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/16 0016 21:56
 * 支付常量类
 */

public class WXPayConstant {

    /**
     * 小程序 appId
     */
    public static final String APP_ID = "7";
    /**
     * 商户id
     */
    public static final String MCH_ID = "";
    /**
     * 支付密钥
     */
    public static final String PAY_APP_SECRET = "";

    /**
     * 支付回调地址
     */
    public static final String PAY_NOTIFY_URL = "";

    /**
     * 统一下单API接口链接
     */
    public static final String PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 证书名字
     */
    public static final String CLIENT_CERT_NAME = "cart/apiclient_cert.p12";

    /**
     * 微信退款接口
     */
    public static final String WEIXIN_REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

}
