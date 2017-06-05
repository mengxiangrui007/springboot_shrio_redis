package com.abroad.security.cache;

import java.util.HashSet;
import java.util.Set;

/**
* @ClassName: LocalCache
* @Description: TODO(获取到的本地缓存)
* @author: mengxr
* @date 2017年3月29日 下午6:30:38
*/
public class LocalCache {
	/**
	* @Fields REQUEST_SET : TODO(当启动后从Spring容器获取到的可访问请求缓存 )
	*/
	public static Set<String> ACCESS_URL = new HashSet<String>();
}
