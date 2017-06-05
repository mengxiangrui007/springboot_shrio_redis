package com.abroad.notice.controller;

import java.io.Serializable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.controller.BaseController;
import com.abroad.security.shiro.AbroadSession;
import com.abroad.uc.vo.UserVO;

/**
* @ClassName: UserController
* @Description: TODO(用户交互接口)
* @author: mengxr
* @date 2017年3月25日 下午3:26:46
*/
@RestController
@RequestMapping("/")
public class AAAController extends BaseController<UserVO>{
	@RequestMapping(value = "/bbb",method = RequestMethod.GET)
   //@RequiresPermissions("userAuth")  
	public ResponseJson test(){
		boolean[] permitted = SecurityUtils.getSubject().isPermitted();
		ResponseJson responseJson = new ResponseJson();
		 Subject subject = SecurityUtils.getSubject();
		 subject.hasRole("admin");
		AbroadSession abroadSession = new AbroadSession();
		//ShiroUser loginUserInfo = abroadSession.getLoginUserInfo();
		//String account = loginUserInfo.getAccount();
		Session session = abroadSession.getSession();
		Serializable id = session.getId();
		System.out.println("---------------------2--------------"+id);
		return responseJson;
	}
}
