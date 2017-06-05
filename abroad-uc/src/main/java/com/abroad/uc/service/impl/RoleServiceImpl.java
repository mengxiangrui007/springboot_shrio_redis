package com.abroad.uc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.uc.dao.IRoleMapper;
import com.abroad.uc.domain.Role;
import com.abroad.uc.service.IRoleService;
@Transactional(readOnly=true)
@Service("iRoleService")
public class RoleServiceImpl extends AbstractService<Role, Long> implements IRoleService {
	@Autowired
	private  IRoleMapper iRoleMapper;
	@Override
	public void setBaseDao() {
		this.baseDao = iRoleMapper;
	}
	@Override
	public List<Role> queryRoleListByUserId(long userId) throws ServiceException {
		// TODO Auto-generated method stub
		return iRoleMapper.queryRoleListByUserId(userId);
	}

}
