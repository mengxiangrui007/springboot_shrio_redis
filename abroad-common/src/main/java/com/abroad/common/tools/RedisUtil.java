package com.abroad.common.tools;


import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
* @ClassName: RedisUtil
* @Description: TODO(Redis操作工具类)
* @author: mengxr
* @date 2017年3月28日 上午11:56:02
*/
@Component
public class RedisUtil {
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 根据key删除
     * @param key
     */
    public void remove(String key){
        if(this.isExistKey(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * 根据key批量删除
     * @param keys
     */
    public void remove(String... keys){
        for(String key : keys){
            this.remove(key);
        }
    }

    /**
     * 根据key判断是否存在对应的value
     * @param key
     * @return
     */
    public boolean isExistKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public Object get(String key){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value){
        try {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean set(String key, Object value, Long expireTime){
        try {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}