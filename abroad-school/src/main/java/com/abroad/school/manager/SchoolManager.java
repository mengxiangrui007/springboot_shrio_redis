package com.abroad.school.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.exception.AbroadException;
import com.abroad.common.exception.ServiceException;
import com.abroad.common.tools.Sequence;
import com.abroad.school.domain.School;
import com.abroad.school.domain.SchoolOverview;
import com.abroad.school.domain.Specialty;
import com.abroad.school.service.ISchoolOverviewService;
import com.abroad.school.service.ISchoolService;
import com.abroad.school.service.ISpecialtyService;
import com.abroad.school.vo.PageSchoolVo;
import com.abroad.school.vo.ResponseSchoolOverviewVo;
import com.abroad.school.vo.ResponseSchoolSpecialtyVo;
import com.abroad.school.vo.ResponseSchoolVo;
import com.abroad.uc.domain.Dictionary;
import com.abroad.uc.service.IDictionaryService;


@Service
public class SchoolManager {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ISchoolService iSchoolService;
	@Autowired
	private ISchoolOverviewService iSchoolOverviewService;
	@Autowired
	private ISpecialtyService iSpecialtyService;
	@Autowired
	private IDictionaryService iDictionaryService;
	
	/**
	 * 添加学校基本信息
	 * @param pageSchoolVo
	 */
	@Transactional
	public void addSchool(PageSchoolVo pageSchoolVo) throws ServiceException{
		logger.info("[info message] method:addSchool");
		if(null == pageSchoolVo){
			throw new ServiceException("abroad.pageSchoolVoIsNull");
		}
		School school = pageSchoolVo.getSchool();
		//生成主键
		school.setId(Sequence.generateId());
		iSchoolService.insert(school);
		long schoolId = school.getId();
		if(schoolId <= 0){
			logger.error("[error message] 学校基本信息添加异常！");
			throw new ServiceException("abroad.insertException");
		}
		
		SchoolOverview schoolOverview = pageSchoolVo.getSchoolOverview();
		//生成主键
		schoolOverview.setId(Sequence.generateId());
		schoolOverview.setSchoolId(schoolId);
		iSchoolOverviewService.insert(schoolOverview);
		if(schoolOverview.getId() <= 0){
			logger.error("[error message] 学校概述添加异常！");
			throw new ServiceException("abroad.insertException");
		}
		
		Specialty specialty = pageSchoolVo.getSpecialty();
		//生成主键
		specialty.setId(Sequence.generateId());
		specialty.setSchoolId(schoolId);
		iSpecialtyService.insert(specialty);
		if(specialty.getId() <= 0){
			logger.error("[error message] 学校专业添加异常！");
			throw new ServiceException("abroad.insertException");
		}
	}
	/**
	 * 根据id查询学校基本信息
	 * @param id
	 * @return
	 * @throws JuntaiException
	 */
//	@Transactional
//	public ResponseSchoolVo findSchool(Long id) throws ServiceException{
//		logger.info("[info message] method:findSchool");
//		if(null == id || id <= 0){
//			throw new ServiceException("abroad.idIsNull");
//		}
//		School school = iSchoolService.findSchool(id);
//		if(null == school){
//			return null;
//		}
//		//封装学校基本信息
//		Dictionary country = iDictionaryService.findDictionary(school.getCountryCode());
//		Dictionary province = iDictionaryService.findDictionary(school.getProvinceCode());
//		Dictionary city = iDictionaryService.findDictionary(school.getCityCode());
//		ResponseSchoolVo responseSchoolVo = new ResponseSchoolVo();
//		responseSchoolVo.setSchool(school);
//		if(null == country || null == province || null == city)
//			return null;
//		responseSchoolVo.setCountry(country.getDes());
//		responseSchoolVo.setProvince(province.getDes());
//		responseSchoolVo.setCity(city.getDes());
//		
//		return responseSchoolVo;
//	}
	/**
	 * 根据学校id查询学校概述
	 * @param schoolId
	 * @return
	 * @throws JuntaiException
	 */
//	public ResponseSchoolOverviewVo findSchoolOverview(Long schoolId) throws ServiceException{
//		logger.info("[info message] method:findSchoolOverview");
//		if(null == schoolId || schoolId <= 0){
//			throw new ServiceException("abroad.idIsNull");
//		}
//		SchoolOverview schoolOverview = iSchoolOverviewService.findSchoolOverview(schoolId);
//		if(null == schoolOverview)
//			return null;
//		
//		//封装学校概述
//		Dictionary schoolType = iDictionaryService.findDictionary(schoolOverview.getSchoolTypeCode());
//		if(null == schoolType)
//			return null;
//		ResponseSchoolOverviewVo responseSchoolOverviewVo = new ResponseSchoolOverviewVo();
//		responseSchoolOverviewVo.setSchoolOverview(schoolOverview);
//		responseSchoolOverviewVo.setSchoolType(schoolType.getDes());
//		
//		return responseSchoolOverviewVo;
//	}
	/**
	 * 根据学校id查询学校专业
	 * @param schoolId
	 * @return
	 * @throws JuntaiException
	 */
//	public ResponseSchoolSpecialtyVo findSpecialty(Long schoolId) throws ServiceException{
//		logger.info("[info message] method:findSpecialty");
//		
//		if(null == schoolId || schoolId <= 0)
//			throw new ServiceException("abroad.idIsNull");
//		Specialty specialty = iSpecialtyService.findSpecialty(schoolId);
//		if(null == specialty)
//			return null;
//		
//		//封装学校概述
//		Dictionary educationLevel = iDictionaryService.findDictionary(specialty.getEducationLevelCode());
//		if(null == educationLevel)
//			return null;
//		ResponseSchoolSpecialtyVo responseSchoolSpecialtyVo = new ResponseSchoolSpecialtyVo();
//		responseSchoolSpecialtyVo.setSpecialty(specialty);
//		responseSchoolSpecialtyVo.setEducationLevel(educationLevel.getDes());
//				
//		return responseSchoolSpecialtyVo;
//	}
	/**
	 * 修改学校基本信息
	 * @param pageSchoolVo
	 * @return
	 * @throws JuntaiException
	 */
	@Transactional
	public void updateSchool(PageSchoolVo pageSchoolVo) throws ServiceException{
		logger.info("[info message] method:updateSchool");
		if(null == pageSchoolVo){
			throw new ServiceException("abroad.idIsNull");
		}
		School school = pageSchoolVo.getSchool();
		int schoolCount = iSchoolService.updateSchool(school);
		if(schoolCount <= 0){
			logger.error("[error message] 修改学校基本信息异常！");
			throw new ServiceException("abroad.updateException");
		}
		SchoolOverview schoolOverview = pageSchoolVo.getSchoolOverview();
		int schoolOverviewCount = iSchoolOverviewService.updateSchoolOverview(schoolOverview);
		if(schoolOverviewCount <= 0){
			logger.error("[error message] 修改学校概述异常！");
			throw new ServiceException("abroad.updateException");
		}
		Specialty specialty = pageSchoolVo.getSpecialty();
		int specialtyCount = iSpecialtyService.updateSpecialty(specialty);
		if(specialtyCount <= 0){
			logger.error("[error message] 修改学校专业异常！");
			throw new ServiceException("abroad.updateException");
		}
	}
	/**
	 * 删除学校基本信息
	 * @param id
	 * @throws JuntaiException
	 */
	@Transactional
	public void deleteSchool(Long id) throws ServiceException{
		logger.info("[info message] method:deleteSchool");
		if(null == id || id <= 0){
			 throw new ServiceException("abroad.idIsNull");
		}
		int schoolCount = iSchoolService.deleteSchool(id);
		if(schoolCount <= 0){
			logger.error("[error message] 删除学校基本信息异常！");
			throw new ServiceException("abroad.deleteException");
		}
		int overviewCount = iSchoolOverviewService.deleteSchoolOverview(id);
		if(overviewCount <= 0){
			logger.error("[error message] 删除学校概述异常！");
			throw new ServiceException("abroad.deleteException");
		}
		int specialtyCount = iSpecialtyService.deleteSpecialty(id);
		if(specialtyCount <= 0){
			logger.error("[error message] 删除专业异常！");
			throw new ServiceException("abroad.deleteException");
		}
	}
}
