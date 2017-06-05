package com.abroad.hr.service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.IBaseService;
import com.abroad.hr.domain.UserJob;

public interface IUserJobService extends IBaseService<UserJob, Long>{

	/**
	 * 根据用户id查询用户职业
	 * @param id
	 * @return
	 */
	public UserJob findUserJob(Long userId) throws ServiceException;
	/**
	 * 修改用户职业
	 * @param userJob
	 * @return
	 */
	public int updateUserJob(UserJob userJob);
	/**
	 * 根据用户id删除用户职业
	 * @param userId
	 * @return
	 */
	public int deleteUserJob(Long userId);
}
