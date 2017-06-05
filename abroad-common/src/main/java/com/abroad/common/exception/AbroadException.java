package com.abroad.common.exception;



/**
 * 考虑到系统底层错误都是一些系统致命错误，因此需要开发人员得到完整系统日志
* @ClassName: BaseMeiproExeception
* @Description: TODO(基础异常类基类)
* @author: mengxr
* @date 2017年2月22日 下午3:51:38
*/
public class AbroadException extends Exception{
	
	
	private static final long serialVersionUID = 4391096143334026121L;
	
	private String errorCode;
    private Object[] args;
	
//	/**
//	* <p>Title: BaseMeiproExeception</p>
//	* <p>Description: 考虑到系统底层错误都是一些系统致命错误，因此需要开发人员得到完整系统日志</p>
//	* @param message
//	*/
//	protected AbroadException(String errorMsg) {
//		super("base system exception name AbroadExeception :" + errorMsg);
//	}
	/**
	* <p>Title: BaseMeiproExeception</p>
	* <p>Description:异常带相应的异常类型和信息 </p>
	* @param errorMsg
	* @param throwable
	*/
	protected AbroadException(String errorMsg,Throwable throwable) {
		super(errorMsg,throwable);
	}
	/**
	* <p>Title: BaseMeiproExeception</p>
	* <p>Description: 异常带相应的信息</p>
	* @param throwable
	*/
	protected AbroadException(Throwable throwable) {
		super(throwable);
	}



    protected AbroadException(String errorMsg) {
        super(errorMsg);
        this.errorCode = errorMsg;
    }

    protected AbroadException(String errorCode, Object... args) {
        super("ERROR CODE: " + errorCode + " ARGS:" + args);
        this.errorCode = errorCode;
        this.args = args;
    }

    protected AbroadException() {

    }

    public String getErrorCode() {
        return errorCode;
    }


    public Object[] getArgs() {
        return args;
    }

    /**
     * 方法声明时使用,放在范围操作符(public等)之后,返回类型声明(void等)之前.即一次只能有一个线程进入该方法,
     * 其他线程要想在此时调用该方法,只能排队等候,当前线程(就是在synchronized方法内部的线程)执行完该方法后,别的线程才能进入.
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
