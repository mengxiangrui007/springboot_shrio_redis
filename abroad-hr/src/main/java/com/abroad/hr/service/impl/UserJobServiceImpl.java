package com.abroad.hr.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.CacheEvict;
import com.abroad.common.tools.Cacheable;
import com.abroad.hr.dao.IUserJobDao;
import com.abroad.hr.domain.UserJob;
import com.abroad.hr.service.IUserJobService;

@Service
public class UserJobServiceImpl extends AbstractService<UserJob, Long> implements IUserJobService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IUserJobDao iUserJobDao;


	@Override
	public void setBaseDao() {
		super.baseDao = iUserJobDao;
	}

	/**
	 * 根据用户id查询用户职业
	 */
	@Override
	@Cacheable(key="getUserJob",fieldKey="#userId")
	public UserJob findUserJob(Long userId) throws ServiceException{
		logger.info("[info message] method:findUserJob");
		if(null == userId){
			throw new ServiceException("abroad.idIsNull");
		}
		return iUserJobDao.findUserJob(userId);
	}

	@Override
	@CacheEvict(key="getUserJob",fieldKey="#userJob.getUserId()")
	public int updateUserJob(UserJob userJob) {
		
		return iUserJobDao.update(userJob);
	}

	@Override
	@CacheEvict(key="getUserJob",fieldKey="#userId")
	public int deleteUserJob(Long userId) {
		
		return iUserJobDao.deleteUserJob(userId);
	}
	
	
}
