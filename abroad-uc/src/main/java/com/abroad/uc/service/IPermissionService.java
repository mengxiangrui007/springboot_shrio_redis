package com.abroad.uc.service;

import java.util.List;
import java.util.Map;

import com.abroad.common.exception.ServiceException;
import com.abroad.uc.domain.MenuPermission;
import com.abroad.uc.domain.OperationPermission;

/**
* @ClassName: IPermissionService
* @Description: TODO(权限服务接口)
* @author: mengxr
* @date 2017年4月12日 下午9:02:51
*/
public interface IPermissionService {
	/**
	* @Title: queryMenuPermissByUserId
	* @Description: TODO(通过用户ID查询菜单权限)
	* @param @return    设定文件
	* @return List<MenuPermission>    返回类型
	* @author: mengxr
	* @date 2017年4月12日 下午8:57:34
	* @throws
	*/
	List<MenuPermission> queryMenuPermissByUserId(long userId) throws ServiceException;
	/**
	* @Title: queryOperPermissByUserId
	* @Description: TODO(根据用户ID查询功能权限)
	* @param @param userId
	* @param @return    设定文件
	* @return List<OperationPermission>    返回类型
	* @author: mengxr
	* @date 2017年4月12日 下午8:58:42
	* @throws
	*/
	List<OperationPermission> queryOperPermissByUserId(long userId) throws ServiceException;
	
	/**
	* @Title: queryMouduleChainDefinition
	* @Description: TODO(根据项目模板加载操作权限链路)
	* @param @param mouduleCode
	* @param @return
	* @param @throws Exception    设定文件
	* @return Map<String,String>    返回类型
	* @author: mengxr
	* @date 2017年4月13日 上午11:21:56
	* @throws
	*/
	Map<String, String> queryMouduleChainDefinition(String mouduleCode) throws ServiceException;
}
