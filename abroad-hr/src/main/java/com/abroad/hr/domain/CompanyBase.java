package com.abroad.hr.domain;

import java.io.Serializable;

/**
* @ClassName: CompanyBase
* @Description: TODO(企业级用户信息)
* @author: shicj
* @date 2017年4月19日 下午3:17:58
*/
public class CompanyBase implements Serializable{

	
	private static final long serialVersionUID = 8016671707316204727L;
	private Long id;
	private String companyName;	//公司名
	private String local;		//所在地		
	private String vocation;	//所属行业
	private String phone;		//电话
	private String linkman;		//联系人
	private Integer linkStatus;	//联系状态 （0：已合作，1：有意向，跟进中，2：暂缓，3：不考虑）
	private Integer important;	//重要程度 （0：重要客户，1：一般客户，2：潜在客户）

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getVocation() {
		return vocation;
	}
	public void setVocation(String vocation) {
		this.vocation = vocation;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public Integer getLinkStatus() {
		return linkStatus;
	}
	public void setLinkStatus(Integer linkStatus) {
		this.linkStatus = linkStatus;
	}
	public Integer getImportant() {
		return important;
	}
	public void setImportant(Integer important) {
		this.important = important;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
