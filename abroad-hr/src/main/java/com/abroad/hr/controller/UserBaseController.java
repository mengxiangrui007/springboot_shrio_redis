package com.abroad.hr.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.controller.BaseController;
import com.abroad.common.exception.AbroadException;
import com.abroad.common.exception.ServiceException;
import com.abroad.common.vo.ShiroUser;
import com.abroad.hr.manager.UserBaseManager;
import com.abroad.hr.service.IUserBaseService;
import com.abroad.hr.service.IUserJobService;
import com.abroad.hr.service.IUserLinkService;
import com.abroad.hr.vo.PageUserBaseVo;


/**
 * 用户信息Controller
 * @author shichangjian
 *
 */
@RestController
@RequestMapping(value="/userBase")
public class UserBaseController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserBaseManager userBaseManager;
	@Autowired
	private IUserBaseService iUserBaseService;
	@Autowired
	private IUserLinkService iUserLinkService;
	@Autowired
	private IUserJobService iUserJobService;
	/**
	 * 添加用户信息
	 * @param pageUserBaseVo
	 * @return
	 */
	@RequestMapping(value="/addUserBase",method=RequestMethod.POST)
	public ResponseJson addUserBase(@RequestBody PageUserBaseVo pageUserBaseVo) throws AbroadException{
		ShiroUser shiroUser = super.getShiroUser();
		if(null == shiroUser){ 
			logger.error("ShiroUser null未能获得session信息");
			throw new ServiceException("abroad.ShiroUserNull");
		}
		if(null == pageUserBaseVo){
			throw new ServiceException("abroad.PageUserBaseVoNull");
		}
		Long id = shiroUser.getId();
		pageUserBaseVo.getUserBase().setAccountId(id);
		userBaseManager.addUserBase(pageUserBaseVo);
		
		return new ResponseJson();
	}
	/**
	 * 修改用户信息
	 * @param pageUserBaseVo
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/updateUserBase",method=RequestMethod.POST)
	public ResponseJson updateUserBase(@RequestBody PageUserBaseVo pageUserBaseVo) throws AbroadException{
		ShiroUser shiroUser = super.getShiroUser();
		if(null == shiroUser){
			logger.error("ShiroUser null未能获得session信息");
			throw new ServiceException("abroad.ShiroUserNull");
		}
		userBaseManager.updateUserBase(pageUserBaseVo,shiroUser.getId());
		
		return new ResponseJson();
	}
	/**
	 * 根据id查询用户基本信息
	 * @param id
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/findUserBase",method=RequestMethod.GET)
	public ResponseJson findUserBase(@RequestParam("id") Long id) throws AbroadException{
		ResponseJson responseJson = new ResponseJson();
		responseJson.setData(iUserBaseService.findUserBase(id));
		
		return responseJson;
	}
	/**
	 * 根据用户id查询用户联系信息
	 * @param userId
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/findUserLink",method=RequestMethod.GET)
	public ResponseJson findUserLink(@RequestParam("userId") Long userId) throws AbroadException{
		ResponseJson responseJson = new ResponseJson();
		responseJson.setData(iUserLinkService.findUserLink(userId));
		
		return responseJson;
	}
	/**
	 * 根据用户id查询用户职业
	 * @param userId
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/findUserJob",method=RequestMethod.GET)
	public ResponseJson findUserJob(@RequestParam("userId") Long userId) throws AbroadException{
		ResponseJson responseJson = new ResponseJson();
		responseJson.setData(iUserJobService.findUserJob(userId));
		
		return responseJson;
	}
	/**
	 * 删除用户信息
	 * @param id
	 * @return
	 * @throws JuntaiException
	 */
	@RequestMapping(value="/deleteUserBase",method=RequestMethod.GET)
	public ResponseJson deleteUserBase(@RequestParam("id") Long id) throws AbroadException{
		ShiroUser shiroUser = super.getShiroUser();
		if(null == shiroUser){
			logger.error("ShiroUser null未能获得session信息");
			throw new ServiceException("abroad.ShiroUserNull");
		}
		userBaseManager.deleteUserBase(id,shiroUser.getId());
		
		return new ResponseJson();
	}

}
