package com.abroad.security.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.http.MediaType;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.comn.StatusCode;
import com.alibaba.fastjson.JSON;

/**
 * Created by barry on 2017/3/4.
 */
public class RolesAuthFilter extends RolesAuthorizationFilter {
    /**
    * @Title: onAccessDenied
    * @Description: TODO(doGetAuthorizationInfo()访问权限时，在doGetAuthorizationInfo()方法出问题时，进入onAccessDenied()方法)
    * @see org.apache.shiro.web.filter.authz.AuthorizationFilter#onAccessDenied(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
    */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        ResponseJson json ;
        if (subject.getPrincipal() == null) {
            json = new ResponseJson(StatusCode.NOT_LOGGED_ON);//未登录
        } else {
            json = new ResponseJson(StatusCode.NO_PERMISSION);//登录无权限
        }

        sendResponse((HttpServletResponse) response, json);
        // return super.onAccessDenied(request, response);
        return false;
    }

    private void sendResponse(HttpServletResponse response, ResponseJson responseJson) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSON.toJSONString(responseJson));
    }
}
