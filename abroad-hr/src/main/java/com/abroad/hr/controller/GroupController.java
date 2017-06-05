package com.abroad.hr.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.comn.StatusCode;
import com.abroad.common.comn.Tree;
import com.abroad.common.controller.BaseController;
import com.abroad.common.exception.ServiceException;
import com.abroad.common.vo.ShiroUser;
import com.abroad.security.shiro.AbroadSession;
import com.abroad.uc.domain.Group;
import com.abroad.uc.service.IGroupService;
import com.abroad.uc.vo.GroupVO;

/**
* @ClassName: GroupController
* @Description: TODO(集团交互接口)
* @author: mengxr
* @date 2017年3月25日 下午3:48:07
*/
@RestController
@RequestMapping("/group")
public class GroupController extends BaseController<GroupVO>{
	
	//日志处理
	protected Logger logger = Logger.getLogger(getClass());
	@Autowired
	private IGroupService iGroupService;
	
	/**
	 * 此树只查到下一级
	* @Title: queryGroupTree
	* @Description: TODO(查询集团树结构)
	* @param @param id
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年3月27日 上午11:32:04
	* @throws
	*/
	@RequestMapping(value = "/queryGroupTree",method = RequestMethod.GET)
	public ResponseJson queryGroupTree(@PathVariable("id") Long id){
		ResponseJson responseJson = null;
		try {
			List<Tree> abroadTreeList = iGroupService.queryGroupTree(156,id);
			responseJson = new ResponseJson(StatusCode.SUCCESS);
			responseJson.setData(abroadTreeList);
		} catch (Exception e) {
			responseJson = new ResponseJson(StatusCode.ERROR);
			logger.error("查询集团树结构发生异常:",new ServiceException(e));
		}
		return responseJson;
	}
	/**
	* @Title: queryGroup
	* @Description: TODO(查询集团信息)
	* @param @param id
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年3月27日 下午3:46:16
	* @throws
	*/
	@RequestMapping(value = "/queryGroup",method = RequestMethod.GET)
	public ResponseJson queryGroup(@PathVariable("id") Long id){
		ResponseJson responseJson = new ResponseJson();
		try {
			Group group = iGroupService.queryGroup(id);
			responseJson.setCode(StatusCode.SUCCESS.getCode());
			responseJson.setData(group);
		} catch (Exception e) {
			responseJson.setCode(StatusCode.ERROR.getCode());
			logger.error("查询集团信息发生异常:",new ServiceException(e));
		}
		return responseJson;
	}
	/**
	* @Title: insertGroup
	* @Description: TODO(添加集团)
	* @param @param group
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年3月27日 下午2:53:06
	* @throws
	*/
	@RequestMapping(value = "/insertGroup",method = RequestMethod.PUT)
	public ResponseJson insertGroup(GroupVO group){
		boolean flag = false;
		ResponseJson responseJson = new ResponseJson();
		try {
			group.setOpe(super.getInsertOpeInfo());
			flag = iGroupService.insertGroup(group);
			if(flag){
				responseJson.setCode(StatusCode.SUCCESS.getCode());
			}else{
				responseJson.setCode(StatusCode.ERROR.getCode());
			}
		} catch (Exception e) {
			responseJson.setCode(StatusCode.ERROR.getCode());
			logger.error("添加集团异常:",new ServiceException(e));
		}
		return responseJson;
	}
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public ResponseJson test(){
		ShiroUser shiroUser = super.getShiroUser();
		ResponseJson responseJson = new ResponseJson();
		AbroadSession abroadSession = new AbroadSession();
		//ShiroUser loginUserInfo = abroadSession.getLoginUserInfo();
		//String account = loginUserInfo.getAccount();
		Session session = abroadSession.getSession();
		Serializable id = session.getId();
		System.out.println("---------------------1--------------"+id);
		return responseJson;
	}
	@RequestMapping(value = "/bbb",method = RequestMethod.GET)
	public ResponseJson bbb(){
		ResponseJson responseJson = new ResponseJson();
		AbroadSession abroadSession = new AbroadSession();
		//ShiroUser loginUserInfo = abroadSession.getLoginUserInfo();
		//String account = loginUserInfo.getAccount();
		Session session = abroadSession.getSession();
		Serializable id = session.getId();
		System.out.println("---------------------1--------------"+id);
		return responseJson;
	}
}
