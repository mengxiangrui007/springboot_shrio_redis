package com.abroad.school.conf;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.abroad.security.config.BaseShiroConfigue;
import com.abroad.uc.comm.ModuleType;
import com.abroad.uc.service.IPermissionService;

/**
 * @ClassName: SchoolShiroConfigue
 * @Description: TODO(学校权限配置)
 * @author: mengxr
 * @date 2017年4月10日 下午6:55:06
 */
@Configuration
public class SchoolShiroConfigue extends BaseShiroConfigue {
	@Autowired
	private IPermissionService iPermissionService;
	// 日志处理
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 加载权限
	 */
	protected void loadShiroFilterChain(
			ShiroFilterFactoryBean shiroFilterFactoryBean) {
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		try {
			Map<String, String> moduleChain = iPermissionService
					.queryMouduleChainDefinition(ModuleType.SchoolModule);
			filterChainDefinitionMap.putAll(moduleChain);
			logger.info("----------start SchoolModule module server success !");
		} catch (Exception e) {
			logger.error("start SchoolModule module server error :" + e);
		}
		shiroFilterFactoryBean
				.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}
}