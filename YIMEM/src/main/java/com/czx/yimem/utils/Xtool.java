package com.czx.yimem.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.*;

/**
 * wwww.yimem.com
 * @author CZX
 * @version 1.0
 * @date 2020/11/12 0012 15:31
 */
@Slf4j
public class Xtool {
    public static boolean isNotNull (String sstring) {
        boolean bolret = true;
        if (isNull(sstring))
            bolret = false;
        return bolret;
    }

    public static boolean isNotNull (int iint) {
        boolean bolret = true;
        String str=iint+"";
        if("".equals(str)||"null".equals(str)) {
            bolret = false;
        }
        return bolret;
    }

    public static boolean isNotNull(Integer iint){
        boolean bolret = true;
        if (iint == null)
            bolret = false;
        return bolret;
    }

    public static boolean isNotNull (Long iint) {
        boolean bolret = true;
        boolean flag = isNull(iint+"") || iint.intValue() == 0;
        if (flag){
            bolret = false;
        }
        return bolret;
    }

    public static boolean isNull (Integer iint) {
        boolean ret = false;
        if (iint == null) {
            ret = true;
        }

        return ret;
    }

    public static boolean isNull (Object obj) {
        boolean ret = false;
        if (obj == null||obj == ""||obj == "null") {
            ret = true;
        }

        return ret;
    }

    public static boolean isNull (String sstring) {
        boolean bolret = false;
        if ((sstring == null) || (sstring.trim().equals("null")) ||
                (sstring.trim().equals("")))
            bolret = true;
        return bolret;
    }

    public static boolean isNull(List lst)
    {
        boolean bolret = false;
        if (lst == null)
            bolret = true;
        if ((lst != null) &&
                (lst.size() == 0))
            bolret = true;
        return bolret;
    }

    public static String isStrNotNull(String str)
    {
        if ((str == null) || ("null".equals(str)) ||
                ("".equals(str.trim())))
            return "";
        return str.trim();
    }

    public static boolean isNotNull (List lst) {
        boolean bolret = false;
        if ((lst != null) &&  (lst.size() > 0))
            bolret = true;
        return bolret;
    }

    public static boolean isNull (int iint) {
        boolean bolret = false;
        String str=iint+"";
        if("".equals(str)||"null".equals(str)){
            bolret=true;
        }
        return bolret;
    }

    /**
     * ????????????????????????????????????
     * @param datas ?????????
     * @param key ??????????????????????????????????????????map???????????????key??????????????????????????????????????????
     * @param className ???????????????????????????????????????map????????????????????????????????????????????????????????????????????????????????????com.inspur.resource.cm1.circuit.pojo.TResAttempCircuit
     * @return Object[]  ?????????2??????????????? [keyList,dataMap] keyList ????????????????????????????????????????????????????????????&~~&???????????????dataMap ??????????????????key??????map??????
     * */
    public static Object[] groupingDatas(List datas,String key,String className){
        Object[] groupObjects = new Object[2];
        List<String>  keyList = new LinkedList<String>();
        Map<String,List<?>> dataMap = new HashMap<String, List<?>>();
        groupObjects[0] = keyList;
        groupObjects[1] = dataMap;
        try {
            if (null != datas && !datas.isEmpty()) {
                for (int i = 0; i < datas.size(); i++) {
                    Object oneData = datas.get(i);
                    String value = "";
                    if (isNull(className)) {
                        value = ((Map) oneData).get(key)+"";
                    } else {
                        try {
                            Class c = Class.forName(className);
                            String methedname = "get"+(key.charAt(0)+"").toUpperCase()+key.substring(1);
                            Method getMethod = c.getMethod(methedname, null);
                            value = getMethod.invoke(oneData, null)+"";
                        } catch (Exception e) {
                            log.error("invoke??????",e);
                        }
                    }
                    if (isNull(value)) {
                        value = "&~~&";
                    }
                    if (!keyList.contains(value)) {
                        keyList.add(value);
                        List groupList = new ArrayList();
                        groupList.add(oneData);
                        dataMap.put(value, groupList);
                    } else {
                        List groupList  = dataMap.get(value);
                        groupList.add(oneData);
                    }
                }
            }
            System.out.println(keyList.toString());
        } catch (RuntimeException e) {
            log.error("groupingDatas??????",e);
        }
        return groupObjects;
    }

}
