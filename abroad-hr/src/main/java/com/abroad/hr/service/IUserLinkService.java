package com.abroad.hr.service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.IBaseService;
import com.abroad.hr.domain.UserLink;

public interface IUserLinkService extends IBaseService<UserLink, Long>{

	/**
	 * 查询用户联系信息
	 * @param id
	 * @return
	 */
	public UserLink findUserLink(Long userId) throws ServiceException;
	/**
	 * 修改用户联系信息
	 * @param userLink
	 * @return
	 */
	public int updateUserLink(UserLink userLink);
	/**
	 * 根据用户id删除用户联系信息
	 * @param userId
	 * @return
	 */
	public int deleteUserLink (Long userId);
}
