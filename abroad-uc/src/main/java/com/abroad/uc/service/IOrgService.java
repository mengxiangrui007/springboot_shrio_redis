package com.abroad.uc.service;

import java.util.List;

import com.abroad.common.exception.ServiceException;

/**
* @ClassName: IOrgService
* @Description: TODO(组织服务接口)
* @author: mengxr
* @date 2017年4月13日 下午5:31:43
*/
public interface IOrgService {
	/**
	* @Title: queryGroupChildren
	* @Description: TODO(查询当前所拥有的子组织)
	* @param @param groupId
	* @param @return
	* @param @throws Exception    设定文件
	* @return List<Long>    返回类型
	* @author: mengxr
	* @date 2017年4月13日 下午5:23:04
	* @throws
	*/
	List<Long> queryOrgIDsChildren(long orgId) throws ServiceException;
}
