package com.abroad.common.intereptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.abroad.common.vo.ShiroUser;

/**
* @ClassName: UserSecurityInterceptor
* @Description: TODO(拦截登录)
* @author: shicj
* @date 2017年4月14日 下午3:02:45
*/
@Component
public class UserSecurityInterceptor implements HandlerInterceptor {

	@Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
         //验证用户是否登陆
        Object obj = request.getSession().getAttribute("Account");
        if (obj == null || !(obj instanceof ShiroUser)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
