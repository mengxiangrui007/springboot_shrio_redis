package com.abroad.common.tools;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
/**
 * 哈希缓存方式util
 * @author shichangjian
 *
 */
@Component
public class RedisCacheBean {
	
	 @Resource  
     private JedisUtil jedisUtil;  

     /** 
      * 把对象放入Hash中 
      */  
     public void hset(String key,String field,Object o){  
         Jedis jedis =jedisUtil.getJedis();  
         jedis.hset(key,field, JSON.toJSONString(o));  
         jedisUtil.returnResource(jedis);  
     }  
     /** 
      * 从Hash中获取对象 
      */  
     public String hget(String key,String field){  
    	 Jedis jedis =jedisUtil.getJedis();  
         String text=jedis.hget(key,field);  
         jedisUtil.returnResource(jedis);  
         return text;  
     }  
     /** 
      * 从Hash中获取对象,转换成制定类型 
      */  
     public <T> T hget(String key,String field,Class<T> clazz){  
         String text=hget(key, field);  
         T result=JSON.parseObject(text, clazz);  
         return result;  
     }  
     /** 
      * 从Hash中删除对象 
      */  
     public void hdel(String key,String ... field){  
    	 Jedis jedis =jedisUtil.getJedis();  
         Object result=jedis.hdel(key,field);  
         jedisUtil.returnResource(jedis);  
     }
}
