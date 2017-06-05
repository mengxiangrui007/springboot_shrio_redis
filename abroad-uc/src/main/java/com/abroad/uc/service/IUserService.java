package com.abroad.uc.service;



import com.abroad.common.exception.ServiceException;
import com.abroad.uc.domain.User;
import com.abroad.uc.vo.UserVO;

/**
* @ClassName: IUserService
* @Description: TODO(登录接口)
* @author: mengxr
* @date 2017年3月30日 下午4:26:05
*/
public interface IUserService {
	/**
	* @Title: login
	* @Description: TODO(登录接口)
	* @param @param username
	* @param @return    设定文件
	* @return User    返回类型
	* @author: mengxr
	* @date 2017年3月30日 下午4:27:54
	* @throws
	*/
	User login(String username) throws ServiceException;
	/**
	* @Title: insertUser
	* @Description: TODO(添加用户)
	* @param @param user
	* @param @return
	* @param @throws Exception    设定文件
	* @return boolean    返回类型
	* @author: mengxr
	* @date 2017年3月31日 上午9:57:47
	* @throws
	*/
	boolean insertUser(UserVO user) throws ServiceException;
		
}
