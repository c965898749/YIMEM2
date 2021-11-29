package com.czx.yimem.utils;

import com.czx.yimem.dto.WxQrcode;
import com.czx.yimem.entity.requestbean.ResultBeanObj;
import com.czx.yimem.dto.WeChatConf;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by cuixn on 2019/3/7.
 */
@Component
public class WeChatUtil {
    @Autowired
    private RedisAPI redisAPI;

    //测试
    public final static String POSTURL = "http://192.168.17.107:8080";

    private final static String AXFCAPP_ID = "22"; //
    private final static String AXFCSECRET = "33";
    private final static String DFAPP_ID = "454";
    private final static String DFSECRET = "55";
    //阿里云图片
    public final static String IMGURL = "66";

    //积分商品的图片地址
    private final static String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/qrcode";//个人专属二维码


    public ResultBeanObj DOCREATERESULTOBJ(String req) {
        JSONObject jso = JSONObject.parseObject(req);
        String success = jso.getString("success");
        if ("1".equals(success)) {
            return ResultBeanObj.success().setData(jso);
        } else {
            return ResultBeanObj.fail(jso.getString("msg")).setData(jso);
        }
    }

    public String todealintegral(String platgid, String opensessionid, String usescene, String addOrdel, double price, String billcode, String dealobject) throws Exception {
        Map postmap = new HashMap();
        HttpUtilTest hut = new HttpUtilTest();
        Map signmap = new HashMap();
        signmap.put("uniquegid", UUID.randomUUID().toString());
        signmap.put("platgid", platgid);
        signmap.put("usescene", usescene);
        signmap.put("makergid", "");
        signmap.put("billcode", "aunt" + billcode);
        signmap.put("dealobject", dealobject);//1本身2创客


        signmap.put(addOrdel, price);//加或者减
        String resign = SignUtils.sign(JSONObject.toJSONString(signmap), SignUtils.PRIVATEKEY, true);
        postmap.put("json", JSONObject.toJSONString(signmap));
        postmap.put("sign", resign);
        String req = "{}";
        Map handmap = new HashMap();
        handmap.put("opensessionid", opensessionid);
        req = hut.sendPostHander(WeChatUtil.POSTURL + "/api/dealbalanceaccount", JSONObject.toJSONString(postmap), handmap);
        return req;
    }
    ////////////////////////////////////////////////////////////////////二维码相关////////////////////////////////////////////////////////////////////

    public WxQrcode qrCodeCreateTmpTicketStr(String scene_str, Integer expire_seconds, WeChatConf wechatconf) throws Exception {
        String url = API_URL_PREFIX + "/create?access_token=" + wechatconf.getAccesstoken();
        JSONObject json = new JSONObject();
        json.put("action_name", "QR_STR_SCENE");
        /*     json.put("action_name", "QR_SCENE");*/
        if (expire_seconds != null) {
            json.put("expire_seconds", expire_seconds);
        }
        JSONObject actionInfo = new JSONObject();
        JSONObject scene = new JSONObject();
/*        Integer scene_id=Integer.valueOf(scene_str);
         scene.put("scene_id", scene_id);*/
        scene.put("scene_str", scene_str);
        actionInfo.put("scene", scene);
        json.put("action_info", actionInfo);
        String outString = HttpUtilTest.post(url, json.toString());
//        LOG.info("1111111="+outString);
        return JSON.parseObject(outString, WxQrcode.class);
    }

    public String qrCodePictureUrl(String ticket) throws Exception {
        String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";
        String resultUrl = String.format(url, URLEncoder.encode(ticket, StandardCharsets.UTF_8.name()));
        return resultUrl;
    }

