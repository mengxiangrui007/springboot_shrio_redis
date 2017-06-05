package com.abroad.common.service.impl;

import java.io.Serializable;

import com.abroad.common.comn.Page;
import com.abroad.common.dao.IBaseDao;
import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.IBaseService;

/**
* @ClassName: AbstractService
* @Description: TODO(AbstractService抽象服务类)
* @author: mengxr
* @date 2017年2月22日 下午4:59:25
*/
public  abstract class AbstractService<T,ID extends Serializable> implements IBaseService<T, ID>{
	/**
	* @Fields baseDao : TODO(数据库访问公共类)
	*/
	protected IBaseDao<T,ID> baseDao;
	
	//由子类决定具体要做的功能
	public abstract void setBaseDao();
	
	/**
	 * @Fields sum : TODO(总页数)
	 */
	protected int sum = 0;
	/**
	 * @Fields pager : TODO(page)
	 */
	protected Page pager = null;
	
	@Override
	public boolean deleteById(ID id) throws ServiceException {
		setBaseDao();
		return baseDao.deleteById(id) > 0 ? true :false; 
	}
	@Override
	public boolean insert(Object entity) throws ServiceException {
		setBaseDao();
		return baseDao.insert(entity) > 0 ? true :false; 
	}

	@Override
	public T findById(ID id) throws ServiceException {
		 setBaseDao();
		 return baseDao.findById(id); 
	}

	@Override
	public boolean update(Object entity)  throws ServiceException {
		setBaseDao();
		return baseDao.update(entity) > 0 ? true :false; 
	}
}
