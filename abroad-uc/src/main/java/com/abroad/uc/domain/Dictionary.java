package com.abroad.uc.domain;

import java.io.Serializable;
/**
 * 业务字典
 * @author shichangjian
 *
 */
public class Dictionary implements Serializable{

	private static final long serialVersionUID = 5130569004853288929L;
	
	private Long code;			//代码
	private String des;			//描述
	private Long parentCode;	//
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Long getParentCode() {
		return parentCode;
	}
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
}
