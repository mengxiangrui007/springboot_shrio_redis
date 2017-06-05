package com.abroad.uc.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.Sequence;
import com.abroad.uc.dao.IRoleMapper;
import com.abroad.uc.domain.Role;
import com.abroad.uc.service.IRoleService;
import com.abroad.uc.vo.RoleVO;
@Transactional(readOnly=true)
@Service("iRoleService")
public class RoleServiceImpl extends AbstractService<Role, Long> implements IRoleService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private  IRoleMapper iRoleMapper;
	@Override
	public void setBaseDao() {
		this.baseDao = iRoleMapper;
	}
	
	@Override
	public List<Role> queryRoleListByUserId(long userId) throws ServiceException {
		 return iRoleMapper.queryRoleListByUserId(userId);
	}
	@Override
	public List<Long> queryRoleIdsByUserId(long userId) throws ServiceException {
		 return  iRoleMapper.queryRoleIdsByUserId(userId);
	}
	@Transactional
	@Override
	public boolean insertRole(RoleVO role) throws ServiceException {
		Integer roleCount = iRoleMapper.queryRoleByName(role); //说明本集团本组织下已经存在该角色名称
		if(null != roleCount && roleCount > 0){
			logger.error("添加角色名称为[{}]发生异常本集团下已经存在该角色名称",role.getName());
			throw new ServiceException("roleService.roleNameExist");
		}
		boolean flag = false;
		try {
			role.setId(Sequence.generateId());
			flag =  iRoleMapper.insert(role) > 0 ? true : false;
		} catch (Exception e) {
			logger.error("添加角色名称[{}]发生异常：",role.getName(),e);
			throw new ServiceException("roleService.insertRoleError");
		}
		return flag;
	}
	@Override
	public boolean deleteRole(Long id) throws ServiceException {
		return false;
	}

}
