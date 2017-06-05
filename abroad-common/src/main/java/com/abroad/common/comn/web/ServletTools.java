package com.abroad.common.comn.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.abroad.common.comn.ResponseJson;
import com.alibaba.fastjson.JSON;

/**
 * @ClassName: Servlet
 * @Description: TODO(Http Servlet工具类)
 * @author: mengxr
 * @date 2017年3月29日 下午2:55:01
 */
public class ServletTools {
	/**
	 * @Title: getRequest
	 * @Description: TODO(获取当前请求的Request对象)
	 * @param @return 设定文件
	 * @return HttpServletRequest 返回类型
	 * @author: mengxr
	 * @date 2017年3月29日 下午2:55:48
	 * @throws
	 */
	public static HttpServletRequest getRequest() {
		try {
			return ((ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes()).getRequest();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @Title: getContextPath
	 * @Description: TODO(获取访问的路径)
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author: mengxr
	 * @date 2017年3月29日 下午9:14:51
	 * @throws
	 */
	public static String getContextPath(HttpServletRequest request) {
		String uri = request.getServletPath();
		String pathInfo = request.getPathInfo();
		if (pathInfo != null && pathInfo.length() > 0) {
			uri = uri + pathInfo;
		}
		return uri;
	}

	/**
	 * @Title: sendResponse
	 * @Description: TODO(发送一个JSON数据)
	 * @param @param response
	 * @param @param responseJson
	 * @param @throws IOException 设定文件
	 * @return void 返回类型
	 * @author: mengxr
	 * @date 2017年3月29日 下午9:13:06
	 * @throws
	 */
	public static void sendResponse(HttpServletResponse response,
			ResponseJson responseJson) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.getWriter().write(JSON.toJSONString(responseJson));
	}
}
