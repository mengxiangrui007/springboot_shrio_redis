package com.abroad.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * pk_hr_user 创建人 
 * cratetime 创建时间
 * pk_user_modify 修改人
 * modifytime 修改时间
 * dr 是否删除
* @ClassName: OperateVO
* @Description: TODO(由于基本每个VO都含有以上字段所以创建了此OperateVO)
* @author: mengxr
* @date 2017年3月25日 下午3:57:20
*/
public class OperateVO implements Serializable{
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private Long pk_hr_user;//创建人
	private Date cratetime;//创建时间
	private Long pk_user_modify;//修改人
	private Date modifytime;//修改时间
	private Short dr;//是否删除
	public Long getPk_hr_user() {
		return pk_hr_user;
	}
	public void setPk_hr_user(Long pk_hr_user) {
		this.pk_hr_user = pk_hr_user;
	}
	public Date getCratetime() {
		return cratetime;
	}
	public void setCratetime(Date cratetime) {
		this.cratetime = cratetime;
	}
	public Long getPk_user_modify() {
		return pk_user_modify;
	}
	public void setPk_user_modify(Long pk_user_modify) {
		this.pk_user_modify = pk_user_modify;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	public Short getDr() {
		return dr;
	}
	public void setDr(Short dr) {
		this.dr = dr;
	}
}
