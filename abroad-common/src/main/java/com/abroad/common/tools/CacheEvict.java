package com.abroad.common.tools;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 哈希缓存自定义注解类，删除缓存
 * @author shichangjian
 *
 */
@Target({ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheEvict {
    String key();  
    String fieldKey() ;  
    int expireTime() default 3600;
}
