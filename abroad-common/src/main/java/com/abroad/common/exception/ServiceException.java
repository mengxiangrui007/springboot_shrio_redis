package com.abroad.common.exception;


/**
* @ClassName: ServiceException
* @Description: TODO(业务逻辑服务层的异常)
* @author: mengxr
* @date 2017年2月22日 下午7:36:47
*/
public class ServiceException extends AbroadException {
	
	
	
	private static final long serialVersionUID = -3528494699263903955L;
//	public ServiceException(String errorMsg) {
//		super("service level exception name ServiceException :" + errorMsg);
//	}
	public ServiceException(String errorMsg, Throwable throwable) {
		super(errorMsg, throwable);
		// TODO Auto-generated constructor stub
	}
//	public ServiceException(Throwable throwable) {
//		super(throwable);
//		// TODO Auto-generated constructor stub
//	}
	
	
	public ServiceException(Exception cause) {
		super(cause);
	}
	public ServiceException(String errorMsg) {
		super(errorMsg);
	}
	public ServiceException(String errorCode, Object... args) {
		super(errorCode, args);
 	}
}
