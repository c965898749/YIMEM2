package com.czx.yimem.utils;

import com.czx.yimem.entity.requestbean.ResultBeanObj;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Slf4j
public class UploadUtil {

    public static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    //2020 新oss
    public static String accessKeyId = "66";
    public static String accessKeySecret = "6";
    public static String bucketName = "77";

    public static String getAuntOssUrl = "44";

    public static ResultBeanObj upload(MultipartFile file, HttpServletRequest request, String type) throws Exception {
        Map reqmap = new HashMap();
        //图片、视频、文档的路径
        // String url= Constant.UpLOAD_PATH;
        String url = UtilPath.getRootPath() + "/upload";

        // 文件保存路径
/*        if("0".equals(type)){
            // 图片
            url += "/images/";
        }else if("1".equals(type)){
            //视频
            url += "/video/";
        }else if("2".equals(type)){
            //文档
            url += "/document/";
        }*/
        //获取文件名
        String fileName = file.getOriginalFilename();
        //文件扩展名
        String extName = fileName.substring(fileName.lastIndexOf("."));
        String newName = UUID.randomUUID().toString() + extName;
        System.out.println("file.getOriginalFilename()" + file.getOriginalFilename());

        Date nowDate = new Date();
        String year = StringUtils.dateToString(nowDate, "yyyy");
        String month = StringUtils.dateToString(nowDate, "MM");
        String imgurlnew = "/" + year + "/" + month + "/";
        String folder = url + imgurlnew;
        // 转存文件
        String filePath = folder + newName;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                //没有目录创建目录
                File imgFolder = new File(folder);
                if (!imgFolder.exists()) {
                    imgFolder.mkdirs();
                }
                File newFile = new File(filePath);
                file.transferTo(newFile);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultBeanObj.fail("路径错误");
            }
        }
        Map map = new HashMap();
        map.put("imageUrl", imgurlnew + newName);
        map.put("fileName", fileName);
        map.put("realPath", imgurlnew + newName);
        map.put("relaPath", filePath);
        return ResultBeanObj.success().setData(map);
    }

    public static ResultBeanObj uploadFileTwo(MultipartFile file, HttpServletRequest request, String type, String filename, String path) throws IOException {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Map map = new HashMap();
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            //文件扩展名
            String extName = fileName.substring(fileName.lastIndexOf("."));
            String newName = "";
            if (StringUtil.isNullObject(filename)) {
                newName = UUID.randomUUID().toString() + extName;
            } else {
                newName = filename + extName;
            }
            // 转存文件
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(new PutObjectRequest(bucketName, path + newName, inputStream));
            map.put("imageUrl", "/" + path + newName);
        } catch (OSSException oe) {
            return ResultBeanObj.fail("路径错误");
        } catch (ClientException ce) {
            return ResultBeanObj.fail("路径错误");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return ResultBeanObj.success().setData(map);
    }

    public static ResultBeanObj uploadFile(MultipartFile file, HttpServletRequest request, String type) throws IOException {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Map map = new HashMap();
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            //文件扩展名
            String extName = fileName.substring(fileName.lastIndexOf("."));
            String newName = UUID.randomUUID().toString() + extName;
            Date nowDate = new Date();
            String year = StringUtils.dateToString(nowDate, "yyyy");
            String month = StringUtils.dateToString(nowDate, "MM");
            String imgurlnew = "/" + year + "/" + month + "/";
            String imgurlnews = year + "/" + month + "/";
            String key = createFolder(ossClient, bucketName, imgurlnews);
            // 转存文件
            String filePath = imgurlnews + newName;
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(new PutObjectRequest(bucketName, imgurlnews + newName, inputStream));
            map.put("imageUrl", imgurlnew + newName);
            map.put("fileName", fileName);
            map.put("shortUrl", imgurlnew + newName);
            map.put("realPath", imgurlnew + newName);
//            map.put("relaPath",filePath);

        } catch (OSSException oe) {
            return ResultBeanObj.fail("路径错误");
        } catch (ClientException ce) {
            return ResultBeanObj.fail("路径错误");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return ResultBeanObj.success().setData(map);
    }

    public static ResultBeanObj uploadFile(MultipartFile file, HttpServletRequest request, String type, String bucketName) throws IOException {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Map map = new HashMap();
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            String fname=file.getName();
            Long filesize=file.getSize();
            //文件扩展名
            String extName = fileName.substring(fileName.lastIndexOf("."));
            String newName = UUID.randomUUID().toString() + extName;
            Date nowDate = new Date();
            String year = StringUtils.dateToString(nowDate, "yyyy");
            String month = StringUtils.dateToString(nowDate, "MM");
            String imgurlnew = "/" + year + "/" + month + "/";
            String imgurlnews = year + "/" + month + "/";
            String key = createFolder(ossClient, bucketName, imgurlnews);
            // 转存文件
            String filePath = imgurlnews + newName;
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(new PutObjectRequest(bucketName, imgurlnews + newName, inputStream));
            map.put("shortUrl", getAuntOssUrl+imgurlnew + newName);
            map.put("realPath", imgurlnew + newName);
            map.put("extName",extName);
            map.put("filesize",filesize);
        } catch (OSSException oe) {
            return ResultBeanObj.fail("路径错误");
        } catch (ClientException ce) {
            return ResultBeanObj.fail("路径错误");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return ResultBeanObj.success().setData(map);
    }


    public static ResultBeanObj uploadImage(MultipartFile file, HttpServletRequest request, String type, String bucketName) throws IOException {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        Map map = new HashMap();
        try {
            //获取文件名
            String fileName = file.getOriginalFilename();
            //文件扩展名
            String extName = fileName.substring(fileName.lastIndexOf("."));
            String newName = UUID.randomUUID().toString() + extName;
            Date nowDate = new Date();
            String year = StringUtils.dateToString(nowDate, "yyyy");
            String month = StringUtils.dateToString(nowDate, "MM");
            String imgurlnew = "/" + year + "/" + month + "/";
            String imgurlnews = year + "/" + month + "/";
            String key = createFolder(ossClient, bucketName, imgurlnews);
            // 转存文件
            String filePath = imgurlnews + newName;
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(new PutObjectRequest(bucketName, imgurlnews + newName, inputStream));

            //无法判断getAuntOssUrl的值 先注释
            map.put("imageUrl", UploadUtil.getAuntOssUrl + imgurlnew + newName);
            map.put("name", fileName);
            map.put("shortUrl", UploadUtil.getAuntOssUrl + imgurlnew + newName);
            map.put("realPath", imgurlnew + newName);

        } catch (OSSException oe) {
            return ResultBeanObj.fail("路径错误");
        } catch (ClientException ce) {
            return ResultBeanObj.fail("路径错误");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return ResultBeanObj.success().setData(map);
    }
    public static void deleteFile( String fileUrl, String bucketName) throws IOException {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            String key  = fileUrl.substring(UploadUtil.getAuntOssUrl.length()+1);
            ossClient.deleteObject(bucketName,key);
        }catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }finally {
            ossClient.shutdown();
        }
    }

    //    public static Map uploadImage2(MultipartFile[] files, HttpServletRequest request, String type, String bucketName) throws IOException {
    public static Map uploadImage2(HttpServletRequest request, String type, String bucketName) throws IOException {
        Map map = new HashMap();
        List list = new ArrayList();
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            Integer fileCount = 0;
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                String fileName = file.getOriginalFilename();
                log.info("文件名" + fileName);
                //文件扩展名
                String extName = fileName.substring(fileName.lastIndexOf("."));
                String newName = UUID.randomUUID().toString() + extName;
                Date nowDate = new Date();
                String year = StringUtils.dateToString(nowDate, "yyyy");
                String month = StringUtils.dateToString(nowDate, "MM");
                String imgurlnew = "/" + year + "/" + month + "/";
                String imgurlnews = year + "/" + month + "/";
                String key = createFolder(ossClient, bucketName, imgurlnews);
                // 转存文件
                String filePath = imgurlnews + newName;
                InputStream inputStream = file.getInputStream();
                ossClient.putObject(new PutObjectRequest(bucketName, imgurlnews + newName, inputStream));
                //无法判断getAuntOssUrl的值 先注释
                log.info("图片地址" + UploadUtil.getAuntOssUrl + imgurlnew + newName);
                list.add(UploadUtil.getAuntOssUrl + imgurlnew + newName);
            }
            map.put("errno", 0);
            map.put("data", list);
        } catch (OSSException oe) {
            log.info("异常-------------" + oe.getMessage());
            map.put("errno", 1);
            return map;
        } catch (ClientException ce) {
            log.info("异常2-------------" + ce.getMessage());
            map.put("errno", 1);
            return map;
        } catch (Throwable e) {
            log.info("异常3-------------" + e.getMessage());
            e.printStackTrace();
            map.put("errno", 1);
            return map;
        } finally {
            ossClient.shutdown();
        }
        return map;
    }


    public static void sendImage(File file, String Osspath, String fileName, String bucketName) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            // 获取指定文件的输入流
            InputStream inputStream = new FileInputStream(file);
            //String Osspathes=createFolder(ossClient,bucketName,Osspath);
            ossClient.putObject(new PutObjectRequest(bucketName, Osspath + "/" + fileName, inputStream));
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }


    public static ResultBeanObj deleteObject(String key) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        JSONObject jsonObject = JSONObject.parseObject(key);
        String imgUrl = jsonObject.getString("imgurl");
        imgUrl = imgUrl.substring(1, imgUrl.length());//去除图片的“/”
        ossClient.deleteObject(bucketName, imgUrl);
        return ResultBeanObj.success();

    }

    public static ResultBeanObj deleteObject(String bucketName, String key) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String imgUrl = key.substring(1, key.length());//去除图片的“/”
        ossClient.deleteObject(bucketName, imgUrl);
        return ResultBeanObj.success();

    }

    /**
     * 创建存储空间
     *
     * @param ossClient  OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public static String createBucketName(OSSClient ossClient, String bucketName) {
        //存储空间
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            //创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            System.out.println("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 创建模拟文件夹
     *
     * @param ossClient  oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名如"qj_nanjing/"
     * @return 文件夹名
     */
    public static String createFolder(OSSClient ossClient, String bucketName, String folder) {
        //文件夹名
        final String keySuffixWithSlash = folder;
        //判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            //创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            System.out.println("创建文件夹成功");
            //得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    public static String uploadInputStream(InputStream inputStream) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //创建文件名
        String newName = UUID.randomUUID().toString() + ".png";
        Date nowDate = new Date();
        String year = StringUtils.dateToString(nowDate, "yyyy");
        String month = StringUtils.dateToString(nowDate, "MM");
        String imgurlnew = "/" + year + "/" + month + "/";
        String imgurlnews = year + "/" + month + "/";
        String key = createFolder(ossClient, bucketName, imgurlnews);
        // 上传文件流。
        ossClient.putObject(bucketName, imgurlnews + newName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        String filePath = imgurlnews + newName;
        return filePath;
    }
}
