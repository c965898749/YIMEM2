package com.czx.yimem.utils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDown {
	public static void main(String[] args) throws Exception {
        String str = "http://audio.initwords.com/Longman_Am_sound_lib/B/bee.mp3";
        ImageDown.saveToFile(str,"D:/bee.mp3");
    }
	 public static  void saveToFile(String destUrl,String filePath) {
	        FileOutputStream fos = null;
	        BufferedInputStream bis = null;
	        HttpURLConnection httpUrl = null;
	        URL url = null;
	        int BUFFER_SIZE = 1024;
	        byte[] buf = new byte[BUFFER_SIZE];
	        int size = 0;
	        try {
	            url = new URL(destUrl);
	            httpUrl = (HttpURLConnection) url.openConnection();
	            httpUrl.connect();
	            bis = new BufferedInputStream(httpUrl.getInputStream());
	            fos = new FileOutputStream(filePath);
	            while ((size = bis.read(buf)) != -1) {
	                fos.write(buf, 0, size);
	            }
	            fos.flush();
	        } catch (IOException e) {
	        } catch (ClassCastException e) {
	        } finally {
	            try {
	                fos.close();
	                bis.close();
	                httpUrl.disconnect();
	            } catch (IOException e) {
	            } catch (NullPointerException e) {
	            }
	        }
	    }

}
