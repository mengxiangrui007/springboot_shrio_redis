package com.abroad.school.domain;

import java.io.Serializable;
/**
 * 学校概况
 * @author shichangjian
 *
 */
public class SchoolOverview implements Serializable{

	private static final long serialVersionUID = 1390736638975352094L;
	
	private Long id;
	private Long schoolId;			//学校id
	private String history;			//学校历史
	private Long schoolTypeCode;	//学校类型
	private String specialty;		//学校特色
	private double enrollRate;		//录取率
	private double sat;				//录取SAP平均分
	private double toefl;			//录取托福平均分
	private String stay;			//住宿情况
	private String des;				//地理位置及人文
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(long schoolId) {
		this.schoolId = schoolId;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public long getSchoolTypeCode() {
		return schoolTypeCode;
	}
	public void setSchoolTypeCode(long schoolTypeCode) {
		this.schoolTypeCode = schoolTypeCode;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public double getEnrollRate() {
		return enrollRate;
	}
	public void setEnrollRate(double enrollRate) {
		this.enrollRate = enrollRate;
	}
	public double getSat() {
		return sat;
	}
	public void setSat(double sat) {
		this.sat = sat;
	}
	public double getToefl() {
		return toefl;
	}
	public void setToefl(double toefl) {
		this.toefl = toefl;
	}
	public String getStay() {
		return stay;
	}
	public void setStay(String stay) {
		this.stay = stay;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
