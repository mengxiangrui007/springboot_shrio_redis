package com.abroad.common.tools;


import org.apache.log4j.Logger;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;  
  
  
import redis.clients.jedis.Jedis;  
import redis.clients.jedis.JedisPool;  
/**
 * 使用Jedis操作redis
 * @author shichangjian
 *
 */
@Component  
public class JedisUtil {  
      
    private static Logger logger = Logger.getLogger(JedisUtil.class);  
  
    @Autowired  
    private JedisPool jedisPool;  
      
//    public Jedis getResource() {  
//        return jedisPool.getResource();  
//    }  
  
    @SuppressWarnings("deprecation")  
    public void returnResource(Jedis jedis) {  
        if(jedis != null){  
            jedisPool.returnResourceObject(jedis);  
        }  
    }  
    /** 
     *类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 
     *没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。 
     */  
    private static class RedisUtilHolder{  
        /** 
         * 静态初始化器，由JVM来保证线程安全 
         */  
        private static JedisUtil instance = new JedisUtil();  
    }  
  
    /** 
     *当getInstance方法第一次被调用的时候，它第一次读取 
     *RedisUtilHolder.instance，导致RedisUtilHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静 
     *态域，从而创建RedisUtil的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。 
     *这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。 
     */  
    public static JedisUtil getInstance() {  
        return RedisUtilHolder.instance;  
    }  
      
    /** 
     * 获取Redis实例. 
     * @return Redis工具类实例 
     */  
    public Jedis getJedis() {  
        Jedis jedis  = null;  
            try{   
                jedis = jedisPool.getResource();  
            } catch (Exception e) {  
                logger.error("get redis master1 failed!", e);  
                 // 销毁对象    
                jedisPool.returnBrokenResource(jedis);    
            }  
        return jedis;  
    }  
  
    /** 
     * 释放redis实例到连接池. 
     * @param jedis redis实例 
     */  
    public void closeJedis(Jedis jedis) {  
        if(jedis != null) {  
        	jedisPool.returnResource(jedis);  
        }  
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    public void set(String key, String value) {  
//        Jedis jedis=null;  
//        try{  
//            jedis = getResource();  
//            jedis.set(key, value);  
//            logger.info("Redis set success - " + key + ", value:" + value);  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);  
//        }finally{  
//            returnResource(jedis);  
//        }  
//    }  
//      
//    public String get(String key) {  
//        String result = null;  
//        Jedis jedis=null;  
//        try{  
//            jedis = getResource();  
//            result = jedis.get(key);  
//            logger.info("Redis get success - " + key + ", value:" + result);  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);  
//        }finally{  
//            returnResource(jedis);  
//        }  
//        return result;  
//    }
}