    /**
     * 下载网络图片，同时二维码和网络图片合成
     *
     * @param filenames  文件名
     * @param imgUrl
     * @param showurl    展示地址
     * @param bucketName 文件对象存储所在文件夹
     * @param membername 用户名
     * @param number
     * @param headimg    头像地址
     * @return
     */
    public Map getQcordImage(String filenames, String imgUrl, String showurl, String bucketName, String membername, String number, String headimg) {
        Map map = new HashMap();

        String filename = filenames;
        String path = UtilPath.getRootPath() + "upload";
        //没有目录创建目录
        File imgFolder = new File(path);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        String lastfile = path + "/" + filename;//合成图片
        String baseImg = IMGURL + imgUrl;
        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/"));
        String dirfile = path + fileName;
        File files = new File(dirfile);
        String bgfilepathURL = "";
        String headingUrl = "";
        String headimgs = "headimg.jpg";
        int height = 130;
        int width = 130;
        try {
            if (files.exists()) {
                bgfilepathURL = dirfile;
            } else {
                //获取目录名
                File rootfile = new File(path);
                File[] DIRfiles = rootfile.listFiles();
                //遍历删除文件
                for (File file : DIRfiles) {
                    file.delete();
                }
                bgfilepathURL = ImageRequest.imgCpty(baseImg, path, fileName);//path+"/bg.jpg";//背景图片
                if (!StringUtil.isNullObject(headimg)) {
                    headimg = URLDecoder.decode(headimg, "UTF-8");
                    headingUrl = ImageRequest.cotyhead(headimg, path, headimgs);
                }
            }
            ImageIcon imageIcon = new ImageIcon(bgfilepathURL);
            int iconWidth = imageIcon.getIconWidth();
            int iconHeight = imageIcon.getIconHeight();

            int x = iconWidth - 160;
            int y = iconHeight - 160;
            //生成二维码，传入指定地址
            boolean bool = QRCodeUtil.createQrcodeWithLogo(showurl, lastfile, width, height, null, bgfilepathURL, path, x, y, membername, number, headingUrl);
            if (bool) {
                File file = new File(lastfile);
                String filepath = "/qrcode/" + filename;
                map.put("success", "1");
                map.put("filepath", filepath);
                UploadUtil.sendImage(file, "qrcode", filename, bucketName);
                file.delete();
            } else {
                map.put("success", "0");
            }
        } catch (Exception e) {
            ImageIcon imageIcon = new ImageIcon(bgfilepathURL);
            int iconWidth = imageIcon.getIconWidth();
            int iconHeight = imageIcon.getIconHeight();
            int x = iconWidth - 160;
            int y = iconHeight - 160;
            boolean bool = QRCodeUtil.createQrcodeWithLogo(showurl, lastfile, width, height, null, bgfilepathURL, path, x, y, membername, number, "");
            if (bool) {
                File file = new File(lastfile);
                String filepath = "/qrcode/" + filename;
                map.put("success", "1");
                map.put("filepath", filepath);
                UploadUtil.sendImage(file, "qrcode", filename, bucketName);
                file.delete();
            } else {
                map.put("success", "0");
            }
        }
        return map;
    }

