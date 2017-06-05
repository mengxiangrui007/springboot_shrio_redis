package com.abroad.security.shiro.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.abroad.common.comn.web.ServletTools;
import com.abroad.common.tools.RedisUtil;
import com.abroad.security.shiro.ShiroSession;
import com.abroad.security.tools.Constants;
import com.abroad.security.tools.SerializeUtils;

/**
 * 默认的Redis的缓存是Echace和CurrentMap实现的
 * 
 * 针对自定义的ShiroSession的Redis CRUD操作，通过isChanged标识符，确定是否需要调用Update方法
 * 通过配置securityManager在属性cacheManager查找从缓存中查找Session是否存在，如果找不到才调用下面方法
 * Shiro内部相应的组件（DefaultSecurityManager）会自动检测相应的对象（如Realm）
 * 是否实现了CacheManagerAware并自动注入相应的CacheManager。
 * 
 * @ClassName: RedisSessionDAO
 * @Description: TODO(实现Redis的Session的管理)
 * @author: mengxr
 * @date 2017年3月28日 上午11:52:29
 */
public class RedisSessionDao extends AbstractSessionDAO implements SessionDAO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @Fields redisUtil : TODO(Redis工具)
	 */
	@Autowired
	private RedisUtil redisUtil;

	/**
	* @Title: update
	* @Description: TODO(每次session有任何变化，都会进此方法)
	* @see org.apache.shiro.session.mgt.eis.SessionDAO#update(org.apache.shiro.session.Session)
	*/
	@Override
	public void update(Session session) throws UnknownSessionException {
		if (session == null || session.getId() == null) {
			return;
		}
		// 如果会话过期/停止 没必要再更新了
		try {
			if (session instanceof ValidatingSession
					&& !((ValidatingSession) session).isValid()) {
				return;
			}
		} catch (Exception e) {
			logger.error("session {} ValidatingSession error", session
					.getClass().getName());
		}
		if (session instanceof ShiroSession) {
			// 如果没有主要字段(除lastAccessTime以外其他字段)发生改变
			ShiroSession shiroSession = (ShiroSession) session;
			if (!shiroSession.isChanged()) {
				return;
			}
			shiroSession.setChanged(false);
			shiroSession.setLastAccessTime(new Date()); // 設置最後访问时间
			redisUtil.set(Constants.SHIRO_REDIS_SESSION + session.getId(),
					SerializeUtils.serializeToString(shiroSession),
					Constants.SESSION_EXPIRE_TIME); // 保存在Redis中
			logger.info("shiro session id {} {} 被更新", session.getId(), session
					.getClass().getName());
		} else if (session instanceof Serializable) {
			redisUtil.set(Constants.SHIRO_REDIS_SESSION + session.getId(),
					SerializeUtils.serializeToString((Serializable) session),
					Constants.SESSION_EXPIRE_TIME); // 保存在Redis中
			logger.info("ID {} classname {} 作为非ShiroSession对象被更新, ",
					session.getId(), session.getClass().getName());
		} else {
			logger.info("ID {} classname {} 不能被序列化 更新失败", session.getId(),
					session.getClass().getName());
		}
	}

	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			return;
		}
		String key = Constants.SHIRO_REDIS_SESSION + session.getId();
		try {
			redisUtil.remove(key);
			logger.info("shiro sessionid {} 删除成功, ", session.getId());
		} catch (Exception e) {
			logger.info("shiro sessionid {} 删除失败, ", session.getId(), e);
		}
	}

	/**
	* @Title: doCreate
	* @Description: TODO(如果没有sessionId，创建一个sessionId)
	* @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache.shiro.session.Session)
	*/
	@Override
	protected Serializable doCreate(Session session) {
		// 创建一个Id并设置给Session
		Serializable sessionId = this.generateSessionId(session);
		assignSessionId(session, sessionId);
		session.setTimeout(Constants.SESSION_EXPIRE_TIME); // 设置session的过期时间
		try {
			redisUtil.set(Constants.SHIRO_REDIS_SESSION + sessionId,
					SerializeUtils.serializeToString((ShiroSession) session),
					Constants.SESSION_EXPIRE_TIME); // 保存在Redis中
			logger.info("shiro session id {} doCreate", sessionId);
		} catch (Exception e) {
			logger.warn("shiro session error with id  [" + sessionId + "]", e);
		}
		return sessionId;
	}

	/**
	* @Title: doReadSession
	* @Description: TODO(登录后每次请求都进此方法)
	* @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doReadSession(java.io.Serializable)
	*/
	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = null;
		try {
			String key = Constants.SHIRO_REDIS_SESSION + sessionId;
			HttpServletRequest request = ServletTools.getRequest(); // 获取当前请求
			session = (Session) request.getAttribute(key); // 获取当前服务器session
			if (null != session) {
				return session;
			}
			String value = (String) redisUtil.get(key);
			if (StringUtils.isNotBlank(value)) {
				session = SerializeUtils.deserializeFromString(value);
				logger.info("shiro session id {} read ", sessionId);
				request.setAttribute(key, session); // 设置当前服务器session
			}
		} catch (Exception e) {
			logger.warn(
					"read shiro session error with id  [" + sessionId + "]", e);
		}
		return session;
	}

	/**
	* @Title: readSession
	* @Description: TODO(每次更新session都有进此方法读取，sessionId不为null的情况下，没次请求先进此方法)
	* @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO#readSession(java.io.Serializable)
	*/
	@Override
	public Session readSession(Serializable sessionId)
			throws UnknownSessionException {
		try {
			return super.readSession(sessionId);
		} catch (UnknownSessionException e) {
			return null;
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		// TODO Auto-generated method stub
		return null;
	}

}
