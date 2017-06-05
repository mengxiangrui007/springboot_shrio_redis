package com.abroad.hr.controller;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.comn.StatusCode;
import com.abroad.security.shiro.AbroadSession;
import com.abroad.security.tools.Constants;

/**
 * @ClassName: LoginController
 * @Description: TODO(登录处理)
 * @author: mengxr
 * @date 2017年3月27日 下午8:33:17
 */
@RestController
@RequestMapping("/")
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * @Title: login
	 * @Description: TODO(用户登录)
	 * @param @param username
	 * @param @param password
	 * @param @param remember
	 * @param @return 设定文件
	 * @return ResponseJson 返回类型
	 * @author: mengxr
	 * @date 2017年3月27日 下午9:17:00
	 * @throws
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseJson login(@RequestParam String username,
			@RequestParam String password, @RequestParam boolean remember) {
		remember = false;
		if (username == null || password == null || "".equals(username)
				|| "".equals(password)) {
			logger.error("/login error username or password  null");
			return new ResponseJson(StatusCode.LOGIN_ERROR_NOT_NULL);
		}
		AbroadSession session = new AbroadSession();
		try {
			session.login(username, password, null, remember);
			session.getSession().setTimeout(Constants.SESSION_EXPIRE_TIME);
		} catch (AuthenticationException e) {
			if (e instanceof UnknownAccountException) {
				// 账户不存在
				logger.warn("---------------login warn ------{}账户不存在-------",
						username);
				return new ResponseJson(StatusCode.LOGIN_ACCOUNT_NOT_EXIST);
			} else if (e instanceof IncorrectCredentialsException) {
				// 密码不正确
				logger.warn("---------------login warn-------{}账户密码不正确------",
						username);
				return new ResponseJson(StatusCode.LOGIN_ERROR);
			}
			return ResponseJson.getSuccessResponse();
		}
		logger.info("------用户 {} 登录 成功~", username);
		return ResponseJson.getSuccessResponse();
	}

	/**
	 * @Title: loginOut
	 * @Description: TODO(退出登录)
	 * @param @return 设定文件
	 * @return ResponseJson 返回类型
	 * @author: mengxr
	 * @date 2017年3月28日 上午9:48:30
	 * @throws
	 */
	@RequestMapping(value = "/loginout"/*, method = RequestMethod.POST*/)
	public ResponseJson loginout() {
		AbroadSession session = new AbroadSession();
		session.logout();
		return ResponseJson.getSuccessResponse();
	}
}
