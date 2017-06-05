package com.abroad.uc.dao;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.dao.IBaseDao;
import com.abroad.uc.domain.User;

/**
* @ClassName: IUserMapper
* @Description: TODO(用户登陆)
* @author: mengxr
* @date 2017年3月30日 下午5:32:33
*/
public interface IUserMapper  extends IBaseDao<User,Long>{
	/**
	* @Title: login
	* @Description: TODO(查询用户接口)
	* @param @param username
	* @param @return    设定文件
	* @return User    返回类型
	* @author: mengxr
	* @date 2017年3月30日 下午5:32:50
	* @throws
	*/
	User login(@Param("username") String username);
}
