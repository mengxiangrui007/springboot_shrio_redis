package com.abroad.hr.vo;

import java.io.Serializable;

import com.abroad.hr.domain.UserBase;

public class ResponseUserBaseVo implements Serializable{

	
	private static final long serialVersionUID = -8791941229077667657L;
	private UserBase userBase;	//用户基本信息
	private String country;		//国家
	private String city;		//城市
	private String userType;	//用户类型
	
	public UserBase getUserBase() {
		return userBase;
	}
	public void setUserBase(UserBase userBase) {
		this.userBase = userBase;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
