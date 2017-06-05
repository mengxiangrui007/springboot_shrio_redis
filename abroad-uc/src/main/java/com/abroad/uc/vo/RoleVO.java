package com.abroad.uc.vo;

import java.io.Serializable;

import com.abroad.common.vo.OperateVO;

/**
* @ClassName: RoleVO
* @Description: TODO(这里用一句话描述这个类的作用)
* @author: mengxr
* @date 2017年4月21日 下午4:40:02
*/
public class RoleVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;//角色主键
	private String code;//角色编码
	private String name;//角色名称
	private Short type;//角色类型
	private Long groupId;//用户所在集团
	private Long orgId;//用户所在组织
	private String note;//备注
	private OperateVO ope;//操作VO
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public OperateVO getOpe() {
		return ope;
	}
	public void setOpe(OperateVO ope) {
		this.ope = ope;
	}
}
