package com.abroad.hr.vo;

import com.abroad.common.vo.OperateVO;

/**
* @ClassName: UserVO
* @Description: TODO(用户前台VO)
* @author: mengxr
* @date 2017年3月25日 下午3:36:38
*/
public class UserVO {
	private Long id;//用户主键
	private String code;//用户编码
	private String account;//用户账号
	private String email;//邮箱
	private String mobile;//电话
	private String password;//密码
	private String name;//用户姓名
	private Short islocked;//用户是否被锁定
	private String salt;	// 加密密码的盐,salt主要是用来进行密码加密
	private Long pk_hr_group;//用户所在集团
	private Long pk_hr_userdetail;//用户详细信息
	private Long pk_hr_org;//用户所在组织
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
	public Long getPk_hr_group() {
		return pk_hr_group;
	}
	public void setPk_hr_group(Long pk_hr_group) {
		this.pk_hr_group = pk_hr_group;
	}
	public Long getPk_hr_org() {
		return pk_hr_org;
	}
	public void setPk_hr_org(Long pk_hr_org) {
		this.pk_hr_org = pk_hr_org;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getPk_hr_userdetail() {
		return pk_hr_userdetail;
	}
	public void setPk_hr_userdetail(Long pk_hr_userdetail) {
		this.pk_hr_userdetail = pk_hr_userdetail;
	}
}
