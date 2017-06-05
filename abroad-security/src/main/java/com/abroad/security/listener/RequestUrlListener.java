package com.abroad.security.listener;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.abroad.security.cache.LocalCache;

/**
 * 当容器启动时获取当前容器的可请求地址，并把它放到缓存中
 * 
 * @ClassName: RequestURLListener
 * @Description: TODO(ApplicationListener:项目启动时需要加载或者执行一些特殊的任务来初始化系统)
 * @author: mengxr
 * @date 2017年3月29日 下午6:25:36
 */
@Component
public class RequestUrlListener implements
		ApplicationListener<ContextRefreshedEvent> {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {// root application context 没有parent，他就是老大.
			try {
				ApplicationContext applicationContext = event
						.getApplicationContext();
				RequestMappingHandlerMapping bean = applicationContext
						.getBean(RequestMappingHandlerMapping.class);
				Set<String> result = new HashSet<String>();
				Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean
						.getHandlerMethods();
				for (RequestMappingInfo rmi : handlerMethods.keySet()) {
					PatternsRequestCondition pc = rmi.getPatternsCondition();
					Set<String> pSet = pc.getPatterns();
					result.addAll(pSet);//获取spring容器请求路径url，把所有url放入result
				}
				LocalCache.ACCESS_URL.addAll(result);
				logger.info("-----------初始化RequestUrlListener成功 ");
			} catch (Exception e) {
				logger.error("-----------获取RequestUrlListener失败 ",e);
			}
		}
	}
}
