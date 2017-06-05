package com.abroad.common.config;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.abroad.common.filter.CorSFilter;

/**
* @ClassName: MyWebAppConfigurer
* @Description: TODO(Springmvc通用配置，WebMvcConfigurerAdapter：进行视图跳转，
* 加上@EnableWebMvc，进行视图解析器的配置，url前缀名和后缀名)
* @author: mengxr
* @date 2017年3月29日 下午6:07:07
*/
@Configuration
public class MyWebAppConfigurer 
        extends WebMvcConfigurerAdapter {
    /**
    * @Title: corFilterRegistration
    * @Description: TODO(跨域过滤器注入Bean)
    * @param @return    设定文件
    * @return FilterRegistrationBean    返回类型
    * @author: mengxr
    * @date 2017年4月1日 下午4:01:44
    * @throws
    */
    @Bean
    public FilterRegistrationBean corFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
//      CORS是为了让AJAX可以实现可控的跨域访问而生的
//      但是CORS也具有一定的风险性，比如请求中只能说明来自于一个特定的域但不能验证是否可信，而且也容易被第三方入侵
        CorSFilter accessFilter = new CorSFilter();
        registration.setFilter(accessFilter);
        registration.addUrlPatterns("/*");
        registration.setName("corFilter");
        return registration;
    }
}