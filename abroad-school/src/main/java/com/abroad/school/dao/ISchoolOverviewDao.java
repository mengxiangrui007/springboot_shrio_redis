package com.abroad.school.dao;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.dao.IBaseDao;
import com.abroad.school.domain.SchoolOverview;


public interface ISchoolOverviewDao extends IBaseDao<SchoolOverview, Long>{
	/**
	 * 根据学校id查询学校概述
	 * @param schoolId
	 * @return
	 */
	public SchoolOverview findSchoolOverview(@Param("schoolId") Long schoolId);
	/**
	 * 根据学校id删除学校概述
	 * @param schoolId
	 * @return
	 */
	public int deleteSchoolOverview(@Param("schoolId") Long schoolId);
	
}
