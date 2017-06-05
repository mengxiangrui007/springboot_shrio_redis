package com.abroad.security.tools;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.codec.Base64;

import com.google.common.collect.Lists;
  
/**
* @ClassName: SerializeUtils
* @Description: TODO(序列化工具集)
* @author: mengxr
* @date 2017年3月28日 下午2:14:14
*/
public class SerializeUtils extends SerializationUtils {  
  
    public static String serializeToString(Serializable obj) {  
        try {  
            byte[] value = serialize(obj);  
            return Base64.encodeToString(value);  
        } catch (Exception e) {  
            throw new RuntimeException("serialize session error", e);  
        }  
    }  
  
    public static <T> T deserializeFromString(String base64) {  
        try {  
            byte[] objectData = Base64.decode(base64);  
            return deserialize(objectData);  
        } catch (Exception e) {  
            throw new RuntimeException("deserialize session error", e);  
        }  
    }  
  
    public static <T> Collection<T> deserializeFromStringController(Collection<String> base64s) {  
        try {  
            List<T> list = Lists.newLinkedList();  
            for (String base64 : base64s) {  
                byte[] objectData = Base64.decode(base64);  
                T t = deserialize(objectData);  
                list.add(t);  
            }  
            return list;  
        } catch (Exception e) {  
            throw new RuntimeException("deserialize session error", e);  
        }  
    }  
}  