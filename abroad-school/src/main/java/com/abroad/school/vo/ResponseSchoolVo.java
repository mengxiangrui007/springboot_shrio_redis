package com.abroad.school.vo;

import java.io.Serializable;

import com.abroad.school.domain.School;

public class ResponseSchoolVo implements Serializable{

	
	private static final long serialVersionUID = -2062193467599557876L;
	private School school;	//学校
	private String country;	//国家
	private String province;//省
	private String city;	//城市
	
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 
	 
	
	
}
