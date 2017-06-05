package com.abroad.hr.dao;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.dao.IBaseDao;
import com.abroad.hr.domain.UserLink;


public interface IUserLinkDao extends IBaseDao<UserLink, Long>{

	/**
	 * 根据用户id查询用户联系信息
	 * @param id
	 * @return
	 */
	public UserLink findUserLink(@Param("userId") Long userId);
	/**
	 * 修改用户联系信息
	 * @param userLink
	 * @return
	 */
	public int updateUserLink(@Param("userLink") UserLink userLink);
	/**
	 * 根据用户id删除用户联系信息
	 * @param userId
	 * @return
	 */
	public int deleteUserLink (@Param("userId") Long userId);
}
