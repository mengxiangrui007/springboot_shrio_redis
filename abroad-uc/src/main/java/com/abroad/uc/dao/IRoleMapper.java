package com.abroad.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.dao.IBaseDao;
import com.abroad.uc.domain.Role;
import com.abroad.uc.vo.RoleVO;

/**
* @ClassName: IRoleMapper
* @Description: TODO(角色Mapper)
* @author: mengxr
* @date 2017年4月12日 下午6:54:57
*/
public interface IRoleMapper extends IBaseDao<Role,Long>{
	/**
	* @Title: queryRoleListByUserId
	* @Description: TODO(通过用户ID查询用户角色信息集合)
	* @param @param userId
	* @param @return    设定文件
	* @return List<Role>    返回类型
	* @author: mengxr
	* @date 2017年4月12日 下午6:55:47
	* @throws
	*/
	List<Role> queryRoleListByUserId(@Param("userId") long userId) ;

	/**
	* @Title: queryRoleIdsByUserId
	* @Description: TODO(通过用户ID查询用户角色信息ID集合)
	* @param @param userId
	* @param @return
	* @param @    设定文件
	* @return List<Long>    返回类型
	* @author: mengxr
	* @date 2017年4月19日 下午1:37:42
	* @throws
	*/
	List<Long> queryRoleIdsByUserId(@Param("userId") long userId) ;
	/**
	* @Title: queryRoleByName
	* @Description: TODO(通过角色名称，集团主键，组织主键查询角色名称是否存在)
	* @param @param role
	* @param @return    设定文件
	* @return Integer    返回类型
	* @author: mengxr
	* @date 2017年4月21日 下午6:18:32
	* @throws
	*/
	Integer queryRoleByName(@Param("role") RoleVO role);
}
