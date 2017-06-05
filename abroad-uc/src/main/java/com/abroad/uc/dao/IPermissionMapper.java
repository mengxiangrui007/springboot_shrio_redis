package com.abroad.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abroad.uc.domain.MenuPermission;
import com.abroad.uc.domain.OperationPermission;

/**
* @ClassName: IPermissionMapper
* @Description: TODO(权限Mapper)
* @author: mengxr
* @date 2017年4月12日 下午8:55:58
*/
public interface IPermissionMapper {
	/**
	* @Title: queryMenuPermissByUserId
	* @Description: TODO(通过用户ID查询菜单权限)
	* @param @return    设定文件
	* @return List<MenuPermission>    返回类型
	* @author: mengxr
	* @date 2017年4月12日 下午8:57:34
	* @throws
	*/
	List<MenuPermission> queryMenuPermissByUserId(@Param("userId") long userId);
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
	List<OperationPermission> queryOperPermissByUserId(@Param("userId") long userId);
}
