package com.abroad.school.service;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.IBaseService;
import com.abroad.school.domain.SchoolOverview;
import com.abroad.school.vo.ResponseSchoolOverviewVo;

public interface ISchoolOverviewService extends IBaseService<com.abroad.school.domain.SchoolOverview, Long>{
	/**
	 * 根据学校id查询学校概述
	 * @param schoolId
	 * @return
	 */
	public ResponseSchoolOverviewVo findSchoolOverview(Long schoolId) throws ServiceException;
	/**
	 * 修改学校概述
	 * @param specialty
	 * @return
	 */
	public int updateSchoolOverview(SchoolOverview schoolOverview);
	/**
	 * 根据学校id删除学校概述
	 * @param schoolId
	 * @return
	 */
	public int deleteSchoolOverview(Long schoolId);
	
}
