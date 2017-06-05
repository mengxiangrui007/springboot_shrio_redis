package com.abroad.uc.domain;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: User
* @Description: TODO(用戶domain)
* @author: mengxr
* @date 2017年3月30日 下午4:45:28
*/
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;//用户主键
	private String code;//用户编码
	private String account;//用户账号
	private String mobile;//电话
	private String password;//密码
	private String email;//email邮箱
	private Date createtime;//创建时间
	private String name;//用户姓名
	private Short islocked;//用户是否被锁定
	private String salt;	// 加密密码的盐,salt主要是用来进行密码加密
	private Long groupId;//用户所在集团
	private Long orgId;//用户所在组织
	private String note;//备注
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
