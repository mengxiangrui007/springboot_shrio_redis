package com.abroad.security.shiro;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionContext;

/**
* @ClassName: ShiroSessionFactory
* @Description: TODO(创建一个ShiroSession工程)
* @author: mengxr
* @date 2017年3月28日 下午3:05:04
*/
public class ShiroSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext initData) {
        ShiroSession session = new ShiroSession();
        HttpServletRequest request = (HttpServletRequest)initData.get(DefaultWebSessionContext.class.getName() + ".SERVLET_REQUEST");
        session.setHost(getIpAddress(request));
        return session;
    }

    /**
    * @Title: getIpAddress
    * @Description: TODO(获取IP地址)
    * @param @param request
    * @param @return    设定文件
    * @return String    返回类型
    * @author: mengxr
    * @date 2017年3月28日 下午3:04:45
    * @throws
    */
    public static String getIpAddress(HttpServletRequest request) {
        String localIP = "127.0.0.1";
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
