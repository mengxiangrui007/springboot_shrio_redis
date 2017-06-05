package com.abroad.hr.domain;

import java.io.Serializable;
/**
 * 顾问——专业关系
 * @author shichangjian
 *
 */
public class CounselorSpecialty implements Serializable{

	private static final long serialVersionUID = -3340163699364238445L;
	
	private Long id; 
	private Long userId;		//顾问id
  	private Long specialtyId;	//专业id
  	
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
	public Long getSpecialtyId() {
		return specialtyId;
	}
	public void setSpecialtyId(Long specialtyId) {
		this.specialtyId = specialtyId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
  	
	
  	
	
  	
	
	
  	
}
