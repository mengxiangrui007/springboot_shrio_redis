package com.abroad.hr.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.tools.Sequence;
import com.abroad.hr.domain.UserBase;
import com.abroad.hr.domain.UserJob;
import com.abroad.hr.domain.UserLink;
import com.abroad.hr.service.IUserBaseService;
import com.abroad.hr.service.IUserJobService;
import com.abroad.hr.service.IUserLinkService;
import com.abroad.hr.vo.PageUserBaseVo;


@Service
public class UserBaseManager {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserBaseService iUserBaseService;
	@Autowired
	private IUserLinkService iUserLinkService;
	@Autowired
	private IUserJobService iUserJobService;
	/**
	 * 添加用户基本信息
	 * @param pageUserBaseVo
	 */
	@Transactional 
	public void addUserBase(PageUserBaseVo pageUserBaseVo) throws ServiceException{
		logger.info("[info message] method:addUserBase");
		UserBase userBase = pageUserBaseVo.getUserBase();
		//生成userBase主键
		userBase.setId(Sequence.generateId());
		iUserBaseService.insert(userBase);
		Long userBaseId = userBase.getId();
		if(null == userBaseId || userBaseId <= 0){
			logger.error("[error message] 用户基本信息添加异常!");
			throw new ServiceException("abroad.insertException");
		}
		
		UserLink userLink = pageUserBaseVo.getUserLink();
		userLink.setId(Sequence.generateId());
		userLink.setUserId(userBaseId);
		if(!iUserLinkService.insert(userLink)){
			logger.error("[error message] 添加用户联系信息异常！");
			throw new ServiceException("abroad.insertException");
		}
		
		UserJob userJob = pageUserBaseVo.getUserJob();
		userJob.setId(Sequence.generateId());
		userJob.setUserId(userBaseId);
		if(!iUserJobService.insert(userJob)){
			logger.error("[error message] 添加用户职业异常！");
			throw new ServiceException("abroad.insertException");
		}
	}
	/**
	 * 修改用户基本信息
	 * @param pageUserBaseVo
	 * @return
	 * @throws JuntaiException
	 */
	@Transactional
	public void updateUserBase(PageUserBaseVo pageUserBaseVo,Long accountId) throws ServiceException{
		logger.info("[info message] method:updateUserBase");
		if(null == pageUserBaseVo){
			throw new ServiceException("abroad.PageUserBaseVoNull");
		}
		UserBase userBase = pageUserBaseVo.getUserBase();
		if(null == userBase){
			throw new ServiceException("abroad.userBaseIsNull");
		}
		Long userBaseId = userBase.getId();
		if(null == userBaseId || userBaseId <= 0) {
			throw new ServiceException("abroad.userBaseIdIsNull");
		}
		//校验修改权限
//		UserBase base = iUserBaseService.findById(userBaseId);
//		if(!accountId.equals(base.getAccountId())){//或者accountId是管理员???
//			logger.error("[error message] 账号id不匹配，无修改权限");
//			throw new ServiceException("abroad.notPermission");
//		}
		//修改用户基本信息
		int baseCount = iUserBaseService.updateUserBase(userBase);
		if(baseCount <= 0){
			logger.error("[error message] 修改用户基本信息异常！");
			throw new ServiceException("abroad.updateUserBaseException");
		}
		
		UserLink userLink = pageUserBaseVo.getUserLink();
		if(null == userLink){
			throw new ServiceException("abroad.userLinkIsNull");
		}
		//修改用户联系信息
		userLink.setUserId(userBaseId);
		int linkCount = iUserLinkService.updateUserLink(userLink);
		if(linkCount <= 0){
			logger.error("[error message] 修改用户联系信息异常！");
			throw new ServiceException("abroad.updateUserLinkException");
		}
		
		UserJob userJob = pageUserBaseVo.getUserJob();
		if(null == userJob){
			throw new ServiceException("abroad.userJobIsNull");
		}
		//修改用户职业
		userJob.setUserId(userBaseId);
		int jobCount = iUserJobService.updateUserJob(userJob);
		if(jobCount <= 0){
			logger.error("[error message] 修改用户职业异常！");
			throw new ServiceException("abroad.updateUserJobException");
		}
	}
	/**
	 * 删除用户基本信息
	 * @param id
	 */
	@Transactional
	public void deleteUserBase(Long id,Long accountId) throws ServiceException{
		logger.info("[info message] method:deleteUserBase");
		if(null == id || id <= 0){
			throw new ServiceException("abroad.idIsNull");
		}
		//权限校验
		UserBase base = iUserBaseService.findById(id);
		if(!accountId.equals(base.getAccountId())){
			logger.error("[error message] 账号id不匹配，无删除权限");
			throw new ServiceException("abroad.notPermission");
		}
		
		int baseCount = iUserBaseService.deleteUserBase(id);
		if(baseCount <= 0){
			logger.error("[error message] 删除用户基本信息异常！");
			throw new ServiceException("abroad.deleteException");
		}
		int linkCount = iUserLinkService.deleteUserLink(id);
		if(linkCount <= 0){
			logger.error("[error message] 删除用户联系信息异常！");
			throw new ServiceException("abroad.deleteException");
		}
		int jobCount = iUserJobService.deleteUserJob(id);
		if(jobCount <= 0){
			logger.error("[error message] 删除用户职业异常！");
			throw new ServiceException("abroad.deleteException");
		}
	}
}
