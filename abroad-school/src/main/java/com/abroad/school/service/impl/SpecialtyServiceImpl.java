package com.abroad.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.CacheEvict;
import com.abroad.common.tools.Cacheable;
import com.abroad.school.dao.ISpecialtyDao;
import com.abroad.school.domain.Specialty;
import com.abroad.school.service.ISpecialtyService;
import com.abroad.school.vo.ResponseSchoolSpecialtyVo;
import com.abroad.uc.domain.Dictionary;
import com.abroad.uc.service.IDictionaryService;

@Service
public class SpecialtyServiceImpl extends AbstractService<Specialty, Long> implements ISpecialtyService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ISpecialtyDao iSpecialtyDao;
	@Autowired
	private IDictionaryService iDictionaryService;
	
	@Override
	public void setBaseDao() {
		super.baseDao = iSpecialtyDao;
	}
	/**
	 * 根据学校id查询学校专业
	 */
	@Override
	@Cacheable(key="getSpecialty",fieldKey="#schoolId")
	public ResponseSchoolSpecialtyVo findSpecialty(Long schoolId) throws ServiceException{
		logger.info("[info message] method:findSpecialty");
		
		if(null == schoolId || schoolId <= 0)
			throw new ServiceException("abroad.idIsNull");
		Specialty specialty = iSpecialtyDao.findSpecialty(schoolId);
		if(null == specialty)
			return null;
		
		//封装学校概述
		Dictionary educationLevel = iDictionaryService.findDictionary(specialty.getEducationLevelCode());
		if(null == educationLevel)
			return null;
		ResponseSchoolSpecialtyVo responseSchoolSpecialtyVo = new ResponseSchoolSpecialtyVo();
		responseSchoolSpecialtyVo.setSpecialty(specialty);
		responseSchoolSpecialtyVo.setEducationLevel(educationLevel.getDes());
				
		return responseSchoolSpecialtyVo;
	}
	/**
	 * 修改学校专业
	 */
	@Override
	@CacheEvict(key="getSpecialty",fieldKey="#specialty.getSchoolId()")
	public int updateSpecialty(Specialty specialty) {
		
		return iSpecialtyDao.update(specialty);
	}
	/**
	 * 根据学校id删除专业
	 */
	@Override
	@CacheEvict(key="getSpecialty",fieldKey="#schoolId")
	public int deleteSpecialty(Long schoolId) {
		
		return iSpecialtyDao.deleteSpecialty(schoolId);
	}

}
