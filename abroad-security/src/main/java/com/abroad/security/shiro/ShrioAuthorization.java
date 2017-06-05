package com.abroad.security.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.abroad.uc.service.IPermissionService;

/**
* @ClassName: ShrioAuthorization
* @Description: TODO(进行Shrio授权权限修改)
* @author: mengxr
* @date 2017年4月13日 下午2:38:26
*/
public class ShrioAuthorization {
	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;
	@Autowired
	private IPermissionService iPermissionService;
	//日志处理
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	* @Title: reloadFilterChains
	* @Description: TODO(重新获取权限)
	* @param @param mouduleCode    设定文件
	* @return void    返回类型
	* @author: mengxr
	* @date 2017年4月13日 下午3:01:30
	* @throws
	*/
	public void reloadFilterChains(String mouduleCode){
		 	synchronized (ShrioAuthorization.class) {
				try {
					AbstractShiroFilter shiroFilter = null;
					shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
							.getObject();
					PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter
							.getFilterChainResolver();
					// 过滤管理器
					DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver
							.getFilterChainManager();
					// 清除权限配置
					manager.getFilterChains().clear();
					shiroFilterFactoryBean.getFilterChainDefinitionMap()
							.clear();
					// 重新设置权限
					Map<String, String> chainDefinition = new HashMap<String, String>();
					chainDefinition.put("/**", "userAuth");
					Map<String, String> moduleChain = iPermissionService
							.queryMouduleChainDefinition(mouduleCode);//重新获取模板权限
					chainDefinition.putAll(moduleChain);
					shiroFilterFactoryBean
							.setFilterChainDefinitionMap(chainDefinition);
				} catch (Exception e) {
					logger.error("configue user permission error :", e);
				}
			} 
	}
}
