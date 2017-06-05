package com.abroad.uc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.common.tools.Sequence;
import com.abroad.uc.dao.IUserMapper;
import com.abroad.uc.domain.User;
import com.abroad.uc.service.IUserService;
import com.abroad.uc.tools.PasswordHelper;
import com.abroad.uc.vo.UserVO;

@Transactional(readOnly=true)
@Service("iUserService")
public class UserServiceImpl extends AbstractService<User, Long> implements IUserService {
	@Autowired
	private IUserMapper iUserMapper;

	@Override
	public void setBaseDao() {
		super.baseDao = this.iUserMapper;
	}
	@Override
	public User login(String username) throws ServiceException{
		User login = iUserMapper.login(username);
		return login;
	}
	@Override
	@Transactional
	public boolean insertUser(UserVO user) throws ServiceException {
		user.setId(Sequence.generateId());
		PasswordHelper.encryptPassword(user);
		return iUserMapper.insert(user) > 0 ? true : false;
	}

	
	
	
	
	
	
	
	
	


//	/**
//	 * 查找账户
//	 * @param account
//	 * @return
//	 * @throws ServiceException
//	 */
//	public Account findAccount(String telephone) throws ServiceException{
//		logger.info("[info message] method:findAccount");
//		
//		if(null == telephone || telephone.equals("")){
//			logger.error("[error message] findAccount.account是Null");
//			throw new ServiceException("account.findAccount.accountIsNull");
//		}
//		
//		return iAccountService.findAccount(telephone);
//	}
//	/**
//	 * 修改密码
//	 * @param account
//	 * @return
//	 */
//	@Transactional
//	public void updateAccount(Long accountId,String newPassword) throws ServiceException{
//		logger.info("[info message] method:updateAccount");
//		
//		Account account = iAccountService.findById(accountId);
//		//校验密码
//		//......
//		
//		account.setPassword(newPassword);  
//		PasswordHelper.encryptPassword(account);  
//		//修改账号
//		int count = iAccountService.update(account);
//		if(count <= 0){
//			logger.error("[error message] 修改账号异常");
//			throw new ServiceException("juntai-rms.updateAccountException");
//		}
//		
//	}
//	/**
//	 * 删除账号
//	 * @param accountId
//	 * @throws ServiceException
//	 */
//	@Transactional
//	public void deleteAccount(Long accountId) throws ServiceException{
//		logger.info("[info message] method:deleteAccount");
//		if(null == accountId){
//			
//		}
//		//删除权限校验
//		//......
//		
//		//删除账号
//		int count = iAccountService.deleteById(accountId);
//		if(count <= 0){
//			logger.error("[error message]删除账号异常");
//			throw new ServiceException("juntai-rms.deleteAccountException");
//		}
//		
//	}
}
