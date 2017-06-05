package com.abroad.school.vo;

import com.abroad.school.domain.SchoolOverview;

public class ResponseSchoolOverviewVo {

	private SchoolOverview schoolOverview;	//学校概述
	private String schoolType;				//学校类型
	
	public SchoolOverview getSchoolOverview() {
		return schoolOverview;
	}
	public void setSchoolOverview(SchoolOverview schoolOverview) {
		this.schoolOverview = schoolOverview;
	}
	public String getSchoolType() {
		return schoolType;
	}
	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}
	
}
