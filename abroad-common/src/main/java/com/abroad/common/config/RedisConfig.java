package com.abroad.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;  
import com.fasterxml.jackson.annotation.PropertyAccessor;  
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;  
import org.springframework.cache.annotation.CachingConfigurerSupport;  
import org.springframework.cache.annotation.EnableCaching;  
import org.springframework.cache.interceptor.KeyGenerator;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.data.redis.cache.RedisCacheManager;  
import org.springframework.data.redis.connection.RedisConnectionFactory;  
import org.springframework.data.redis.core.RedisTemplate;  
import org.springframework.data.redis.core.StringRedisTemplate;  
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;  

import java.lang.reflect.Method;  
  
  
/**
* @ClassName: RedisConfig
* @Description: TODO(Redis配置)
* @author: mengxr
* @date 2017年3月28日 下午6:24:33
*/
@Configuration  
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

//    @Value("${spring.redis.password}")
//    private String password;
	
	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};

	}

	@Bean
	public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}

	/**
	* @Title: redisTemplate
	* @Description: TODO(redis模板配置)
	* @param @param factory
	* @param @return    设定文件
	* @return RedisTemplate<?,?>    返回类型
	* @author: shicj
	* @date 2017年4月17日 上午10:40:45
	* @throws
	*/
	@Bean
	public RedisTemplate<?,?> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
        //RedisTemplate序列化类设置
        //json序列化
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
//		这是设置对value的序列化类的设置，当value的类型不是string时，就会用他做序列化
		template.setValueSerializer(jackson2JsonRedisSerializer);
        //hash的value的序列化类
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
    @Bean  
    public JedisPoolConfig getRedisConfig(){  
        JedisPoolConfig config = new JedisPoolConfig();  
        return config;  
    }
    /**
     * JedisPool实例化对象
     * @return
     */
    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = getRedisConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        return jedisPool;
    }
}
