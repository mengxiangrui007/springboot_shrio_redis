package com.abroad.security.shiro;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.abroad.common.vo.ShiroUser;

/**
 * 此处实现了Map接口用于操作getSession()，由于getSession()的结构是键值对的形式的
 * 
 * @ClassName: AbroadgetSession()
 * @Description: TODO(此处是此系统的基于Shrio自定义getSession())
 * @author: mengxr
 * @date 2017年3月27日 上午10:57:57
 */
public class AbroadSession implements Map<String, Object> {
	private Subject subject;

	public AbroadSession() {
		this.subject = SecurityUtils.getSubject();
	}

	/**
	 * @Title: getLoginUserInfo
	 * @Description: TODO(返回登录账号的信息)
	 * @param @return 设定文件
	 * @return Account 返回类型
	 * @author: mengxr
	 * @date 2017年3月27日 下午6:09:31
	 * @throws
	 */
	public ShiroUser getLoginUserInfo() {
		return (ShiroUser) subject.getPrincipal();
	}

	/**
	 * @Title: getgetSession()
	 * @Description: TODO(获取Shiro的getSession()信息)
	 * @param @return 设定文件
	 * @return getSession() 返回类型
	 * @author: mengxr
	 * @date 2017年3月27日 下午6:11:14
	 * @throws
	 */
	public Session getSession() {
		return subject.getSession();
	}

	/**
	 * @Title: getSubject
	 * @Description: TODO(用户目标对象)
	 * @param @return 设定文件
	 * @return Subject 返回类型
	 * @author: mengxr
	 * @date 2017年3月27日 下午6:11:37
	 * @throws
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * 登录用户
	 * 
	 * @Title: login
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param username
	 * @param @param password
	 * @param @param host
	 * @param @param remberMe 设定文件
	 * @return void 返回类型
	 * @author: mengxr
	 * @date 2017年3月27日 下午6:12:44
	 * @throws
	 */
	public void login(String username, String password, String host,
			boolean rememberMe) throws AuthenticationException{
		UsernamePasswordToken token = new UsernamePasswordToken();// 创建一个登录用户的令牌
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		token.setRememberMe(rememberMe);
		token.setHost(host);
		subject.login(token);
	}

	/**
	 * @Title: logout
	 * @Description: TODO(退出登录)
	 * @param 设定文件
	 * @return void 返回类型
	 * @author: mengxr
	 * @date 2017年3月27日 下午6:18:09
	 * @throws
	 */
	public void logout() {
		subject.logout();
	}

	@Override
	public int size() {
		Collection<Object> keys = getSession().getAttributeKeys();
		return (keys == null) ? 0 : keys.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsKey(Object key) {
		return keySet().contains(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return getSession().getAttributeKeys().contains(value);
	}

	@Override
	public Object get(Object key) {
		return getSession().getAttribute(key);
	}

	@Override
	public Object put(String key, Object value) {
		getSession().setAttribute(key, value);
		return value;
	}

	@Override
	public Object remove(Object key) {
		return getSession().removeAttribute((String) key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		for (Map.Entry<? extends String, ? extends Object> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void clear() {
		Collection<Object> keys = getSession().getAttributeKeys();
		for (Object key : keys) {
			getSession().removeAttribute(key);
		}
	}

	@Override
	public Set<String> keySet() {
		Collection<Object> keys = getSession().getAttributeKeys();
		Map<String, Object> map = new HashMap<String, Object>();
		if (keys != null && keys.size() > 0) {
			for (Object key : keys) {
				map.put((String) key, getSession().getAttribute(key));
			}
		}
		return map.keySet();
	}

	@Override
	public Collection<Object> values() {
		return getSession().getAttributeKeys();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		Collection<Object> keys = getSession().getAttributeKeys();
		Map<String, Object> map = new HashMap<String, Object>();
		if (keys != null && keys.size() > 0) {
			for (Object key : keys) {
				map.put((String) key, getSession().getAttribute(key));
			}
		}
		return map.entrySet();
	}

}
