package com.abroad.hr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.comn.Tree;
import com.abroad.common.controller.BaseController;
import com.abroad.common.vo.ShiroUser;
/**
* @ClassName: PermissController
* @Description: TODO(权限交互接口)
* @author: mengxr
* @date 2017年4月19日 上午10:03:43
*/
@RestController
@RequestMapping("/permission")
public class PermissController extends BaseController<Object>{
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
	@RequestMapping(value = "/queryUserMenuPermiss",method = RequestMethod.GET)
	public ResponseJson queryUserMenuPermiss(){
		ResponseJson responseJson = null;
		ShiroUser shiroUser = super.getShiroUser();
		try {
			List<Tree> menuTrees = shiroUser.getMenuTrees();
			responseJson = ResponseJson.getSuccessResponse();
			responseJson.setData(menuTrees);
		} catch (Exception e) {
			responseJson = ResponseJson.getFailedResponse();
			logger.error("查询[{}]用户菜单错误：" + shiroUser.getAccount(),e);
		}
		return responseJson;
	}
}
