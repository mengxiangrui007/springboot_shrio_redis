package com.abroad.common.exception;

import com.abroad.common.tools.JuntaiEnvironment;
import com.alibaba.fastjson.JSON;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
* @ClassName: LogInterceptor
* @Description: TODO(获取请求Header，将错误信息输入 日志)
* @author: shicj
* @date 2017年4月18日 上午10:15:31
*/
public class LogInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static String getRequest(HttpServletRequest httpServletRequest) throws IOException {
        //请求日志
        Map<String, Object> requestLogMap = new HashMap<>();
        requestLogMap.put("remote", JuntaiEnvironment.getRemoteAddr());
        //HTTP Header
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

        Map<String, Object> headerMap = new HashMap<>();
        String headerName;
        while (headerNames.hasMoreElements()) {
            headerName = headerNames.nextElement();
            headerMap.put(headerName, httpServletRequest.getHeader(headerName));
        }

        if (!headerMap.isEmpty()) {
            requestLogMap.put("header", headerMap);
        }

        requestLogMap.put("queryString", httpServletRequest.getQueryString());

        if (httpServletRequest instanceof ContentCachingRequestWrapper) {
            ContentCachingRequestWrapper wrapper =
                    WebUtils.getNativeRequest(httpServletRequest, ContentCachingRequestWrapper.class);
            requestLogMap.put("body", new String(wrapper.getContentAsByteArray()));
        }

        return JSON.toJSONString(requestLogMap);
    }
}
