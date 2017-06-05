package com.abroad.hr.dao;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.dao.IBaseDao;
import com.abroad.hr.domain.UserJob;


public interface IUserJobDao extends IBaseDao<UserJob, Long>{

	/**
	 * 根据用户id查询用户职业
	 * @param id
	 * @return
	 */
	public UserJob findUserJob(@Param("userId") Long userId);
	/**
	 * 修改用户职业
	 * @param userJob
	 * @return
	 */
	public int updateUserJob(@Param("userJob") UserJob userJob);
	/**
	 * 根据用户id删除用户职业
	 * @param userId
	 * @return
	 */
	public int deleteUserJob(@Param("userId") Long userId);
	 
}
