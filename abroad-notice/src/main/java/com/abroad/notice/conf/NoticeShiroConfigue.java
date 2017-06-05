package com.abroad.notice.conf;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Configuration;

import com.abroad.security.config.BaseShiroConfigue;

/**
* @ClassName: NoticeShiroConfigue
* @author: mengxr
* @date 2017年4月10日 下午6:55:06
*/
@Configuration
public class NoticeShiroConfigue extends BaseShiroConfigue{
 	/**
	 * 加载权限
	 */
	protected void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		
		filterChainDefinitionMap.put("/aaa", "authc");
		filterChainDefinitionMap.put("/bbb", "userAuth,roleAuth[admin]");
		filterChainDefinitionMap.put("/static/**", "anon");
 		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}
}