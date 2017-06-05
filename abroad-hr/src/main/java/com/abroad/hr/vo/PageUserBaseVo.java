package com.abroad.hr.vo;


import com.abroad.hr.domain.UserBase;
import com.abroad.hr.domain.UserJob;
import com.abroad.hr.domain.UserLink;

/**
 * 用户基本信息包装类
 * @author shichangjian
 *
 */
public class PageUserBaseVo {

	private UserBase userBase;	//用户基本信息
	private UserLink userLink;	//用户联系信息
	private UserJob userJob;	//用户职业
	
	public UserBase getUserBase() {
		return userBase;
	}
	public void setUserBase(UserBase userBase) {
		this.userBase = userBase;
	}
	public UserLink getUserLink() {
		return userLink;
	}
	public void setUserLink(UserLink userLink) {
		this.userLink = userLink;
	}
	public UserJob getUserJob() {
		return userJob;
	}
	public void setUserJob(UserJob userJob) {
		this.userJob = userJob;
	}
	
	
	
	
}
