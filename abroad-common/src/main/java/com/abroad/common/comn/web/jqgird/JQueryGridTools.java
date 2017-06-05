package com.abroad.common.comn.web.jqgird;

import java.lang.reflect.Field;
import java.util.List;

import com.abroad.common.comn.web.ServletTools;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: JQueryGridTools
 * @Description: TODO(专门用于Jquery前端插件封装的方法)
 * @author: mengxr
 * @date 2017年4月18日 下午5:32:06
 */
public class JQueryGridTools<T>{
	Class<T> persistentClass = null;
	/**
	* <p>Title: </p>
	* <p>Description:通过泛型获取Class </p>
	*/
	@SuppressWarnings("all")
	public JQueryGridTools(Class clazz) {
		persistentClass = clazz;
	}

	/**
	 * @Fields objectMapper : TODO(JSON工具)
	 */
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @Title: getQueryVO
	 * @Description: TODO(获取查询的VO对象)
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @author: mengxr
	 * @date 2017年3月8日 上午10:33:34
	 * @throws
	 */
	public T getQueryVO() {
		T readValue = null;
		Filters filters = null;
		try {
			String parameter = ServletTools.getRequest().getParameter("filters");
			if (null != parameter && parameter.length() > 0) {
				filters = (Filters) objectMapper.readValue(parameter,
						Filters.class);
				readValue = this.reverseVo(filters);
			} else {
				readValue = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return readValue;
	}

	/**
	 * @Title: reverseVo
	 * @Description: TODO(转VO)
	 * @param @param filters
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @author: mengxr
	 * @date 2017年3月8日 上午11:41:21
	 * @throws
	 */
	private T reverseVo(Filters filters) {
		T newInstance = null;
		try {
			newInstance = persistentClass.newInstance();
			List<Rules> rules = filters.getRules();
			String fieldName = null;
			String data = null;
			Field field = null;
			for (Rules rule : rules) {
				fieldName = rule.getField();
				data = rule.getData();
				field = persistentClass.getDeclaredField(fieldName);
				field.setAccessible(true);// 暴力访问
				field.set(newInstance, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newInstance;
	}
}
class Filters {
	private String groupOp;
	private List<Rules> rules;
	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	public List<Rules> getRules() {
		return rules;
	}
	public void setRules(List<Rules> rules) {
		this.rules = rules;
	}
}
class Rules {
	private String field; //字段名称
	private String op; //操作
	private String data; //数据
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
