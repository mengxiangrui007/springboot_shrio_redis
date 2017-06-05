package com.abroad.school.service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.IBaseService;
import com.abroad.school.domain.Specialty;
import com.abroad.school.vo.ResponseSchoolSpecialtyVo;

public interface ISpecialtyService extends IBaseService<Specialty, Long>{

	/**
	 * 根据学校id查询学校专业
	 * @param schoolId
	 * @return
	 */
	public ResponseSchoolSpecialtyVo findSpecialty(Long schoolId) throws ServiceException;
	/**
	 * 修改学校专业
	 * @param specialty
	 * @return
	 */
	public int updateSpecialty(Specialty specialty);
	/**
	 * 根据学校id删除专业
	 * @param schoolId
	 * @return
	 */
	public int deleteSpecialty(Long schoolId);

}
