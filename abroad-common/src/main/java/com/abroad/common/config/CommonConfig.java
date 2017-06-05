package com.abroad.common.config;

import java.io.IOException;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
* @ClassName: CommonConfig
* @Description: TODO(通用配置类)
* @author: mengxr
* @date 2017年3月29日 上午11:50:43
*/
@Configuration
public class CommonConfig {
	/**
	 * 用于解决 Jackson Could not write JSON: No serializer found for class java.lang.Object
	* @Title: jacksonObjectMapper
	* @Description: TODO(把null变为"")
	* @param @return    设定文件
	* @return ObjectMapper    返回类型
	* @author: mengxr
	* @date 2017年3月29日 上午11:51:16
	* @throws
	*/
	@Bean(name="objectMapper")
	public ObjectMapper jacksonObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
					throws IOException, JsonProcessingException {
				// 所有null字段，重写为空字符串
				jg.writeString("");
				sp.getDefaultNullKeySerializer();
			}
		});
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		return objectMapper;
	}
	/**
	* @Title: messageSource
	* @Description: TODO(mvc框架中实现MessageSource来管理国际资源文件)
	* @param @return    设定文件
	* @return MessageSource    返回类型
	* @author: shicj
	* @date 2017年4月17日 上午11:23:15
	* @throws
	*/
	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource rb = new ReloadableResourceBundleMessageSource();
		rb.setBasenames("classpath:messages");//获取message.properties
		return rb;
		
	}
}
