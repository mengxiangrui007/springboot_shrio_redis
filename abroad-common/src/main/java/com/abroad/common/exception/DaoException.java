package com.abroad.common.exception;


/**
* @ClassName: DaoException
* @Description: TODO(Dao层产生的异常)
* @author: mengxr
* @date 2017年2月22日 下午7:33:42
*/
public class DaoException extends AbroadException {
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;

	protected DaoException(String errorMsg) {
		super(errorMsg);
	}

	public DaoException(String errorMsg, Throwable throwable) {
		super("service level exception name DaoException :"+ errorMsg, throwable);
	}

	public DaoException(Throwable throwable) {
		super(throwable);
	}
	
}
