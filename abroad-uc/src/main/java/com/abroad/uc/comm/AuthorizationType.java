package com.abroad.uc.comm;

/**
 * @ClassName: AuthorizationType
 * @Description: TODO(权限类型)
 * @author: mengxr
 * @date 2017年4月12日 下午6:32:35
 */
public interface AuthorizationType {

	/**
	 * @Fields menu : TODO(菜单权限)
	 */
	public final static int MENU = 0;

	/**
	 * @Fields OPER : TODO(操作权限)
	 */
	public final static int OPER = 1;

	/**
	 * @Fields FILE : TODO(文件权限)
	 */
	public final static int FILE = 2;
}
