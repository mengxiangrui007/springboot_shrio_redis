package com.abroad.uc.vo;

import java.io.Serializable;

import com.abroad.common.vo.OperateVO;

/**
* @ClassName: GroupVO
* @Description: TODO(集团VO)
* @author: mengxr
* @date 2017年3月25日 下午3:48:57
*/
public class GroupVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;//主键
	private String code;//编码
	private Long pid;//上级集团主键
	private String note;//备注
	private OperateVO ope;//操作VO
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public OperateVO getOpe() {
		return ope;
	}
	public void setOpe(OperateVO ope) {
		this.ope = ope;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
