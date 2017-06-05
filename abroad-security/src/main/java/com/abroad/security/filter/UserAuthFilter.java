package com.abroad.security.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.MediaType;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.comn.StatusCode;
import com.alibaba.fastjson.JSON;

/**
 * 权限filter
 * 
 * @author qupeng
 *
 */
public class UserAuthFilter extends UserFilter {

	
	/**
	* @Title: isAccessAllowed
	* @Description: TODO(第一个过滤器，验证是否登录，如果访问的是登录页面，
	* shiroFilterFactoryBean.setLoginUrl("/login");根据shiroConfig中此配置路径进行匹配，如果访问路径和此路径一样，将进入此路径，反之进入onaccessDenied()方法
	* 如果登录了，和过滤器中的路径匹配，true进入访问路径，false进入onaccessDenied()方法)
	* isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false进入onAccessDenied()方法；
	* @see org.apache.shiro.web.filter.authc.UserFilter#isAccessAllowed(javax.servlet.ServletRequest, javax.servlet.ServletResponse, java.lang.Object)
	*/
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		return super.isAccessAllowed(request, response, mappedValue);
	}

	/**
	* @Title: onAccessDenied
	* 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）
	* @Description: TODO(进入此方法表示不但没有登录，而且与shiroFilterFactoryBean.setLoginUrl("/login")此路径不匹配)
	* onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将中断过滤器，直接返回即可。
	* @see org.apache.shiro.web.filter.authc.UserFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	*/
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		ResponseJson json = new ResponseJson(StatusCode.NOT_LOGGED_ON);
		sendResponse((HttpServletResponse) response, json);
		// return super.onAccessDenied(request, response);
		return false;
	}

	private void sendResponse(HttpServletResponse response, ResponseJson responseJson) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.getWriter().write(JSON.toJSONString(responseJson));

	}

}
