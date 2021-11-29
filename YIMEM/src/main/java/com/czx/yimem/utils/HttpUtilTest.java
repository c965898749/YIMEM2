package com.czx.yimem.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

//import net.sf.json.JSONObject;

public class HttpUtilTest {

    private static final String DEFAULT_CHARSET = "UTF-8";

    public static  String sendPost(String url,String Params)throws IOException{
        return sendAll(url,Params,"POST");
    }


    public static  String sendAll(String url,String Params,String akstype)throws IOException{
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        String response="";
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod(akstype);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("opensessionid","opensessionidtest");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //POST请求
            out = new OutputStreamWriter(
                    conn.getOutputStream());
            out.write(Params);
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response+=lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(reader!=null){
                    reader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return response;
    }

    public static void main(String[] strs) throws Exception{

//        TsOrderReq tsOrderReq=new TsOrderReq();
//        tsOrderReq.setOrders_id("12345");
//        tsOrderReq.setItem_id(41);
//        tsOrderReq.setName("用户");
//        tsOrderReq.setMobile("13515222627");
//        tsOrderReq.setLaunchid(po.getOrderid());
//        tsOrderReq.setTradeno(ads.getTradeno());

        Map<String,Object> map=new HashMap<>();
        map.put("method", "orders_list");
        map.put("_pid", TsConf._PID);
        map.put("size",100);
//        map.put("orders_id", tsOrderReq.getOrders_id());
//        map.put("item_id", tsOrderReq.getItem_id());
//        map.put("name", tsOrderReq.getName());
//        map.put("mobile", tsOrderReq.getMobile());

        String result=MD5Utils.getMapSort(map,TsConf.AUTHCODE);
        map.put("_sig",result);

        String url="http://demo.demo1.sjdzp.com/Api/Seller/api.json";
//        String url="http://demo.demo1.sjdzp.com/Api/seller/api.php";
//        String ParamSs=JSONObject.fromObject(map).toString();
        String reslut=HttpUtilTest.postAsyncHttpClient(url,map,null);
        JSONObject jsonObject= JSONObject.parseObject(reslut);
        System.out.println(jsonObject.toJSONString());
        if(jsonObject.getBoolean("success")){
            JSONArray jsonArray=jsonObject.getJSONArray("list");
            System.out.println("11111");
//            ads.setTradeno(tsOrderReq.getTradeno());
//            ads.setLaunchid(tsOrderReq.getLaunchid());
//            ts_orderResultMapper.insertSelective(ads);
            //更新卡券中券号 ( 暂不需要)
        }


//        try {
//            String reslut=HttpUtilTest.postAsyncHttpClient(url,map,null);
//            JSONObject jsonObject=JSONObject.parseObject(reslut);
//            System.out.println(jsonObject.toJSONString());
//
//        }catch (Exception e){
//
//        }

    }

    public static String postAsyncHttpClient(String url, Map<String, Object> params, Map<String, Object> header) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
        builder.setBodyEncoding("utf-8");
        if(header!=null){
            if (header != null && !header.isEmpty()) {
                Set<String> keys = header.keySet();
                for (String key : keys) {
                    builder.addHeader(key, String.valueOf(header.get(key)));
                }
            }
        }
        if (params != null && !params.isEmpty()) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.addParameter(key, String.valueOf(params.get(key)));
            }
        }
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody("UTF-8");
        http.close();
        return body;
    }

    public static String sendPostHander(String url, String Params, Map<String, Object> header)throws IOException{
        return  qudoJsonPost( url,  Params,header);
    }

    public static String qudoJsonPost(String url, String Params, Map<String, Object> header) throws UnsupportedEncodingException {
        OutputStreamWriter out = null;
        DataOutputStream  outd = null;
        OutputStream os = null;
        BufferedReader reader = null;
        String response="";
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type","application/json;charset=utf-8");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("contentType", "utf-8");
            if(header!=null){
                if (header != null && !header.isEmpty()) {
                    Set<String> keys = header.keySet();
                    for (String key : keys) {
                        conn.setRequestProperty(key, String.valueOf(header.get(key)));
                    }
                }
            }

            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            writer.write(Params);
            writer.close();
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), Charset.forName("utf-8"));
                response+=lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(reader!=null){
                    reader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return URLDecoder.decode(response,"UTF-8");
    }

    public static String post(String url, String s) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        builder.setBody(s);
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param charset
     *             发送和接收的格式
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param,String charset) {
        String result = "";
        String line;
        StringBuffer sb=new StringBuffer();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性 设置请求格式
            conn.setRequestProperty("contentType", charset);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(6000);
            conn.setReadTimeout(10000);
            // 建立实际的连接
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应,设置接收格式
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(),charset));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result=sb.toString();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param map
     *            请求Map参数，请求参数应该是 {"name1":"value1","name2":"value2"}的形式。
     * @param charset
     *             发送和接收的格式
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, Map<String,Object> map,String charset){
        StringBuffer sb=new StringBuffer();
        //构建请求参数
        if(map!=null&&map.size()>0){
            Iterator it=map.entrySet().iterator(); //定义迭代器
            while(it.hasNext()){
                Map.Entry er= (Map.Entry) it.next();
                sb.append(er.getKey());
                sb.append("=");
                sb.append(er.getValue());
                sb.append("&");
            }
        }
        return  sendGet(url,sb.toString(), charset);
    }
}
