package com.abroad.school.dao;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.dao.IBaseDao;
import com.abroad.school.domain.Specialty;


public interface ISpecialtyDao extends IBaseDao<Specialty, Long>{

	/**
	 * 根据学校id查询学校专业
	 * @param schoolId
	 * @return
	 */
	public Specialty findSpecialty(@Param("schoolId") Long schoolId);
	/**
	 * 根据学校id删除专业
	 * @param schoolId
	 * @return
	 */
	public int deleteSpecialty(@Param("schoolId") Long schoolId);
	
}
