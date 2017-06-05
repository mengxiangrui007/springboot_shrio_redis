package com.abroad.hr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.controller.BaseController;
@RestController
@RequestMapping("/menu")
public class MenuPermissController extends BaseController{
	//日志处理
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	* @Title: queryUserMenuPermiss
	* @Description: TODO(查询登录用户的菜单权限)
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年4月13日 上午10:42:26
	* @throws
	*/
	@RequestMapping(value = "/queryUserMenuPermiss",method = RequestMethod.PUT)
	public ResponseJson queryUserMenuPermiss(){
		return null;
	}
}
