package com.abroad.school.domain;

import java.io.Serializable;
/**
 * 学校——专业关系
 * @author shichangjian
 *
 */
public class Specialty implements Serializable{

	private static final long serialVersionUID = -6727127791926164541L;
	
	private Long id;
	private Long schoolId;				//学校id
	private String specialty;			//专业名
	private String des;					//描述
	private Long educationLevelCode;	//学历等级
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Long getEducationLevelCode() {
		return educationLevelCode;
	}
	public void setEducationLevelCode(Long educationLevelCode) {
		this.educationLevelCode = educationLevelCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	 
	
	
}
