package com.abroad.common.exception;


/**
* @ClassName: ManagerException
* @Description: TODO(通用业务处理层异常)
* @author: mengxr
* @date 2017年2月22日 下午7:40:43
*/
public class ManagerException extends AbroadException{
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;

	public ManagerException(String errorMsg) {
		super(errorMsg);
	}

	public ManagerException(String errorMsg, Throwable throwable) {
		super("manager level exception name ManagerException :" +errorMsg, throwable);
		// TODO Auto-generated constructor stub
	}

	public ManagerException(Throwable throwable) {
		super(throwable);
	}
}
