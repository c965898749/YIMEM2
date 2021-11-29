package com.czx.yimem.utils;

/**
 * @author CZX
 * @version 1.0
 * @date 2020/11/16 0016 22:04
 */

import java.util.Random;

public class CreateRandomUtil {
    public static final String numberChar ="0123456789";

    /**
     * 根据系统时间获得指定位数的随机数
     *
     * @return 获得的随机数
     */
    public static String getRandom() {

        Long seed = System.currentTimeMillis();// 获得系统时间，作为生成随机数的种子

        StringBuffer sb = new StringBuffer();// 装载生成的随机数

        Random random = new Random(seed);// 调用种子生成随机数

        for (int i = 0; i < 16; i++) {

            sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }

        return sb.toString();

    }



    public static String getRandom(int w) {

        Long seed = System.currentTimeMillis();// 获得系统时间，作为生成随机数的种子

        StringBuffer sb = new StringBuffer();// 装载生成的随机数

        Random random = new Random(seed);// 调用种子生成随机数

        for (int i = 0; i < w; i++) {

            sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
        }

        return sb.toString();

    }

}
