package com.abroad.school.vo;

import com.abroad.school.domain.Specialty;

public class ResponseSchoolSpecialtyVo {

	private Specialty specialty;	//学校专业
	private String educationLevel;	//学历等级
	
	public Specialty getSpecialty() {
		return specialty;
	}
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	
	
}
