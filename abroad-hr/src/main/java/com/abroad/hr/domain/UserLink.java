package com.abroad.hr.domain;

import java.io.Serializable;
/**
 * 用户联系信息
 * @author shichangjian
 *
 */
public class UserLink implements Serializable{

	private static final long serialVersionUID = 906120497607552881L;
	
	private Long id;			
	private Long userId;		//用户id
	private String telephone;	//手机
	private String email;		//常用邮箱
	private String emailBackup;//备用邮箱
	private String qq;			//QQ
	private String wechat;		//微信
	private String phone1;		//电话1
	private String phone2;		//电话2
	private String fax;			//传真
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailBackup() {
		return emailBackup;
	}
	public void setEmailBackup(String emailBackup) {
		this.emailBackup = emailBackup;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	 
	
}
