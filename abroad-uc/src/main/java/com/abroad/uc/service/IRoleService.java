package com.abroad.uc.service;

import java.util.List;

import com.abroad.common.exception.ServiceException;
import com.abroad.uc.domain.Role;
import com.abroad.uc.vo.RoleVO;

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
	/**
	* @Title: queryRoleIdsByUserId
	* @Description: TODO(通过用户ID查询所拥有的角色ID)
	* @param @param userId
	* @param @return
	* @param @throws ServiceException    设定文件
	* @return List<Long>    返回类型
	* @author: mengxr
	* @date 2017年4月19日 下午1:36:27
	* @throws
	*/
	List<Long> queryRoleIdsByUserId(long userId)  throws ServiceException;
	/**
	* @Title: insertRole
	* @Description: TODO(添加角色)
	* @param @param role
	* @param @return
	* @param @throws ServiceException    设定文件
	* @return boolean    返回类型
	* @author: mengxr
	* @date 2017年4月24日 上午10:15:09
	* @throws
	*/
	boolean insertRole(RoleVO role)  throws ServiceException;
	/**
	* @Title: deleteRole
	* @Description: TODO(删除角色)
	* @param @param id
	* @param @return
	* @param @throws ServiceException    设定文件
	* @return boolean    返回类型
	* @author: mengxr
	* @date 2017年4月24日 上午10:15:06
	* @throws
	*/
	boolean deleteRole(Long id)  throws ServiceException;
}
