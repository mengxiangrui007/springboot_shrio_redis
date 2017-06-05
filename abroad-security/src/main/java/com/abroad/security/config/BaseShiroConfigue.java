package com.abroad.security.config;

import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.abroad.security.filter.RolesAuthFilter;
import com.abroad.security.filter.UserAuthFilter;

/**
 * @ClassName: ShiroConfiguration
 * @Description: TODO(Shiro权限框架配置类)
 * @author: mengxr
 * @date 2017年3月28日 下午3:50:49
 */
@Configuration
@ImportResource({"classpath:config/spring-shiro.xml"})
public abstract class BaseShiroConfigue {
    /**
    * @Title: hashedCredentialsMatcher
    * @Description: TODO(密码校验)
    * @param @return    设定文件
    * @return HashedCredentialsMatcher    返回类型
    * @author: mengxr
    * @date 2017年3月28日 下午5:07:16
    * @throws
    */
    @Bean(name="hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
       HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//      hashedCredentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
//      hashedCredentialsMatcher.setHashIterations(1024);
       hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
       hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
       return hashedCredentialsMatcher;
    }
    /**
    * @Title: rememberMeManager
    * @Description: TODO(记住密码功能放到Cookie中)
    * @param @return    设定文件
    * @return CookieRememberMeManager    返回类型
    * @author: mengxr
    * @date 2017年3月28日 下午5:08:04
    * @throws
    */
    @Bean(name="rememberMeCookie")
    public CookieRememberMeManager rememberMeManager(){
       CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
       SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
       //<!-- 记住我cookie生效时间30天 ,单位秒;-->
       simpleCookie.setMaxAge(259200);
       cookieRememberMeManager.setCookie(simpleCookie);
       return cookieRememberMeManager;
    }
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.getFilters().put("userAuth", new UserAuthFilter());
        shiroFilterFactoryBean.getFilters().put("roleAuth", new RolesAuthFilter());
 		loadShiroFilterChain(shiroFilterFactoryBean);
 		Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();
 		filterChainDefinitionMap.put("/**", "userAuth"); //由于Shrio路径是按照顺序拦截 的的，当前面的都没有拦截到执行这个拦截 
 		return shiroFilterFactoryBean;
	}
	/**
	 * 加载其他模板权限
	 */
	protected abstract void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean);
}
