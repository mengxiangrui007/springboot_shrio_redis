package com.abroad.uc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.uc.dao.IOrgMapper;
import com.abroad.uc.domain.Org;
import com.abroad.uc.service.IOrgService;

/**
* @ClassName: OrgServiceImpl
* @Description: TODO(组织服务视实现类)
* @author: mengxr
* @date 2017年4月13日 下午5:33:13
*/
@Transactional(readOnly=true)
@Service("iOrgService")
public class OrgServiceImpl  extends AbstractService<Org, Long>  implements IOrgService{
	@Autowired
	private  IOrgMapper iOrgMapper;
	@Override
	public void setBaseDao() {
		super.baseDao = this.iOrgMapper;
	}
	@Override
	public List<Long> queryOrgIDsChildren(long orgId) throws ServiceException {
		 return iOrgMapper.queryOrgIDsChildren(orgId);
	}
}
