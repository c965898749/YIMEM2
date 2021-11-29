package com.czx.yimem.utils;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class NesSendAliyun {
    //对应阿里云账号
    private static String accessKeyId="11";
    private static String secret="22";
    private static String regionId="default";
    private static String TEMPLATE_CODE_JHRC = "33";
    private static String TEMPLATE_SIGN_AXMEM = "11";
    public static void sendMes(String phoneNumber,String[] msg,String plagid){

        DefaultProfile profile = DefaultProfile.getProfile(regionId,accessKeyId,secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("PhoneNumbers", phoneNumber);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",msg[0]);
        request.putQueryParameter("TemplateParam", jsonObject.toJSONString());

        plagid="kk";

        request.putQueryParameter("SignName", TEMPLATE_SIGN_AXMEM);
        request.putQueryParameter("TemplateCode", TEMPLATE_CODE_JHRC);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }








}
