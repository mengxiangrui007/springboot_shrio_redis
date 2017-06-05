package com.abroad.security.shiro;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.abroad.security.shiro.redis.RedisSessionDao;
  
/**
* @ClassName: ShiroSessionListener
* @Description: TODO(发现用户退出后，Session没有从Redis中销毁，虽然当前重新new了一个，
* 但会对统计带来干扰，通过SessionListener解决这个问题)
* @author: mengxr
* @date 2017年3月28日 下午3:08:09
*/
public class ShiroSessionListener implements SessionListener {  
  
    private static final Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);  
  
    @Autowired  
    private RedisSessionDao sessionDao;  
  
    @Override  
    public void onStart(Session session) {  
        // 会话创建时触发  
        logger.info("ShiroSessionListener session {} 被创建", session.getId());  
    }  
  
    @Override  
    public void onStop(Session session) {  
    	sessionDao.delete(session);  
        // 会话被停止时触发  
        logger.info("ShiroSessionListener session {} 被销毁", session.getId());  
    }  
  
    @Override  
    public void onExpiration(Session session) {  
        sessionDao.delete(session);  
        //会话过期时触发  
        logger.info("ShiroSessionListener session {} 过期", session.getId());  
    }  
}  