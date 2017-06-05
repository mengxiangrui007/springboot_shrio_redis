package com.abroad.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abroad.common.comn.ResponseJson;
import com.abroad.common.controller.BaseController;
import com.abroad.common.exception.AbroadException;
import com.abroad.uc.service.IRoleService;
import com.abroad.uc.vo.RoleVO;

/**
* @ClassName: RoleController
* @Description: TODO(角色交互类)
* @author: mengxr
* @date 2017年4月19日 上午10:07:06
*/
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<RoleVO>{

	@Autowired
	private IRoleService iRoleService;
	
	/**
	* @Title: insertRole
	* @Description: TODO(添加一个角色)
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年4月21日 下午4:45:27
	* @throws
	*/
	@RequestMapping(value = "/insertRole",method = RequestMethod.POST)
	public ResponseJson insertRole(@RequestBody RoleVO role) throws AbroadException{
		role.setOpe(super.getInsertOpeInfo());
		iRoleService.insertRole(role);
		return  ResponseJson.getSuccessResponse();
	}
	/**
	 * @throws AbroadException 
	* @Title: deleteRole
	* @Description: TODO(删除一个角色)
	* @param @param id
	* @param @return    设定文件
	* @return ResponseJson    返回类型
	* @author: mengxr
	* @date 2017年4月21日 下午5:59:46
	* @throws
	*/
	@RequestMapping(value = "/deleteRole",method = RequestMethod.DELETE)
	public ResponseJson deleteRole(@RequestParam("id") Long id) throws AbroadException{
		 iRoleService.deleteRole(id);
		return ResponseJson.getSuccessResponse();
	}
}
