package com.abroad.hr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.comn.StatusCode;
import com.abroad.common.controller.BaseController;
import com.abroad.uc.domain.User;
import com.abroad.uc.service.IUserService;
import com.abroad.uc.vo.UserVO;

/**
* @ClassName: RegistetAction
* @Description: TODO(注册用户)
* @author: mengxr
* @date 2017年4月1日 上午10:56:59
*/
@RestController
@RequestMapping("/")
public class RegistetAction extends BaseController<User>{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IUserService iUserService;
	@RequestMapping(value = "/register")
	public ResponseJson register(@RequestParam String username, @RequestParam String password,
			@RequestParam String email,@RequestParam String mobile){
		try {
			UserVO user = new UserVO();
			user.setOpe(super.getInsertOpeInfo());
			user.setAccount(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setMobile(mobile);
			user.setIslocked((short)0); //没有锁定
			iUserService.insertUser(user);
			return new ResponseJson(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("注册账号异常{}用户名异常",username,e);
			return new ResponseJson(StatusCode.ERROR);
		}
	}
}
