package com.abroad.school.service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.IBaseService;
import com.abroad.school.domain.School;
import com.abroad.school.vo.ResponseSchoolVo;

public interface ISchoolService extends IBaseService<School, Long>{

	/**
	 * 根据id查询学校信息
	 * @param id
	 * @return
	 */
	public ResponseSchoolVo findSchool(Long id) throws ServiceException;
	/**
	 * 修改学校信息
	 * @param school
	 * @return
	 */
	public int updateSchool(School school);
	/**
	 * 删除学校信息
	 * @param id
	 * @return
	 */
	public int deleteSchool(Long id);
	
}
