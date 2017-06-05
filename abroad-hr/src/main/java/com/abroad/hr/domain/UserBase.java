package com.abroad.hr.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 用户基本信息
 * @author shichangjian
 *
 */
public class UserBase implements Serializable {

	private static final long serialVersionUID = -4132562961220897890L;

	private Long id;
	private Long accountId;		//账号id
	private String userName;	//用户名
	private String nickName;	//昵称
	private Integer userAge;	//年龄
	private Integer sex;		//性别
	private Long birthday;		//出生年月
	private Integer isMarried;	//是否已婚
	private Long countryId;		//国家
	private Long cityId;		//城市
	private Long userTypeCode;	//用户类型
	private String icon;		//头像
	private String hobby;		//爱好
	private String specialtyDes;//专业描述
	private Long beginWorkDate;	//工龄
	private Integer caseCount;	//案例数
	private String motto;		//格言
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Long getBirthday() {
		return birthday;
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	public Integer getIsMarried() {
		return isMarried;
	}
	public void setIsMarried(Integer isMarried) {
		this.isMarried = isMarried;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getUserTypeCode() {
		return userTypeCode;
	}
	public void setUserTypeCode(Long userTypeCode) {
		this.userTypeCode = userTypeCode;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getSpecialtyDes() {
		return specialtyDes;
	}
	public void setSpecialtyDes(String specialtyDes) {
		this.specialtyDes = specialtyDes;
	}
	public Long getBeginWorkDate() {
		return beginWorkDate;
	}
	public void setBeginWorkDate(Long beginWorkDate) {
		this.beginWorkDate = beginWorkDate;
	}
	public Integer getCaseCount() {
		return caseCount;
	}
	public void setCaseCount(Integer caseCount) {
		this.caseCount = caseCount;
	}
	public String getMotto() {
		return motto;
	}
	public void setMotto(String motto) {
		this.motto = motto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
}
