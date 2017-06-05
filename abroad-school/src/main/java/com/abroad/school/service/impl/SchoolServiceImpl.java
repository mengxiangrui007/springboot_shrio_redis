package com.abroad.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.CacheEvict;
import com.abroad.common.tools.Cacheable;
import com.abroad.school.dao.ISchoolDao;
import com.abroad.school.domain.School;
import com.abroad.school.service.ISchoolService;
import com.abroad.school.vo.ResponseSchoolVo;
import com.abroad.uc.domain.Dictionary;
import com.abroad.uc.service.IDictionaryService;

@Service
public class SchoolServiceImpl extends AbstractService<School, Long> implements ISchoolService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ISchoolDao iSchoolDao;
	@Autowired
	private IDictionaryService iDictionaryService;
	@Override
	public void setBaseDao() {
		super.baseDao = iSchoolDao;
	}
	/**
	 * 根据id查询学校信息
	 */
	@Transactional
	@Override
	@Cacheable(key="getSchool",fieldKey="#id")
	public ResponseSchoolVo findSchool(Long id) throws ServiceException{
		logger.info("[info message] method:findSchool");
		if(null == id || id <= 0){
			throw new ServiceException("abroad.idIsNull");
		}
		School school = iSchoolDao.findById(id);
		if(null == school){
			return null;
		}
		//封装学校基本信息
		Dictionary country = iDictionaryService.findDictionary(school.getCountryCode());
		Dictionary province = iDictionaryService.findDictionary(school.getProvinceCode());
		Dictionary city = iDictionaryService.findDictionary(school.getCityCode());
		ResponseSchoolVo responseSchoolVo = new ResponseSchoolVo();
		responseSchoolVo.setSchool(school);
		if(null == country || null == province || null == city)
			return null;
		responseSchoolVo.setCountry(country.getDes());
		responseSchoolVo.setProvince(province.getDes());
		responseSchoolVo.setCity(city.getDes());
		
		return responseSchoolVo;
	}
	/**
	 * 修改学校信息
	 */
	@Override
	@CacheEvict(key="getSchool",fieldKey="#school.getId()")
	public int updateSchool(School school) {
		
		return iSchoolDao.update(school);
	}
	/**
	 * 删除学校信息
	 */
	@Override
	@CacheEvict(key="getSchool",fieldKey="#id")
	public int deleteSchool(Long id) {
		
		return iSchoolDao.deleteById(id);
	}

	
}
