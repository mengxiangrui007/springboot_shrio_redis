package com.abroad.common.dao;

import java.io.Serializable;


/**
* @ClassName: IBaseDAO
* @Description: TODO(数据库底层BaseDao公共类)
* @author: mengxr
* @date 2017年2月22日 下午4:52:16
*/
public interface IBaseDao<T,ID extends Serializable> {
	/**
	 * 注意此处删除时对数据库  dr字段进行了修改
	* @Title: deleteById
	* @Description: TODO(删除通过ID)
	* @param @param id
	* @param @return    设定文件
	* @return int    返回类型 大于0时删除成功
	* @author: mengxr
	* @date 2017年2月22日 下午4:53:44
	* @throws
	*/
	int deleteById(ID id);
	/**
	* @Title: insert
	* @Description: TODO(插入一个实体向数据库)
	* @param @param t
	* @param @return    设定文件
	* @return int     返回类型 大于0时插入成功
	* @author: mengxr
	* @date 2017年2月22日 下午4:54:07
	* @throws
	*/
	int insert(Object entity);
	/**
	* @Title: findById
	* @Description: TODO(查询实体通过实体主键)
	* @param @param id
	* @param @return    设定文件
	* @return T    返回类型
	* @author: mengxr
	* @date 2017年2月22日 下午4:55:23
	* @throws
	*/
	T findById(ID id);
	/**
	* @Title: updateEntity
	* @Description: TODO(更新一个实体)
	* @param @param entity
	* @param @return    设定文件
	* @return int    返回类型   大于0时更新成功
	* @author: mengxr
	* @date 2017年2月22日 下午4:56:10
	* @throws
	*/
	int update(Object entity);
}
