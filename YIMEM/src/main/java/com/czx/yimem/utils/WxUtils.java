package com.czx.yimem.utils;
import com.czx.yimem.entity.AccessToken;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author xyd
 * @version V1.0
 * @Package com.demo.util
 * @Description:
 * @date 2018/8/6 17:24
 */
public class WxUtils {

    /**
     * 获取accessToken
     *
     * @return
     * @throws
     * @throws IOException
     */
//    public AccessToken getAccessToken(String toUserName) throws IOException {
//        AccessToken token = new AccessToken();
//        String url = null;
//        if (Constants.TO_USER_NAME.equals(toUserName)) {
//            url = Constants.ACCESS_TOKEN_URL.replace("APPID", Constants.APPID).replace("APPSECRET", Constants.APPSECRET);
//        } else {
//            url = Constants.ACCESS_TOKEN_URL.replace("APPID", Constants.APPID_2).replace("APPSECRET", Constants.APPSECRET_2);
//
//        }
//        JSONObject jsonObject = doGetStr(url);
//        if (jsonObject != null) {
//            token.setToken(jsonObject.getString("access_token"));
//            token.setExpiresIn(jsonObject.getString("expires_in"));
//        }
//        return token;
//    }

    /**
     * 获取openid和session_key
     */
    public static String getOpenidandSessionKey(String code) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();
//        String smsUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&SECRET=" + secret + "&code=" + code + "&grant_type=authorization_code";
        String smsUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="+Constants.APPID+"&secret="+Constants.SECRET+"&js_code="+code+"&grant_type=authorization_code";
        HttpGet httpGet = new HttpGet(smsUrl);
        String strResult = "";
        HttpResponse response = httpclient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            strResult = EntityUtils.toString(response
                    .getEntity(), "UTF-8");
        }
        return strResult;
    }
    /**
     * 获取Openid
     *
     * @param appid
     * @param secret
     * @param code
     * @return
     * @throws Exception
     */
    public static String getLoginAcessToken(String appid, String secret, String code) throws Exception {
        HttpClient httpclient = HttpClients.createDefault();
        String smsUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&SECRET=" + secret + "&code=" + code + "&grant_type=authorization_code";
        HttpGet httpGet = new HttpGet(smsUrl);
        String strResult = "";

        HttpResponse response = httpclient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            strResult = EntityUtils.toString(response
                    .getEntity(), "UTF-8");
        }
        return strResult;
    }

    /**
     * 获取微信用户信息
     * @param access_token
     * @param openid
     * @return
     * @throws Exception
     */
    public static String getWeiXinUser(String access_token,String openid) throws Exception{
        HttpClient httpclient = HttpClients.createDefault();
        String smsUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"D&lang=zh_CN";
        HttpGet httpGet = new HttpGet(smsUrl);
        String strResult = "";
        HttpResponse response = httpclient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            strResult = EntityUtils.toString(response
                    .getEntity(), "UTF-8");
        }
        return strResult;
    }

    /**
     * 2020/8/10 后不再使用的扫码登录方式 改造成账号绑定
     * @param appid
     * @param redirect_uri
     * @param state
     * @return
     * @throws Exception
     */
    public static String getURL(String appid, String redirect_uri, String state) throws Exception {
//        静默获取用户id
//        String smsUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_base&state=" + state + "#wechat_redirect";
//        因为用户绑定或注册涉及头像昵称
        String smsUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
        return smsUrl;
    }

    /**
     * jsapi_ticket获取
     */

    public static String getJsapiTicket(String access_token) throws Exception{
        HttpClient httpclient = HttpClients.createDefault();
        String smsUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
        HttpGet httpGet = new HttpGet(smsUrl);
        String strResult = "";
        HttpResponse response = httpclient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            strResult = EntityUtils.toString(response
                    .getEntity(), "UTF-8");
        }
        return strResult;
    }

    public JSONObject doGetStr(String url) throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        HttpResponse httpResponse = client.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
//            String result = EntityUtils.toString(entity,"UTF-8");
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        }
        return jsonObject;
    }

    /**
     * POST请求
     *
     * @param url
     * @param outStr
     * @return
     * @throws
     * @throws IOException
     */
    public JSONObject doPostStr(String url, String outStr) throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);
        JSONObject jsonObject = null;
//        httpost.setEntity(new StringEntity(outStr,"UTF-8"));
        httpost.setEntity(new StringEntity(outStr, "UTF-8"));
        HttpResponse response = client.execute(httpost);
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }



}
