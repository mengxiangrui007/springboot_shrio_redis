package com.abroad.uc.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.FblMapResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.exception.ServiceException;
import com.abroad.uc.dao.IPermissionMapper;
import com.abroad.uc.domain.MenuPermission;
import com.abroad.uc.domain.OperationPermission;
import com.abroad.uc.service.IPermissionService;

@Transactional(readOnly=true)
@Service("iPermissionService")
public class PermissionServiceImpl implements IPermissionService {
	@Autowired
	private  IPermissionMapper iPermissionMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory; 
	@Override
	public List<MenuPermission> queryMenuPermissByUserId(long userId)
			throws ServiceException {
		return iPermissionMapper.queryMenuPermissByUserId(userId);
	}
	@Override
	public List<OperationPermission> queryOperPermissByUserId(long userId)
			throws ServiceException {
		return iPermissionMapper.queryOperPermissByUserId(userId);
	}

	@Override
	public Map<String, String> queryMouduleChainDefinition(String mouduleCode)
			throws ServiceException {
		 SqlSession openSession = sqlSessionFactory.openSession();
		  FblMapResultHandler fbl = new FblMapResultHandler();  
		  openSession.select("queryMouduleChainDefinition", mouduleCode,fbl) ;
		  @SuppressWarnings("unchecked")
		  Map<String,String> map =fbl.getMappedResults();
		  openSession.close();
		  return map;  
	}
	
}
