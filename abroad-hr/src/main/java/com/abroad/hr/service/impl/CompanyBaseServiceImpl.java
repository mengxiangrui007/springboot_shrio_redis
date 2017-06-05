package com.abroad.hr.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.CacheEvict;
import com.abroad.common.tools.Cacheable;
import com.abroad.common.tools.Sequence;
import com.abroad.hr.dao.ICompanyBaseDao;
import com.abroad.hr.domain.CompanyBase;
import com.abroad.hr.service.ICompanyBaseService;

@Service
public class CompanyBaseServiceImpl extends AbstractService<CompanyBase,Long> implements ICompanyBaseService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ICompanyBaseDao iCompanyBaseDao;
	@Override
	public void setBaseDao() {
		super.baseDao = iCompanyBaseDao;
	}
	
	public void addCompanyBase(CompanyBase companyBase) throws ServiceException{
		logger.info("[info message] method:addCompanyBase");
		//生成主键
		companyBase.setId(Sequence.generateId());
		if(iCompanyBaseDao.insert(companyBase) <= 0){
			logger.error("[error message] 添加企业级信息异常");
			throw new ServiceException("abroad-hr.insertException");
		}
	}
	
	
	@CacheEvict(key="getCompanyBase",fieldKey="#id")
	@Override
	public int deleteCompanyBase(Long id) throws ServiceException{
		if(null == id || id <= 0 )
			throw new ServiceException("abroad.idIsNull");
		return iCompanyBaseDao.deleteById(id);
	}
	@Override
	@Cacheable(key="getCompanyBase",fieldKey="#id")
	public CompanyBase findCompanyBase(Long id) throws ServiceException{
		if(null == id || id <= 0 )
			throw new ServiceException("abroad.idIsNull");
		return iCompanyBaseDao.findById(id);
	}
	@CacheEvict(key="getCompanyBase",fieldKey="#companyBase.getId()")
	@Override
	public void updateCompanyBase(CompanyBase companyBase) throws ServiceException{
		logger.info("[info message] method:updateCompanuBase");
		
		if(iCompanyBaseDao.update(companyBase) <= 0){
			logger.error("[error message] 修改企业级信息异常");
			throw new ServiceException("abroad-hr.updateException");
		}
	} 
	
}
