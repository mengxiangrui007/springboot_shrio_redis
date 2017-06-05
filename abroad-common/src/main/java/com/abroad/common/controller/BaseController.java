package com.abroad.common.controller;

import java.util.Date;

import org.apache.shiro.SecurityUtils;

import com.abroad.common.vo.ShiroUser;
import com.abroad.common.vo.OperateVO;


/**
 * @ClassName: BaseAction
 * @Description: TODO(Action层面公共方法)
 * @author: mengxr
 * @company: 国美云事业部基础开发
 * @date 2017年2月24日 下午2:12:50
 */
public class BaseController<T> {
	
	
	/**
	* @Title: getOperateInfo
	* @Description: TODO(得到增加的操作的信息)
	* @param @return    设定文件
	* @return OperateVO    返回类型
	* @author: mengxr
	* @date 2017年3月27日 下午3:18:18
	* @throws
	*/
	protected OperateVO getInsertOpeInfo(){
		OperateVO operateVO =  new OperateVO();
		operateVO.setCratetime(new Date());
		ShiroUser shiroUser = getShiroUser();
		if(null != shiroUser){
			long userId = shiroUser.getId(); //创建者ID
			operateVO.setPk_hr_user(userId);
		}
		return operateVO;
	}
	/**
	 * 获取当前登录用户的Session信息
	* @Title: getAccount
	* @Description: TODO()
	* @param @return    设定文件
	* @return Account    返回类型
	* @author: mengxr
	* @date 2017年3月28日 下午8:39:50
	* @throws
	*/
	protected  ShiroUser getShiroUser(){
		return 	(ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}
}
