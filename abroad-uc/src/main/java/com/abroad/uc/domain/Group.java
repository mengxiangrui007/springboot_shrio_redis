package com.abroad.uc.domain;

/**
* @ClassName: Group
* @Description: TODO(集团Domain)
* @author: mengxr
* @date 2017年4月19日 下午12:07:05
*/
public class Group {
	private Long id;//主键
	private Long pid;//上级集团主键
	private String code;//编码
	private String name;//名称
	private String note;//备注
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
