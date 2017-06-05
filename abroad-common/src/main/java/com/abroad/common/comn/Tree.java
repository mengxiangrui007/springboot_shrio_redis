package com.abroad.common.comn;

import java.io.Serializable;
import java.util.List;

/**
* @ClassName: AbroadTree
* @Description: TODO(树形结构)
* @author: mengxr
* @date 2017年3月27日 上午11:19:24
*/
public class Tree implements Serializable{
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;
	private String id;
	private String pid;
	private String code;
	private String name;
	private List<Tree> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
}
