package com.abroad.uc.domain;


/**
* @ClassName: Menu
* @Description: TODO(菜单)
* @author: mengxr
* @date 2017年4月12日 下午8:26:49
*/
public class MenuPermission {
	private Long id;//菜单主键
	private String code;//菜单编码
	private String name;//菜单名称
	private Short type;//菜单类型
	private Long pid;//父菜单
	private String url;//访问路径
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
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
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
}
