package com.czx.yimem.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * RedisAPI
 */
@Component
public class RedisAPI {

	@Autowired
	public StringRedisTemplate redisTemplate;

	/**
	 * 存储字符串类型数据
	 * @param key
	 * @param value
	 * @return
	 */
	public  boolean set(String key,String value,Integer time){
		try{
			redisTemplate.opsForValue().set(key, value,time, TimeUnit.SECONDS);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public boolean exist(String key){
		try{
			return redisTemplate.hasKey(key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * 读取字符串类型数据
	 * @param key
	 * @return
	 */
	public String get(String key){
		String value = null;
		try{
			value = redisTemplate.opsForValue().get(key);

		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}

	public void del(String key){
		String value = null;
		try{
			redisTemplate.delete(key);

		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
