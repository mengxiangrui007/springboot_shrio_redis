package com.abroad.uc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.comn.Page;
import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.Sequence;
import com.abroad.uc.dao.IUserMapper;
import com.abroad.uc.domain.User;
import com.abroad.uc.service.IUserService;
import com.abroad.uc.tools.PasswordHelper;
import com.abroad.uc.vo.UserVO;

@Transactional(readOnly = true)
@Service("iUserService")
public class UserServiceImpl extends AbstractService<User, Long> implements
		IUserService {
	@Autowired
	private IUserMapper iUserMapper;

	@Override
	public void setBaseDao() {
		super.baseDao = this.iUserMapper;
	}

	@Override
	public User login(String username) throws ServiceException {
		User login = null;
		login = iUserMapper.login(username);
		return login;
	}

	@Override
	@Transactional
	public boolean insertUser(UserVO user) throws ServiceException {
		boolean flag = false;
		user.setId(Sequence.generateId());
		PasswordHelper.encryptPassword(user);
		flag = iUserMapper.insert(user) > 0 ? true : false;
		return flag;
	}

	@Override
	public Page queryUserInfo(UserVO user, int page, int pageSize)
			throws ServiceException {
		sum = iUserMapper.queryConditionCount(user);
		pager = new Page(sum, page, pageSize);
		List<User> userList = iUserMapper.queryDomainList(user,
				pager.getStart(), pageSize);
		pager.setRows(userList);
		return pager;
	}

}
