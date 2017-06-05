package com.abroad;

import org.springframework.boot.SpringApplication;

/**
* @ClassName: SecurityApplication
* @Description: TODO(权限管理框架)
* @author: mengxr
* @date 2017年3月23日 上午10:52:13
*/
//@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
    	SpringApplication springApplication = new SpringApplication(SecurityApplication.class);
    	//springApplication.addListeners(new RequestUrlListener()); //添加一个启动后监听
    	springApplication.run(args);
    }
}
