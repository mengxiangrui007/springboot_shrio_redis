package com.abroad.hr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.Page;
import com.abroad.common.comn.ResponseJson;
import com.abroad.common.comn.StatusCode;
import com.abroad.common.comn.web.jqgird.JQueryGridTools;
import com.abroad.common.controller.BaseController;
import com.abroad.uc.service.IUserService;
import com.abroad.uc.vo.UserVO;

/**
* @ClassName: UserController
* @Description: TODO(用户交互接口)
* @author: mengxr
* @date 2017年3月25日 下午3:26:46
*/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserVO>{
	//日志处理
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IUserService iUserService;
	/**
	* @Title: insertUser
	* @Description: TODO(添加用户)
	* @param @param user
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年3月31日 上午10:15:00
	* @throws
	*/
	@RequestMapping(value = "/insertUser",method = RequestMethod.PUT)
	public ResponseJson insertUser(UserVO user){
		try {
			user.setOpe(super.getInsertOpeInfo());
			iUserService.insertUser(user);
			return new ResponseJson(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("获取账号异常{}用户名异常",user.getAccount(),e);
			return new ResponseJson(StatusCode.ERROR);
		}
	}
	/**
	* @Title: queryUserInfo
	* @Description: TODO(查询用户信息)
	* @param @param user
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年4月17日 上午10:04:22
	* @throws
	*/
	@RequestMapping(value = "/queryUserInfo",method = RequestMethod.GET)
	public ResponseJson queryUserInfo(UserVO user){
		//判定是否为Jquery Gird前端框架
		JQueryGridTools<UserVO> gridTools = new JQueryGridTools<UserVO>(UserVO.class);
		UserVO queryVO = gridTools.getQueryVO();
		if(null != queryVO){
			user = queryVO;
		}
		ResponseJson reJson  = null;
		try {
			user.setGroups(super.getUserGroups());
			user.setOrgs(super.getUserOrgs());	
			Page pager = iUserService.queryUserInfo(user,
					super.getPage(), super.getPageSize());
			reJson = ResponseJson.getSuccessResponse();
			reJson.setData(pager);
		} catch (Exception e) {
			reJson = ResponseJson.getFailedResponse();
			logger.error("查询条件用户异常" + user,e);
		}
		return reJson;
	}
}
