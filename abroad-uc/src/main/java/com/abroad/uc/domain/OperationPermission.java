package com.abroad.uc.domain;

import org.apache.shiro.authz.Permission;

/**
* @ClassName: Operation
* @Description: TODO(功能权限)
* @author: mengxr
* @date 2017年4月12日 下午8:29:12
*/
public class OperationPermission implements Permission{
	private Long id;//功能主键
	private String code;//功能编码
	private String name;//功能名称
	private Short type;//功能类型
	private String url;//拦截url前缀
	private Long groupId;//用户所在集团
	private Long orgId;//用户所在组织
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	@Override
	public boolean implies(Permission p) {
		// TODO Auto-generated method stub
		return false;
	}
}
