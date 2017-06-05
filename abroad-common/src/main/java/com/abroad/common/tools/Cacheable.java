package com.abroad.common.tools;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 哈希缓存自定义注解类
 * @author shichangjian
 *
 */
@Target({ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {
    String key();  
    String fieldKey() ;  
    int expireTime() default 3600;
}

