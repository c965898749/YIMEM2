package com.czx.yimem.service.financial.impl;

import com.czx.yimem.config.RedisOrderIDGenerate;
import com.czx.yimem.service.financial.WexinPayService;
import com.czx.yimem.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/16 0016 22:02
 * www.yimem.com
 */
@Slf4j
@Service
public class WexinPayServiceImpl implements WexinPayService {
    @Autowired
    private RedisOrderIDGenerate redisOrderIDGenerate;
    @Override
    public Object Presentation(String appendid, String money) {
        String key = WXPayConstant.PAY_APP_SECRET;

        // 1.0 拼凑企业支付需要的参数
        String appid = WXPayConstant.APP_ID; // 微信公众号的appid
        String mch_id = WXPayConstant.MCH_ID; // 商户号
        String nonce_str = CreateRandomUtil.getRandom(20);// 随机字符串
        String partner_trade_no="";
        synchronized (this) {
            partner_trade_no = redisOrderIDGenerate.orderNo(Constants.ORDER_ON, 1L);;// 商户订单号
        }
        String openid = appendid; // 支付给用户openid
        String check_name = "NO_CHECK"; // 是否验证真实姓名呢
        String re_user_name = "KOLO"; // 收款用户姓名(非必须)

        BigDecimal tmoney=new BigDecimal("100");
        BigDecimal newtmoney=new BigDecimal(money);
        money=newtmoney.multiply(tmoney).toString();

        Integer aaaaaaa= newtmoney.multiply(tmoney).intValue();
        String amount = aaaaaaa.toString(); // 企业付款金额，最少为100，单位为分

        String desc = "恭喜你，提现成功"; // 企业付款操作说明信息。必填。
        String spbill_create_ip = "121.229.12.66"; // 用户的ip地址
        // 2.0 生成map集合
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
        //Map<String, String> packageParams = new HashMap<String, String>();
        packageParams.put("mch_appid", appid); // 微信公众号的appid
        packageParams.put("mchid", mch_id); // 商务号
        packageParams.put("nonce_str", nonce_str); // 随机生成后数字，保证安全性
        packageParams.put("partner_trade_no", partner_trade_no); // 生成商户订单号
        packageParams.put("openid", openid); // 支付给用户openid
        packageParams.put("check_name", check_name); // 是否验证真实姓名呢
        packageParams.put("re_user_name", re_user_name);// 收款用户姓名
        packageParams.put("amount", amount); // 企业付款金额，单位为分
        packageParams.put("desc", desc); // 企业付款操作说明信息。必填。
        packageParams.put("spbill_create_ip", spbill_create_ip); // 调用接口的机器Ip地址
        try {
            //3.0 签名
            String sign = XMLUtil.createSign("UTF-8", packageParams,key);  //获取签名
            packageParams.put("sign", sign);
            // 5.0将当前的map结合转化成xml格式
            //String xml = WXPayUtil.mapToXml(packageParams);
            String xml = XMLUtil.getRequestXml(packageParams);//将请求参数转换成xml类型
            //String xml = MessageUtil.messageToXML(packageParams);// 打包要发送的xml
            // 6.0获取需要发送的url地址
            String wxUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers"; // 获取退款的api接口
            log.info("发送前的xml为：" + xml);
            // 7,向微信发送请求转账请求
            String returnXml = CertHttpUtil.postData(wxUrl, xml, WXPayConstant.MCH_ID, WXPayConstant.CLIENT_CERT_NAME);
            log.info("返回的returnXml为:" + returnXml);
            // 8，将微信返回的xml结果转成map格式
            Map returnMap = XMLUtil.doXMLParse(returnXml);
            if (returnMap.get("return_code").equals("SUCCESS")&&returnMap.get("return_msg").equals("")) {
                // TODO 写你要处理的逻辑,一般是操作数据库，写公司的业务逻辑
                return "退款成功 ";
            }
            return "退款失败 ";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }



}
