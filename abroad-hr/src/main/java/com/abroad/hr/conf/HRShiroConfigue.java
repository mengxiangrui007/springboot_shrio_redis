package com.abroad.hr.conf;

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
 * @ClassName: HRShiroConfigue
 * @Description: TODO(用户权限配置)
 * @author: mengxr
 * @date 2017年4月10日 下午6:55:06
 */
@Configuration
public class HRShiroConfigue extends BaseShiroConfigue {
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
//			authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问，user:配置记住我或认证通过可以访问
			filterChainDefinitionMap.put("/aa.html", "anon");
			Map<String, String> moduleChain = iPermissionService
					.queryMouduleChainDefinition(ModuleType.HRModule);
			filterChainDefinitionMap.putAll(moduleChain);
			logger.info("----------start HR module server success !");
		} catch (Exception e) {
			logger.error("start HR module server error :" + e);
		}
		shiroFilterFactoryBean
				.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}
}