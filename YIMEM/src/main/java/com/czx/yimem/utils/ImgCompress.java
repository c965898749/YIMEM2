//package com.aixin.jhrc.utils;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Date;
//
//public class ImgCompress {
//	private Image img;
//    private int width;
//    private int height;
//    @SuppressWarnings("deprecation")
//    public static void main(String[] args) throws Exception {
//        System.out.println("开始：" + new Date().toLocaleString());
//        ImgCompress imgCom = new ImgCompress("D:/head.jpg","D:/head_compress.png");
//        imgCom.resizeFix(200, 200);
//        System.out.println("结束：" + new Date().toLocaleString());
//    }
//
//    public static boolean compressImg(String srcFilePath,String destFilePath){
//    	 ImgCompress imgCom;
//		try {
//			imgCom = new ImgCompress(srcFilePath,destFilePath);
//			imgCom.resizeFix(60, 60);
//			return true;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//    }
//    /**
//     * 构造函数
//     */
//    public ImgCompress(String fileName, String destpath) throws IOException {
//        File file = new File(fileName);// 读入文件
//        img = ImageIO.read(file);      // 构造Image对象
//        width = img.getWidth(null);    // 得到源图宽
//        height = img.getHeight(null);  // 得到源图长
//        this.destpath=destpath;
//    }
//    /**
//     * 按照宽度还是高度进行压缩
//     * @param w int 最大宽度
//     * @param h int 最大高度
//     */
//    public void resizeFix(int w, int h) throws IOException {
//        if (width / height > w / h) {
//            resizeByWidth(w);
//        } else {
//            resizeByHeight(h);
//        }
//    }
//    /**
//     * 以宽度为基准，等比例放缩图片
//     * @param w int 新宽度
//     */
//    public void resizeByWidth(int w) throws IOException {
//        int h = (int) (height * w / width);
//        resize(w, h);
//    }
//    /**
//     * 以高度为基准，等比例缩放图片
//     * @param h int 新高度
//     */
//    public void resizeByHeight(int h) throws IOException {
//        int w = (int) (width * h / height);
//        resize(w, h);
//    }
//    private String destpath;
//
//    public String getDestpath() {
//		return destpath;
//	}
//
//	public void setDestpath(String destpath) {
//		this.destpath = destpath;
//	}
//
//	/**
//     * 强制压缩/放大图片到固定的大小
//     * @param w int 新宽度
//     * @param h int 新高度
//     */
//    public void resize(int w, int h) throws IOException {
//        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
//        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );
//        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
//        File destFile = new File(this.getDestpath());
//        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
//        // 可以正常实现bmp、png、gif转jpg
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//        encoder.encode(image); // JPEG编码
//        out.close();
//    }
//}
