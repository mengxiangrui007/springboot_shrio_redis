package com.abroad.common.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.abroad.common.vo.ShiroUser;
import com.abroad.common.vo.OperateVO;


/**
 * @ClassName: BaseAction
 * @Description: TODO(Action层面公共方法)
 * @author: mengxr
 * @company: 国美云事业部基础开发
 * @date 2017年2月24日 下午2:12:50
 */
public class BaseController<T> {
	// 获取当前的requert对象
	@Autowired
	private HttpServletRequest request;
	/**
	 * @Title: getPage
	 * @Description: TODO(获取当前的页数)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: mengxr
	 * @date 2017年3月3日 上午10:56:34
	 * @throws
	 */
	protected int getPage() {
		String pageStr = request.getParameter("page");
		return pageStr == null ? 0 : Integer.parseInt(pageStr);
	}

	/**
	 * @Title: getPageSize
	 * @Description: TODO(获取显示的页数)
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @author: mengxr
	 * @date 2017年3月3日 上午10:56:51
	 * @throws
	 */
	protected int getPageSize() {
		String pageSizeStr = request.getParameter("rows");
		return pageSizeStr == null ? 15 : Integer.parseInt(pageSizeStr);
	}

	/**
	* @Title: getOperateInfo
	* @Description: TODO(得到增加的操作的信息)
	* @param @return    设定文件
	* @return OperateVO    返回类型
	* @author: mengxr
	* @date 2017年3月27日 下午3:18:18
	* @throws
	*/
	protected OperateVO getInsertOpeInfo(){
		OperateVO operateVO =  new OperateVO();
		operateVO.setCratetime(new Date());
		ShiroUser shiroUser = getShiroUser();
		if(null != shiroUser){
			long userId = shiroUser.getId(); //创建者ID
			operateVO.setPk_hr_user(userId);
		}
		return operateVO;
	}
	/**
	 * 获取当前登录用户的Session信息
	* @Title: getAccount
	* @Description: TODO()
	* @param @return    设定文件
	* @return Account    返回类型
	* @author: mengxr
	* @date 2017年3月28日 下午8:39:50
	* @throws
	*/
	protected  ShiroUser getShiroUser(){
		return 	(ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}
	/**
	* @Title: queryUserGroups
	* @Description: TODO(获取当前用户所在的集团以及子集团)
	* @param @return    设定文件
	* @return List<Long>    返回类型
	* @author: mengxr
	* @date 2017年4月17日 上午10:46:15
	* @throws
	*/
	protected List<Long> getUserGroups() {
		return getShiroUser().getGroups();
	}
	/**
	* @Title: queryUserOrgs
	* @Description: TODO(获取当前用户所在的组织以及子组织)
	* @param @return    设定文件
	* @return List<Long>    返回类型
	* @author: mengxr
	* @date 2017年4月17日 上午10:46:15
	* @throws
	*/
	protected List<Long> getUserOrgs() {
		return getShiroUser().getOrgs();
	}
}
