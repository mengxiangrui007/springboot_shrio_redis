package com.abroad.uc.service;

import java.util.List;

import com.abroad.common.exception.ServiceException;
import com.abroad.uc.domain.Role;

/**
* @ClassName: IRoleService
* @Description: TODO(角色服务接口)
* @author: mengxr
* @date 2017年4月12日 下午9:11:21
*/
public interface IRoleService {
	/**
	* @Title: queryRoleListByUserId
	* @Description: TODO(通过用户ID查询用户角色信息)
	* @param @param userId
	* @param @return    设定文件
	* @return List<Role>    返回类型
	* @author: mengxr
	* @date 2017年4月12日 下午6:55:47
	* @throws
	*/
	List<Role> queryRoleListByUserId(long userId) throws ServiceException;
}
