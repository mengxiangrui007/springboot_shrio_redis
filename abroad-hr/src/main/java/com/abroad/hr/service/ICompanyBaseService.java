package com.abroad.hr.service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.IBaseService;
import com.abroad.hr.domain.CompanyBase;

public interface ICompanyBaseService extends IBaseService<CompanyBase,Long>{

	/**
	* @Title: addCompanyBase
	* @Description: TODO(添加企业级信息)
	* @param @param companyBase
	* @param @throws ServiceException    设定文件
	* @return void    返回类型
	* @author: shicj
	* @date 2017年4月22日 下午4:21:25
	* @throws
	*/
	public void addCompanyBase(CompanyBase companyBase) throws ServiceException;
	/**
	* @Title: deleteCompanyBase
	* @Description: TODO(删除企业级信息)
	* @param @param id
	* @param @return
	* @param @throws ServiceException    设定文件
	* @return boolean    返回类型
	* @author: shicj
	* @date 2017年4月19日 下午7:37:09
	* @throws
	*/
	public int deleteCompanyBase(Long id) throws ServiceException;
	/**
	* @Title: findCompanyBase
	* @Description: TODO(查询企业级信息)
	* @param @param id
	* @param @return
	* @param @throws ServiceException    设定文件
	* @return CompanyBase    返回类型
	* @author: shicj
	* @date 2017年4月19日 下午7:38:13
	* @throws
	*/
	public CompanyBase findCompanyBase(Long id) throws ServiceException;
	/**
	* @Title: updateCom2panyBase
	* @Description: TODO(更新企业级信息)
	* @param @param companyBase
	* @param @return
	* @param @throws ServiceException    设定文件
	* @return boolean    返回类型
	* @author: shicj
	* @date 2017年4月19日 下午7:38:38
	* @throws
	*/
	public void updateCompanyBase(CompanyBase companyBase) throws ServiceException;
}
