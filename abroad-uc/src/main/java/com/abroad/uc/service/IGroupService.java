package com.abroad.uc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.comn.Tree;
import com.abroad.common.exception.ServiceException;
import com.abroad.uc.domain.Group;
import com.abroad.uc.vo.GroupVO;

/**
* @ClassName: IGroupService
* @Description: TODO(集团服务接口)
* @author: mengxr
* @date 2017年3月27日 上午10:36:21
*/
public interface IGroupService {

	/**
	 * 只查询到下一级子节点
	* @Title: queryGroupTree
	* @Description: TODO(查询集团树结构)
	* @param @param groupId 集团主键
	* @param @param id 
	* @param @return    设定文件
	* @return List<AbroadTree>    返回类型
	* @author: mengxr
	* @date 2017年3月27日 下午12:07:08
	* @throws
	*/
	List<Tree> queryGroupTree(long groupId,  long id)  throws ServiceException;

	/**
	* @Title: insertGroup
	* @Description: TODO(添加集团的信息)
	* @param @param group
	* @param @return
	* @param @throws Exception    设定文件
	* @return boolean    返回类型
	* @author: mengxr
	* @date 2017年3月27日 下午4:05:21
	* @throws
	*/
	boolean insertGroup(GroupVO group) throws ServiceException;

	/**
	* @Title: queryGroup
	* @Description: TODO(查询集团信息)
	* @param @param id
	* @param @return
	* @param @throws Exception    设定文件
	* @return Group    返回类型
	* @author: mengxr
	* @date 2017年3月27日 下午3:48:27
	* @throws
	*/
	Group queryGroup(Long id) throws ServiceException;
	/**
	* @Title: queryGroupChildren
	* @Description: TODO(查询当前所拥有的子集团)
	* @param @param groupId
	* @param @return
	* @param @throws Exception    设定文件
	* @return List<String>    返回类型
	* @author: mengxr
	* @date 2017年4月13日 下午5:23:04
	* @throws
	*/
	List<Long> queryGroupIDsChildren(@Param("groupId") long groupId) throws ServiceException;
}
