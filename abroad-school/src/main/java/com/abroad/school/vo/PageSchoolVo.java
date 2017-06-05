package com.abroad.school.vo;

import com.abroad.school.domain.School;
import com.abroad.school.domain.SchoolOverview;
import com.abroad.school.domain.Specialty;

public class PageSchoolVo {

	private School school;					//学校
	private SchoolOverview schoolOverview;	//学校概述
	private Specialty specialty;			//学校专业
	
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public SchoolOverview getSchoolOverview() {
		return schoolOverview;
	}
	public void setSchoolOverview(SchoolOverview schoolOverview) {
		this.schoolOverview = schoolOverview;
	}
	public Specialty getSpecialty() {
		return specialty;
	}
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
	
}
