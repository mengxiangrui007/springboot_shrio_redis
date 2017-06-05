package com.abroad.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.dao.IBaseDao;
import com.abroad.uc.domain.Role;

/**
* @ClassName: IRoleMapper
* @Description: TODO(角色Mapper)
* @author: mengxr
* @date 2017年4月12日 下午6:54:57
*/
public interface IRoleMapper extends IBaseDao<Role,Long>{
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
	List<Role> queryRoleListByUserId(@Param("userId") long userId);
}
