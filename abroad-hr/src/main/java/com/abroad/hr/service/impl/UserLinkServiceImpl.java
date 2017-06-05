package com.abroad.hr.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.CacheEvict;
import com.abroad.common.tools.Cacheable;
import com.abroad.hr.dao.IUserLinkDao;
import com.abroad.hr.domain.UserLink;
import com.abroad.hr.service.IUserLinkService;

@Service
public class UserLinkServiceImpl extends AbstractService<UserLink, Long> implements IUserLinkService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IUserLinkDao iUserLinkDao;
	

	@Override
	public void setBaseDao() {
		super.baseDao = iUserLinkDao;
	}

	/**
	 * 根据用户id查询用户联系信息
	 */
	@Override
	@Cacheable(key="getUserLink",fieldKey="#userId")
	public UserLink findUserLink(Long userId) throws ServiceException{
		logger.info("[info message] method:findUserLink");
		if(null == userId){
			throw new ServiceException("abroad.idIsNull");
		}
		return iUserLinkDao.findUserLink(userId);
	}

	@Override
	@CacheEvict(key="getUserLink",fieldKey="#userLink.getUserId()")
	public int updateUserLink(UserLink userLink) {
		
		return iUserLinkDao.update(userLink);
	}

	@Override
	@CacheEvict(key="getUserLink",fieldKey="#userId")
	public int deleteUserLink(Long userId) {
		
		return iUserLinkDao.deleteUserLink(userId);
	}

}
