package com.abroad.security.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abroad.security.filter.AccessFilter;

/**
 * 
* @ClassName: AccessConfigue
* @Description: TODO(访问控制)
* @author: mengxr
* @date 2017年3月29日 下午5:58:39
*/
//@Configuration
public class AccessConfigue {
    /**
     * 由于shrio框架在每次请求的时候都要向缓存查询当前的Session效率十分低，并且shrio框架是基于过滤器实现的，
     * 所以在进入shrioFilter前先对访问的数据进行过滤，如果不存在的换直接跳转到404状态
    * @Title: accessFilterRegistration
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @return    设定文件
    * @return FilterRegistrationBean    返回类型
    * @author: mengxr
    * @date 2017年3月29日 下午6:03:17
    * @throws
    */
//    @Bean
    public FilterRegistrationBean accessFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        AccessFilter accessFilter = new AccessFilter();
        registration.setFilter(accessFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(Integer.MAX_VALUE); //拦截器优先级最高
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("accessFilter");
        return registration;
    }
}
