package com.abroad.uc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abroad.common.comn.Tree;
import com.abroad.common.exception.ServiceException;
import com.abroad.common.service.impl.AbstractService;
import com.abroad.uc.dao.ICountryMapper;
import com.abroad.uc.dao.IGroupMapper;
import com.abroad.uc.domain.Group;
import com.abroad.uc.service.IGroupService;
import com.abroad.uc.vo.CountryRegionTest;
import com.abroad.uc.vo.GroupVO;

/**
* @ClassName: GroupServiceImpl
* @Description: TODO(集团服务类实现接口)
* @author: mengxr
* @date 2017年3月27日 上午10:37:22
*/
@Transactional(readOnly=true)
@Service("iGroupService")
public class GroupServiceImpl  extends AbstractService<Group, Long>  implements IGroupService{
	@Autowired
	private  IGroupMapper iGroupMapper;
	@Autowired
	private  ICountryMapper iCountryMapper;
	
	@Override
	public void setBaseDao() {
		super.baseDao = this.iGroupMapper;
	}
	@Override
	public List<Tree> queryGroupTree(long groupId, long id) throws ServiceException{
		 return  iGroupMapper.queryGroupTree(groupId,id);
	}
	@Transactional
	@Override
	public boolean insertGroup(GroupVO group) throws ServiceException {
		return super.insert(group);
	}
	@Override
	public Group queryGroup(Long id) throws Exception {
		return iGroupMapper.findById(id);
	}
	@Override
	public List<Long> queryGroupIDsChildren(long groupId) throws ServiceException {
		 return iGroupMapper.queryGroupIDsChildren(groupId);
	}
	@Override
	public List<Group> queryGroupChildren() throws ServiceException {
		return iGroupMapper.queryGroupChildren();
	}
	@Override
	public void test(List<CountryRegionTest> countryRegionTests) throws ServiceException {
		 iCountryMapper.updateCountry(countryRegionTests);
	}
	
}
