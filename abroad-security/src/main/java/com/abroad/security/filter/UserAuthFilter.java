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

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		return super.isAccessAllowed(request, response, mappedValue);
	}

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
