package com.abroad.hr.service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.IBaseService;
//import com.abroad.common.tools.BaseService;
import com.abroad.hr.domain.UserBase;
import com.abroad.hr.vo.ResponseUserBaseVo;

public interface IUserBaseService extends IBaseService<UserBase, Long>{

	/**
	 * 根据用户id查询详情
	 * @param id
	 * @return
	 */
	public ResponseUserBaseVo findUserBase(Long id) throws ServiceException;
	/**
	 * 修改用户基本信息
	 * @param userBase
	 * @return
	 */
	public int updateUserBase(UserBase userBase);
	/**
	 * 根据用户删除用户基本信息
	 * @param userId
	 * @return
	 */
	public int deleteUserBase(Long userId);
}
