package com.abroad.security.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.abroad.common.comn.Tree;
import com.abroad.common.tools.TreeNodeUtil;
import com.abroad.common.vo.ShiroUser;
import com.abroad.security.shiro.redis.RedisSessionDao;
import com.abroad.uc.domain.MenuPermission;
import com.abroad.uc.domain.OperationPermission;
import com.abroad.uc.domain.Role;
import com.abroad.uc.domain.User;
import com.abroad.uc.service.IGroupService;
import com.abroad.uc.service.IOrgService;
import com.abroad.uc.service.IPermissionService;
import com.abroad.uc.service.IRoleService;
import com.abroad.uc.service.IUserService;

/**
 * @ClassName: AbroadRealm
 * @Description: TODO(对用户进行认证和权限校验)
 * @author: mengxr
 * @date 2017年3月27日 下午7:50:14
 */
public class AbroadRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisSessionDao redisSessionDao;
	// 获取账号
	@Autowired
	private IUserService iAccountService;
	//获取登录用户的权限
	@Autowired
	private IPermissionService iPermissionService;
	@Autowired
	private IRoleService iRoleService; 
	@Autowired
	private IGroupService iGroupService;
	@Autowired
	private IOrgService iOrgService;
	
	/**
	 * @Title: doGetAuthenticationInfo
	 * @Description: TODO(进行登录认证)Subject.login()会触发
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		if (token instanceof UsernamePasswordToken) {
			UsernamePasswordToken usernameToken = (UsernamePasswordToken) token;
			String username = usernameToken.getUsername();
			if (username == null) {
				logger.info("login username:{} not exist", username);
				throw new UnknownAccountException();
			} else {
					User user = null;
					ShiroUser shiroUser = null;
					try {
						user = iAccountService.login(username);
						if(null == user){
							throw new UnknownAccountException();
						}
						shiroUser = this.setShiroUserInfo(user);
					} catch (Exception e) {
						throw new AuthenticationException(e);
					}
					char[] password = user.getPassword().toCharArray();
					String salt = user.getSalt();// 获取salt
					return new SimpleAuthenticationInfo(shiroUser ,
							password, ByteSource.Util.bytes(salt), this.getName());
			}
		}
		return null;
	}

	/**
	* @Title: setShiroUserInfo
	* @Description: TODO(设置ShrioUser的信息)
	* @param @param user
	* @param @return    设定文件
	* @return ShiroUser    返回类型
	* @author: mengxr
	* @date 2017年4月13日 下午4:27:42
	* @throws
	*/
	private ShiroUser setShiroUserInfo(User user) {
		ShiroUser shiroUser = new ShiroUser();
		try {
			long userId = user.getId();
			Long groupId = user.getGroupId();
			Long orgId = user.getOrgId();
			shiroUser.setAccount(user.getAccount());
			shiroUser.setId(userId);
			shiroUser.setGroupId(groupId);
			shiroUser.setOrgId(orgId);
			shiroUser.setCode(user.getCode());
			shiroUser.setIslocked(user.getIslocked());
			//获取用户所拥有的角色
			List<Long> roleIds = iRoleService.queryRoleIdsByUserId(userId);
			shiroUser.setRoles(roleIds);
			//获取用户所拥有菜单树
			List<MenuPermission> menuPermissions = iPermissionService.queryMenuPermissByUserId(userId);
			List<Tree> menuTrees = this.getMenuTree(menuPermissions);
			shiroUser.setMenuTrees(menuTrees);
			//设置所拥有的子集团
			if(null !=groupId){
				List<Long> groupIDsChildren = iGroupService.queryGroupIDsChildren(groupId);
				shiroUser.setGroups(groupIDsChildren);
			}
			if(null != orgId){
				List<Long> orgIDsChildren = iOrgService.queryOrgIDsChildren(orgId);
				shiroUser.setOrgs(orgIDsChildren);
			}
			logger.info("set [{}]  get ShiroUser success",shiroUser.getAccount());
		} catch (Exception e) {
			logger.error("set  [{}] account ShiroUser info error: ",user.getAccount(),e);
		}
		return shiroUser;
	}

	/**
	* @Title: getMenuTree
	* @Description: TODO(循环遍历得到菜单树)
	* @param @param menuPermissions
	* @param @return    设定文件
	* @return Tree    返回类型
	* @author: mengxr
	* @date 2017年4月13日 下午4:53:41
	* @throws
	*/
	private List<Tree> getMenuTree(List<MenuPermission> menuPermissions) {
		List<Tree> reverseTree = TreeNodeUtil.reverseTree(menuPermissions);
		return TreeNodeUtil.getfatherNode(reverseTree);
	}

	/**
	 * @Title: doGetAuthorizationInfo
	 * @Description: TODO(查询具体的权限)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		long id = shiroUser.getId();
		try {
			List<OperationPermission> operationPermissions = iPermissionService.queryOperPermissByUserId(id);
			List<Role> rolePermissions = iRoleService.queryRoleListByUserId(id);
			info.addRoles(this.getRoleCodes(rolePermissions));
			info.addStringPermissions(this.getOperationCodes(operationPermissions));
			info.addObjectPermissions(new HashSet<Permission>(operationPermissions));
			logger.info("set [{}] account get ShiroUser success",shiroUser.getAccount());
		} catch (Exception e) {
			logger.error("query  [{}] account AuthorizationInfo permission success ", shiroUser.getAccount(),e);
		}
		return info;
	}
	/**
	* @Title: getOperationCodes
	* @Description: TODO(得到操作权限Code集合)
	* @param @return    设定文件
	* @return Collection<String>    返回类型
	* @author: mengxr
	* @date 2017年4月12日 下午9:43:09
	* @throws
	*/
	private Collection<String> getOperationCodes(List<OperationPermission> operationPermissions){
		Set<String> codeSets = new HashSet<String>(operationPermissions.size());
		for(OperationPermission permission : operationPermissions){
			codeSets.add(permission.getUrl());
		}
		return codeSets;
	}
	/**
	* @Title: getRoleCodes
	* @Description: TODO(得到角色Code集合)
	* @param @return    设定文件
	* @return Collection<String>    返回类型
	* @author: mengxr
	* @date 2017年4月12日 下午9:43:54
	* @throws
	*/
	private Collection<String> getRoleCodes(List<Role> rolePermissions){
		Set<String> codeSets = new HashSet<String>(rolePermissions.size());
		for(Role role : rolePermissions){
			codeSets.add(role.getCode());
		}
		return codeSets;
	}
}
