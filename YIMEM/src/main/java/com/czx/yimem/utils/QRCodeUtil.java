package com.czx.yimem.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
//import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;
import java.util.Random;

/**
 * 二维码工具类
 * Created by fuli.shen on 2017/3/31.
 */
public class QRCodeUtil {

//    private static final Logger LOG = Logger.getLogger(QRCodeUtil.class);

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int WIDTH = 60;
    // LOGO高度
    private static final int HEIGHT = 60;

    private static int basewidth=500;
    private static int baseheight=500;

    /**
     * 生成二维码的方法
     *
     * @param content      目标URL
     * @param imgPath      LOGO图片地址
     * @param needCompress 是否压缩LOGO
     * @return 二维码图片
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String imgPath,
                                             boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    /**
     * 插入LOGO
     *
     * @param source       二维码图片
     * @param imgPath      LOGO图片地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String imgPath,
                                    boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content      内容
     * @param imgPath      LOGO地址
     * @param destPath     存放目录
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath,
                              boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath,
                needCompress);
        mkdirs(destPath);
        String file = new Random().nextInt(99999999) + ".jpg";
        ImageIO.write(image, FORMAT_NAME, new File(destPath + "/" + file));
    }

    /**
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
     *
     * @param destPath 存放目录
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content  内容
     * @param imgPath  LOGO地址
     * @param destPath 存储地址
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath)
            throws Exception {
        QRCodeUtil.encode(content, imgPath, destPath, false);
    }

    /**
     * 生成二维码
     *
     * @param content      内容
     * @param destPath     存储地址
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String destPath,
                              boolean needCompress) throws Exception {
        QRCodeUtil.encode(content, null, destPath, needCompress);
    }

    /**
     * 生成二维码
     *
     * @param content  内容
     * @param destPath 存储地址
     * @throws Exception
     */
    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtil.encode(content, null, destPath, false);
    }

    /**
     * 生成二维码(内嵌LOGO)
     *
     * @param content      内容
     * @param imgPath      LOGO地址
     * @param output       输出流
     * @param needCompress 是否压缩LOGO
     * @throws Exception
     */
    public static void encode(String content, String imgPath,
                              OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath,
                needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 生成二维码
     *
     * @param content 内容
     * @param output  输出流
     * @throws Exception
     */
    public static void encode(String content, OutputStream output)
            throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }

    /**
     * 解析二维码
     *
     * @param file 二维码图片
     * @return
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
                image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * 解析二维码
     *
     * @param path 二维码图片地址
     * @return 不是二维码的内容返回null, 是二维码直接返回识别的结果
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }

    public static String encode(String content)  {
        try{
            BufferedImage image = QRCodeUtil.createImage(content, null,
                    false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
            ImageIO.write(image, "png", baos);//写入流中
            byte[] bytes = baos.toByteArray();//转换成字节
            BASE64Encoder encoder = new BASE64Encoder();
            String png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
            return "data:image/png;base64,"+png_base64;
        }catch (Exception e){
            return "";
        }


    }

    public static void main(String[] args) {
        createQrcode("http://www.yimem.com/details.html?blog_id=123","D:\\",200,200);
    }

    /**
     * 生成二维码
     * @param qcordUrl
     * @param filepath
     * @param width
     * @param height
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void createQrcode(String qcordUrl,String filepath,int width,int height){
        try{
            String format = "png";
            Hashtable hints= new Hashtable();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 0); //设置二维码空白边框的大小 1-4，1是最小 4是默认的国标
            BitMatrix bitMatrix =null;
            if(width>0&&height>0){
                bitMatrix  = new MultiFormatWriter().encode(qcordUrl, BarcodeFormat.QR_CODE, width, height,hints);
                //bitMatrix=QRCodeWriter.encode(param,width, height,hints);
            }else{
                bitMatrix  = new MultiFormatWriter().encode(qcordUrl, BarcodeFormat.QR_CODE, basewidth, baseheight,hints);
            }
//            File outputFile = new File(filepath);
            File outputFile = new File(filepath,"CC.png");
//            File file = new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
            File filePath = new File(filepath);
            if (filePath.exists()) {
                if (filePath.isDirectory()) {
                    System.out.println("dir exists");
                } else {
                    System.out.println("the same name file exists, can not create dir");
                }
            } else {
                System.out.println("dir not exists, create it ...");
                filePath.mkdir();
            }
            MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 将二进制转换成文件保存
     * @param instreams 二进制流
     * @param imgPath 图片的保存路径
     * @param imgName 图片的名称
     * @return
     *      1：保存正常
     *      0：保存失败
     */
    public static int saveToImgByInputStream(InputStream instreams, String imgPath, String imgName){
        int stateInt = 1;
        if(instreams != null){
            try {
                File file = new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
                File filePath = new File(imgPath);
                if (filePath.exists()) {
                    if (filePath.isDirectory()) {
                        System.out.println("dir exists");
                    } else {
                        System.out.println("the same name file exists, can not create dir");
                    }
                } else {
                    System.out.println("dir not exists, create it ...");
                    filePath.mkdir();
                }
                FileOutputStream fos=new FileOutputStream(file);
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;//1 成功，0 失败
    }

    public static boolean createQrcodeWithLogo(String qcordUrl,String lastfile,int width,int height,String headpath,String bgfilepathURL,String dir,int x,int y,String membername,String number,String headimg){
//        createQrcode(qcordUrl,lastfile,width,height);
//        if(headpath!=null){
//            String downfilename=String.valueOf(System.currentTimeMillis());
//            String downheadimg=dir+"/"+downfilename+".png";
//            ImageDown.saveToFile(headpath,downheadimg);
//            String compressname=dir+"/"+downfilename+"_compress.png";
//            ImgCompress.compressImg(downheadimg, compressname);
//            File f1=new File(bgfilepathURL);//背景图片
//            File f2=new File(lastfile);//合成图片
//            File f3=new File(compressname);
//            File f4=new File(downheadimg);
//            try {
//                mergeImage(f1,f2,f3,lastfile);//filepath 最后合成图片
//                f3.delete();
//                f4.delete();
//                return true;
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return false;
//            }
//        }
//        else
//        {
//            File f1=new File(bgfilepathURL);
//            File f2=new File(lastfile);
//
//            try {
//                mergeImageTwo(f1,f2,lastfile,x,y,width,height, membername, number, headimg);
//                return true;
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return false;
//            }
//        }
        return true;
    }


    public static void mergeImage(File file1, File file2,File file3,String path) throws IOException {
        BufferedImage image1 = ImageIO.read(file1);
        BufferedImage image2 = ImageIO.read(file2);
        BufferedImage image3 = ImageIO.read(file3);
        //System.out.println("====image1=="+image1.getWidth()+"========="+image1.getHeight());
        BufferedImage combined = new BufferedImage(image1.getWidth() , image1.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = combined.getGraphics();
        g.drawImage(image1, 0, 0, null);
        g.drawImage(image2, 175, 446, null);
        g.drawImage(image3,345,616,null);
        //g.setFont(new Font("宋体",Font.BOLD,50));
        //g.setColor(Color.yellow);
        //g.drawString("长按识别二维码",180 ,1150);
        // Save as new image
        ImageIO.write(combined, "jpg", new File(path));
    }

    public static void mergeImageTwo(File file1, File file2,String path,int x,int y ,int width,int height,String membername,String number,String headimg) throws IOException {
        BufferedImage image1 = ImageIO.read(file1);
        BufferedImage image2 = ImageIO.read(file2);

        BufferedImage combined = new BufferedImage(image1.getWidth() , image1.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = combined.getGraphics();
        g.drawImage(image1, 0, 0, null);
        g.drawImage(image2, x, y,width,height, null);
        if (!StringUtil.isNullObject(headimg)) {
            File f3=new File(headimg);
            BufferedImage image3 = ImageIO.read(f3);
            g.drawImage(image3, 40, 1200,80,80, null);
        }
        g.setColor(Color.WHITE);
        Font f = new Font("宋体", Font.PLAIN, 30);
        Color mycolor = Color.WHITE;//new Color(0, 0, 255);
        g.setColor(mycolor);
        g.setFont(f);
        //最后一个参数用来设置字体的大小
        if (!StringUtil.isNullObject(membername)) {

            g.drawString(membername, 150, 1240); //表示这段文字在图片上的位置(cx,cy) .第一个是你设置的内容。
        }
/*        if (!StringUtil.isNullObject(number)) {
            g.drawString(number, 100, 1080); //表示这段文字在图片上的位置(cx,cy) .第一个是你设置的内容。
        }*/
        g.dispose();
        ImageIO.write(combined, "jpg", new File(path));


//        LOG.info("==========昵称："+membername);
//        LOG.info("==========描述："+number);
    }

}