    /*public  void main(String[] str){
        try {
            String req  = getMakerType("13236110260");
            System.out.print(req);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    public String showYourCity(String latitude, String longitude) throws IOException, ExecutionException, InterruptedException {
        String req = "{}";
        HttpUtilTest hut = new HttpUtilTest();
        JSONObject postmap = new JSONObject();
        postmap.put("location", latitude + "," + longitude);
        postmap.put("output", "json");
        postmap.put("pois", 1);
        req = hut.sendPost("http://api.map.baidu.com/geocoder", JSONObject.toJSONString(postmap));//
        return req;
    }

    public String GetAddr(String latitude, String longitude) {
        String addr = "error Loading location Info";
        /*
         * 也可以是http://maps.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s，不过解析出来的是英文地址
         * 密钥可以随便写一个key=abc
         * output=csv,也可以是xml或json，不过使用csv返回的数据最简洁方便解析
         */
        String url = "http://api.map.baidu.com/geocoder?location=" + latitude + "," + longitude + "&output=json&pois=1";
        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        try {

            httpsConn = (URLConnection) myURL.openConnection();

            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                    String[] retList = data.split(",");
                    if (retList.length > 2 && ("200".equals(retList[0]))) {
                        addr = retList[2];
                    } else {
                        addr = "error Loading location";
                    }
                }
                insr.close();
            }
        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
        return addr;

    }

    /**
     * 获取小程序access_token
     *
     * @return
     */
    public String getAccessToken() {
        if (!redisAPI.exist("access_token") || redisAPI.get("access_token") == null) {
            String url = "https://api.weixin.qq.com/cgi-bin/token";
            String param = "appid=" + Constants.APPID + "&secret=" + Constants.SECRET + "&grant_type=client_credential";
            Map map = new HashMap();
            map.put("grant_type", "client_credential");
            map.put("appid", Constants.APPID);
            map.put("secret",Constants.SECRET);
            String req = "{}";
            HttpUtilTest hut = new HttpUtilTest();
            req = HttpUtilTest.sendGet(url, param, "utf-8");
            JSONObject jso = JSONObject.parseObject(req);
            redisAPI.set("access_token", jso.getString("access_token"), 110 * 60);
            return  jso.getString("access_token");
        } else {
            return redisAPI.get("access_token").toString();
        }
    }

    /**
     * 生成指定参数小程序二维码
     *
     * @param path  要跳转页面的路径
     * @param param 所需参数
     * @return
     */
    public String getWechatCode(String path, String param) throws IOException {
        String token = "";
        if (!redisAPI.exist("access_token") || redisAPI.get("access_token") == null) {
            token = getAccessToken();
            redisAPI.set("access_token", token, 110 * 60);
        } else {
            token = redisAPI.get("access_token").toString();
        }
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + token;
        JSONObject postmap = new JSONObject();
        Map<String, Object> color = new HashMap<String, Object>();
        color.put("r", 0);
        color.put("g", 0);
        color.put("b", 0);
        postmap.put("scene", param);
        postmap.put("page", path);
        postmap.put("width", 430);
        postmap.put("auto_color", false);
        postmap.put("line_color", color);
        postmap.put("is_hyaline", false);
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        String response = null;
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("opensessionid", "opensessionidtest");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //POST请求
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(JSONObject.toJSONString(postmap));
            System.out.print("返回图片buffer信息：" + JSONObject.toJSONString(postmap));
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            InputStream iss = conn.getInputStream();
            response = UploadUtil.uploadInputStream(iss);//UploadCtyunUtil.uploadInputStream(iss);
            reader.close();
            // 断开连接
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用final块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 获取小程序access_token
     *
     * @return
     */
    public String getAXAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String param = "appid=" + AXFCAPP_ID + "&secret=" + AXFCSECRET + "&grant_type=client_credential";
        Map map = new HashMap();
        map.put("grant_type", "client_credential");
        map.put("appid", AXFCAPP_ID);
        map.put("secret", AXFCSECRET);
        String req = "{}";
        HttpUtilTest hut = new HttpUtilTest();
        req = HttpUtilTest.sendGet(url, param, "utf-8");
        JSONObject jso = JSONObject.parseObject(req);
        return jso.getString("access_token");
    }

    /**
     * 生成指定参数小程序二维码
     *
     * @param path  要跳转页面的路径
     * @param param 所需参数
     * @return
     */
    public String getAXWechatCode(String path, String param) throws IOException {
        String token = "";
        if (!redisAPI.exist("access_ax_token") || StringUtil.isNullObject(redisAPI.get("access_ax_token").toString())) {
            token = getAXAccessToken();
            redisAPI.set("access_ax_token", token, 110 * 60);
        } else {
            token = redisAPI.get("access_ax_token").toString();
        }
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + token;
        JSONObject postmap = new JSONObject();
     /*   Map<String,Object> color = new HashMap<String,Object>();
        color.put("r",0);
        color.put("g",0);
        color.put("b",0);*/
        postmap.put("scene", param);//携带参数
        postmap.put("page", path);//小程序跳转页面
 /*       postmap.put("width", 430);//二维码宽度
        postmap.put("auto_color", false);//是否自动配置线条颜色
        postmap.put("line_color", color);//是否需要rgb颜色
        postmap.put("is_hyaline", false);//是否透明底色*/
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        String response = null;
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("opensessionid", "opensessionidtest");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //POST请求
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(JSONObject.toJSONString(postmap));
            System.out.print("返回图片buffer信息：" + JSONObject.toJSONString(postmap));
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            InputStream iss = conn.getInputStream();
            response = UploadUtil.uploadInputStream(iss);//UploadCtyunUtil.uploadInputStream(iss);
            reader.close();
            // 断开连接
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用final块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 获取小程序access_token
     *
     * @return
     */
    public String getDFAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String param = "appid=" + DFAPP_ID + "&secret=" + DFSECRET + "&grant_type=client_credential";
        Map map = new HashMap();
        map.put("grant_type", "client_credential");
        map.put("appid", DFAPP_ID);
        map.put("secret", DFSECRET);
        String req = "{}";
        HttpUtilTest hut = new HttpUtilTest();
        req = HttpUtilTest.sendGet(url, param, "utf-8");
        JSONObject jso = JSONObject.parseObject(req);
        return jso.getString("access_token");
    }

    /**
     * 生成指定参数小程序二维码
     *
     * @param path  要跳转页面的路径
     * @param param 所需参数
     * @return
     */
    public String getDFWechatCode(String path, String param) throws IOException {
        String token = "";
        if (!redisAPI.exist("access_df_token") || StringUtil.isNullObject(redisAPI.get("access_df_token").toString())) {
            token = getDFAccessToken();
            redisAPI.set("access_df_token", token, 110 * 60);
        } else {
            token = redisAPI.get("access_df_token").toString();
        }
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + token;
        JSONObject postmap = new JSONObject();
     /*   Map<String,Object> color = new HashMap<String,Object>();
        color.put("r",0);
        color.put("g",0);
        color.put("b",0);*/
        postmap.put("scene", param);//携带参数
        postmap.put("page", path);//小程序跳转页面
 /*       postmap.put("width", 430);//二维码宽度
        postmap.put("auto_color", false);//是否自动配置线条颜色
        postmap.put("line_color", color);//是否需要rgb颜色
        postmap.put("is_hyaline", false);//是否透明底色*/
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        String response = null;
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("opensessionid", "opensessionidtest");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //POST请求
            out = new OutputStreamWriter(conn.getOutputStream());
            out.write(JSONObject.toJSONString(postmap));
            System.out.print("返回图片buffer信息：" + JSONObject.toJSONString(postmap));
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            InputStream iss = conn.getInputStream();
            response = UploadUtil.uploadInputStream(iss);//UploadCtyunUtil.uploadInputStream(iss);
            reader.close();
            // 断开连接
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用final块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 小程序文本违规信息校验
     *
     * @param context
     * @return
     * @throws IOException
     */
    public String msgSecCheck(String context) throws IOException {
        String token = "";
        if (!redisAPI.exist("access_token") || redisAPI.get("access_token") == null) {
            token = getAccessToken();
            redisAPI.set("access_token", token, 110 * 60);
        } else {
            token = redisAPI.get("access_token").toString();
        }
        String url = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=" + token;
        JSONObject postmap = new JSONObject();
        postmap.put("content", context);
        String response = "";
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity requestEntity = new HttpEntity(postmap, headers);
        ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, (org.springframework.http.HttpEntity<?>) requestEntity, byte[].class, new Object[0]);
        response = new String(entity.getBody(), "utf-8");
        return response;
    }

    public String imgSecCheck(String imgpath) throws IOException {
        String token = "";
        if (!redisAPI.exist("access_token") || redisAPI.get("access_token") == null) {
            token = getAccessToken();
            redisAPI.set("access_token", token, 110 * 60);
        } else {
            token = redisAPI.get("access_token").toString();
        }
        String url = "https://api.weixin.qq.com/wxa/img_sec_check?access_token=" + token;
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost request = new HttpPost(url);
        request.addHeader("Content-Type", "application/octet-stream");
        InputStream inputStream = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        try {
            byte[] byt = new byte[inputStream.available()];
            inputStream.read(byt);
            request.setEntity(new ByteArrayEntity(byt, ContentType.create("image/jpg")));
            HttpResponse response = httpclient.execute(request);
            org.apache.http.HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");// 转成string
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取所有经纪人信息
     *
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public String getBrokers() throws IOException, ExecutionException, InterruptedException {
        Map postmap = new HashMap();
        String req = "{}";
        HttpUtilTest hut = new HttpUtilTest();
        req = hut.sendPost("http://oa.axfc.cn/oa/Bmapi/allpeople", JSONObject.toJSONString(postmap));//
        return req;
    }

    public String addVipRecommend(String loginno, String memgid, String recommendmobile, String recommendmemgid) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map postmap = new HashMap();
        postmap.put("VipNumber", memgid);
        postmap.put("VipMobile", loginno);
        postmap.put("UserMobile", recommendmobile);
        postmap.put("UserVipNumber", recommendmemgid);
        postmap.put("RegisterTime", sdf.format(new Date()));
        String req = "{}";
        HttpUtilTest hut = new HttpUtilTest();
        //req = hut.postAsyncHttpClient( "http://192.168.0.4:8102/vip/VipUser/activationVipUser",postmap,null);//

        //        "http://nantonghms.erp.axfc.cn/vip/VipUser/activationVipUser"
        //1://南通、2://盐城、3://宿迁、4://徐州、5://淮安、 6://泰州  7：//连云港  8:扬州
        //String httpurl="http://nantonghms.erp.axfc.cn";//默认南通
        /*if(setarea==1){
            httpurl="http://nantonghms.erp.axfc.cn";
        }else if(setarea==2){
            httpurl="http://yanchenhms.erp.axfc.cn";
        }else if(setarea==3){
            httpurl="http://suqianhms.erp.axfc.cn";
        }else if(setarea==4){
            httpurl="http://xuzhouhms.erp.axfc.cn";
        }else if(setarea==5){
            httpurl="http://huaianhms.erp.axfc.cn";
        }else if(setarea==6){
            httpurl="http://taizhouhms.erp.axfc.cn";
        }else if(setarea==7){
            httpurl="http://lyghms.erp.axfc.cn";
        }else if(setarea==8){
            httpurl="http://yzhms.erp.axfc.cn";
        }*/
        // httpurl=httpurl+"/vip/VipUser/activationVipUser";
        //String httpurl = "http://106.14.222.134:8101/api/VipUser/addVipRecommend";
        String httpurl = "";
        req = hut.sendPost(httpurl, JSONObject.toJSONString(postmap));
        return req;
    }

//    {
//            try{
//                    Security.addProvider(new BouncyCastleProvider());
//            }catch(Exception e){
//                    e.printStackTrace();
//            }
//    }


    /*    public  String getPhoneNumber(String session_key, String encryptedData, String iv) throws Exception  {
     *//*session_key="RWxoK6dIn2C1VwT4WtRaAA==";
        encryptedData="7YfOff0UJm+NC8DBNR65gEAJEndu3ra758Q3cNxoy+r/y5oOvwxTcgj6h6H33DdOihI5dY9PEw2onw7XJbVXBlopap373O6rDM0fR/Mp5770VnOk88VautAcjDaLzpYliXkzF5PFR/Q/gywOJ7zqjNPNV2uqFp3qMobqT+cKSCrWWBgk01N6vYj9nnL85QiRUmoQajJuCli6c6MgZHxpxg==";
        iv= "AV4d7KP+YMsocipKQlslrw==";*//*

    }*/

    public void main(String[] str) {
        try {
            //String result = activationVipUser("8efadb6b-eb27-4dbe-85c3-3bf19e84713b");
            /*String result = getWechatCode("pages/coupon/coupon","18019661788a108");*/
            //String result = getAccessToken();
            /*System.out.print(result);*/
            //String result =getPhoneNumber(null,null,null);
            /*String result = getMemberIntegral("18019661788");
            System.out.print(result);*/
//            redisAPI.set("Mem:member","111");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getTodayCook(String ids) {
        return null;
    }


}
