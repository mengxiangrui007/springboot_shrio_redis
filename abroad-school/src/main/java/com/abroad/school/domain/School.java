package com.abroad.school.domain;

import java.io.Serializable;
/**
 * 学校基本信息
 * @author shichangjian
 *
 */
public class School implements Serializable{

	private static final long serialVersionUID = -7415867154596380791L;
	
	private Long  id;
	private String schoolNameCh;	//学校名/中
	private String schoolNameEn;	//学校名/英
	private Long countryCode;		//国家
	private Long provinceCode;		//省
	private Long cityCode;			//市/州
	private String des;				//描述
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSchoolNameCh() {
		return schoolNameCh;
	}
	public void setSchoolNameCh(String schoolNameCh) {
		this.schoolNameCh = schoolNameCh;
	}
	public String getSchoolNameEn() {
		return schoolNameEn;
	}
	public void setSchoolNameEn(String schoolNameEn) {
		this.schoolNameEn = schoolNameEn;
	}
	public long getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(long countryCode) {
		this.countryCode = countryCode;
	}
	public long getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(long provinceCode) {
		this.provinceCode = provinceCode;
	}
	public long getCityCode() {
		return cityCode;
	}
	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
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
