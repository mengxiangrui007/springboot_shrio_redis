package com.abroad.uc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.abroad.common.dao.IBaseDao;
import com.abroad.uc.domain.Org;

/**
* @ClassName: IOrgMapper
* @Description: TODO(组织查询Mapper)
* @author: mengxr
* @date 2017年3月27日 上午11:49:39
*/
public interface IOrgMapper extends IBaseDao<Org,Long>{
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
	List<Long> queryOrgIDsChildren(@Param("orgId") long orgId) ;
}
