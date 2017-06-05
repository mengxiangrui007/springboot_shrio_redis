package com.abroad.hr.domain;

import java.io.Serializable;
/**
 * 用户，职业
 * @author shichangjian
 *
 */
public class UserJob implements Serializable{

	private static final long serialVersionUID = 2260916306725196491L;
	
	private Long id;				
	private Long userId;			//用户id
	private String companyName;		//公司
	private String companyAddress;	//地址
	private String businessType;	//行业
	private String duty;			//职位
	
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	 
	
}
