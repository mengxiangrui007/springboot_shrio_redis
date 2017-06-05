package com.abroad.school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.CacheEvict;
import com.abroad.common.tools.Cacheable;
import com.abroad.school.dao.ISchoolOverviewDao;
import com.abroad.school.domain.SchoolOverview;
import com.abroad.school.service.ISchoolOverviewService;
import com.abroad.school.vo.ResponseSchoolOverviewVo;
import com.abroad.uc.domain.Dictionary;
import com.abroad.uc.service.IDictionaryService;

@Service
public class SchoolOverviewServiceImpl extends AbstractService<SchoolOverview, Long> implements ISchoolOverviewService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ISchoolOverviewDao iSchoolOverviewDao;
	@Autowired
	private IDictionaryService iDictionaryService;
	@Override
	public void setBaseDao() {
		super.baseDao = iSchoolOverviewDao;
	}
	/**
	 * 根据学校id查询学校概述
	 */
	@Override
	@Cacheable(key="getSchoolOverview",fieldKey="#schoolId")
	public ResponseSchoolOverviewVo findSchoolOverview(Long schoolId) throws ServiceException{
		logger.info("[info message] method:findSchoolOverview");
		if(null == schoolId || schoolId <= 0){
			throw new ServiceException("abroad.idIsNull");
		}
		SchoolOverview schoolOverview = iSchoolOverviewDao.findSchoolOverview(schoolId);
		if(null == schoolOverview)
			return null;
		
		//封装学校概述
		Dictionary schoolType = iDictionaryService.findDictionary(schoolOverview.getSchoolTypeCode());
		if(null == schoolType)
			return null;
		ResponseSchoolOverviewVo responseSchoolOverviewVo = new ResponseSchoolOverviewVo();
		responseSchoolOverviewVo.setSchoolOverview(schoolOverview);
		responseSchoolOverviewVo.setSchoolType(schoolType.getDes());
		
		return responseSchoolOverviewVo;
	}
	/**
	 * 修改学校概述
	 */
	@Override
	@CacheEvict(key="getSchoolOverview",fieldKey="#schoolOverview.getSchoolId()")
	public int updateSchoolOverview(SchoolOverview schoolOverview) {
		
		return iSchoolOverviewDao.update(schoolOverview);
	}
	/**
	 * 根据学校id删除学校概述
	 */
	@Override
	@CacheEvict(key="getSchoolOverview",fieldKey="#schoolId")
	public int deleteSchoolOverview(Long schoolId) {
		
		return iSchoolOverviewDao.deleteSchoolOverview(schoolId);
	}

}
