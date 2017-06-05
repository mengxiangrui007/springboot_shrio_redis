package com.abroad.hr.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.CacheEvict;
import com.abroad.common.tools.Cacheable;
import com.abroad.hr.dao.IUserBaseDao;
import com.abroad.hr.domain.UserBase;
import com.abroad.hr.service.IUserBaseService;
import com.abroad.hr.vo.ResponseUserBaseVo;
import com.abroad.uc.domain.Dictionary;
import com.abroad.uc.service.IDictionaryService;

@Service
public class UserBaseServiceImpl extends AbstractService<UserBase, Long> implements IUserBaseService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IUserBaseDao iUserBaseDao;
	@Autowired
	private IDictionaryService iDictionaryService;

	@Override
	public void setBaseDao() {
		super.baseDao = iUserBaseDao;
	}

	/**
	 * 根据id查询用户基本信息
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	@Cacheable(key="getUserBase",fieldKey="#id")
	public ResponseUserBaseVo findUserBase(Long id) throws ServiceException{
		logger.info("[info message] method:findUserBase");
		if(null == id || id <= 0){
			throw new ServiceException("abroad.idIsNull");
		}
		UserBase userBase = iUserBaseDao.findById(id);
		if(null == userBase){
			return null;
		}
		Long countryId = userBase.getCountryId();
		Long cityId = userBase.getCityId();
		Long userTypeCode = userBase.getUserTypeCode();
		if(null == countryId || null == cityId || null == userTypeCode){
			return null;
		}
		//封装用户信息
		Dictionary country = iDictionaryService.findDictionary(countryId);
		Dictionary city = iDictionaryService.findDictionary(cityId);
		Dictionary userType = iDictionaryService.findDictionary(userTypeCode);
		ResponseUserBaseVo responseUserBaseVo = new ResponseUserBaseVo();  
		responseUserBaseVo.setUserBase(userBase);
		responseUserBaseVo.setCountry(country.getDes());
		responseUserBaseVo.setCity(city.getDes());
		responseUserBaseVo.setUserType(userType.getDes());
			
		return responseUserBaseVo;
	}
	
	/**
	 * 
	 * @param userBase
	 * @return
	 */
	@CacheEvict(key="getUserBase",fieldKey="#userBase.getId()")
	public int updateUserBase(UserBase userBase) {
		
		return iUserBaseDao.update(userBase);
	}
	
	/**
	 * 根据用户id删除用户基本信息
	 */
	@Override
	@CacheEvict(key="getUserBase",fieldKey="#userId")
	public int deleteUserBase(Long userId) {
		
		return iUserBaseDao.deleteById(userId);
	}
	
	

}
