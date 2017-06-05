package com.abroad.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.comn.Tree;
import com.abroad.common.dao.IBaseDao;
import com.abroad.uc.domain.Group;

/**
* @ClassName: IGroupMapper
* @Description: TODO(集团查询Mapper)
* @author: mengxr
* @date 2017年3月27日 上午11:49:39
*/
public interface IGroupMapper extends IBaseDao<Group,Long>{

	/**只查询到下一级子节点
	* @Title: queryGroupTree
	* @Description: TODO(查询树结构)
	* @param @param groupId
	* @param @param orgId
	* @param @param id
	* @param @return    设定文件
	* @return List<AbroadTree>    返回类型
	* @author: mengxr
	* @date 2017年3月27日 下午12:09:21
	* @throws
	*/
	List<Tree> queryGroupTree(long groupId, long id)  ;
	/**
	* @Title: queryGroupChildren
	* @Description: TODO(查询当前所拥有的子集团)
	* @param @param groupId
	* @param @return
	* @param @throws Exception    设定文件
	* @return List<Long>    返回类型
	* @author: mengxr
	* @date 2017年4月13日 下午5:23:04
	* @throws
	*/
	List<Long> queryGroupIDsChildren(@Param("groupId") long groupId) ;
	/**
	* @Title: queryGroupChildren
	* @Description: TODO(查询所有的公司)
	* @param @return
	* @param @    设定文件
	* @return List<Group>    返回类型
	* @author: mengxr
	* @date 2017年4月19日 上午11:57:31
	* @throws
	*/
	List<Group> queryGroupChildren();
}
