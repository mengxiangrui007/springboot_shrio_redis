package com.abroad.common.vo;

import java.io.Serializable;
import java.util.List;

import com.abroad.common.comn.Tree;

/**
 * 
* @ClassName: Account
* @Description: TODO(Account登陆信息)
* @author: mengxr
* @date 2017年3月27日 上午10:27:11
*/
public class ShiroUser implements Serializable{
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private long id;//用户主键
	private String code;//用户编码
	private String account;//用户账号
	private String name;//用户姓名
	private Short islocked;//用户是否被锁定
	private long groupId;//用户所在集团
	private long orgId;//用户所在组织
	private List<Long> roles;//用户所拥有的角色
	private List<Long> orgs;//用户所拥有的组织
	private List<Long> groups;//集团
	private List<Tree> menuTrees;//用户所拥有的菜单树
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getIslocked() {
		return islocked;
	}
	public void setIslocked(Short islocked) {
		this.islocked = islocked;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Long> getRoles() {
		return roles;
	}
	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}
	public List<Long> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<Long> orgs) {
		this.orgs = orgs;
	}
	public List<Long> getGroups() {
		return groups;
	}
	public void setGroups(List<Long> groups) {
		this.groups = groups;
	}
	public List<Tree> getMenuTrees() {
		return menuTrees;
	}
	public void setMenuTrees(List<Tree> menuTrees) {
		this.menuTrees = menuTrees;
	}
}
