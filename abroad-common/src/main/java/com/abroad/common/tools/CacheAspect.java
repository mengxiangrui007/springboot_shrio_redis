package com.abroad.common.tools;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.abroad.common.exception.AbroadException;
import com.abroad.common.exception.ServiceException;


/**
 * 哈希缓存aop
 * 
 * @author shichangjian
 *
 */
@Component
@Aspect
public class CacheAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisCacheBean redis;

	@Pointcut("@annotation(com.abroad.common.tools.Cacheable)")
	public void getCache1() {
		logger.info("添加缓存切入点");
	}

	@Pointcut("@annotation(com.abroad.common.tools.CacheEvict)")
	public void getCacheEvict() {
		logger.info("删除缓存切入点");
	}

	/**
	 * 定义缓存逻辑
	 */
	@Around("getCache1()")
	public Object cache(ProceedingJoinPoint pjp) throws AbroadException{
		Object result = null;
		// 判断是否开启缓存
		// Boolean cacheEnable=SystemConfig.getInstance().getCacheEnabled();
		// if(!cacheEnable){
		// try {
		// 		result= pjp.proceed();
		// } catch (Throwable e) {
		// 		e.printStackTrace();
		// }
		// 	return result;
		// }

		Method method = null;
		try {
			method = getMethod(pjp);
		} catch (Exception e) {
			logger.error("[error message] 获取注解缓存类，函数，参数异常!");	
			throw new ServiceException("juntai-redis.ObtainParametersException");
		}

		Cacheable cacheable = method.getAnnotation(Cacheable.class);
		String fieldKey = parseKey(cacheable.fieldKey(), method, pjp.getArgs());
		// 获取方法的返回类型,让缓存可以返回正确的类型
		Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();
		// 使用redis 的hash进行存取，易于管理
		result = redis.hget(cacheable.key(), fieldKey, returnType);
		if (result == null) {
			System.err.println("走数据库**************");
			try {
				result = pjp.proceed();
				Assert.notNull(fieldKey);
				redis.hset(cacheable.key(), fieldKey, result);
			} catch (ServiceException e) {
				if(e instanceof ServiceException)
						throw new ServiceException(e.getErrorCode());
			} catch (Throwable e) {
				logger.error("[error message] 存入缓存数据异常!");	
				throw new ServiceException("abroad-redis.AddCacheParametersException");
			} 		
			
		}
		return result;
	}

	// 定义清除缓存逻辑
	@Around("getCacheEvict()")
	public Object evict(ProceedingJoinPoint pjp) throws AbroadException{
		Object result = null;
		// Boolean cacheEnable=SystemConfig.getInstance().getCacheEnabled();
		// 判断是否开启缓存
		// if(!cacheEnable){
		// try {
		// 		result= pjp.proceed();
		// } catch (Throwable e) {
		// 		e.printStackTrace();
		// }
		// return result;
		// }

		Method method = null;
		try {
			method = getMethod(pjp);
		} catch (Exception e) {
			logger.error("[error message] 获取注解缓存类，函数，参数异常!");	
			throw new ServiceException("abroad-redis.ObtainParametersException");
		}
		CacheEvict cacheable = method.getAnnotation(CacheEvict.class);
		String fieldKey = parseKey(cacheable.fieldKey(), method, pjp.getArgs());
		// 获取方法的返回类型,让缓存可以返回正确的类型
//		Class returnType = ((MethodSignature) pjp.getSignature()).getReturnType();
		// 使用redis 的hash进行存取，易于管理
		try {
			result = pjp.proceed();
			redis.hdel(cacheable.key(), fieldKey);
		} catch (Throwable e) {
			logger.error("[error message] 删除缓存异常!");	
			throw new ServiceException("abroad-redis.DeleteCacheException");
		}
		return result;
	}

	/**
	 * 获取被拦截方法对象 MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象 而缓存的注解在实现类的方法上
	 * 所以应该使用反射获取当前对象的方法对象
	 * @throws NoSuchMethodException 
	 */
	public Method getMethod(ProceedingJoinPoint pjp) throws AbroadException{
		// 获取参数的类型
		Object[] args = pjp.getArgs();
		Class[] argTypes = new Class[pjp.getArgs().length];
		for (int i = 0; i < args.length; i++) {
			argTypes[i] = args[i].getClass();
		}
		Method method = null;
		try {
			// 只识别类Long，not long
			method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
		} catch (Exception e) {
			logger.error("[error message] 获取注解缓存类，函数，参数异常!");	
			throw new ServiceException("abroad-redis.ObtainParametersException");
		}
		return method;
	}

	/**
	 * 获取缓存的key,key定义在注解上，支持SPEL表达式 ，只识别impl类
	 * 
	 * @param pjp
	 * @return
	 */
	private String parseKey(String key, Method method, Object[] args) throws AbroadException{
		// 获取被拦截方法参数名列表(使用Spring支持类库)
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = u.getParameterNames(method);

		// 使用SPEL进行key的解析
		ExpressionParser parser = new SpelExpressionParser();
		// SPEL上下文
		StandardEvaluationContext context = new StandardEvaluationContext();
		// 把方法参数放入SPEL上下文中
		for (int i = 0; i < paraNameArr.length; i++) {
			context.setVariable(paraNameArr[i], args[i]);
		}
		try {
			return parser.parseExpression(key).getValue(context, String.class);
		} catch (Exception e) {
			logger.error("[error message] key解析异常");
			throw new ServiceException("abroad-redis.keyParseException");
		}
	}
}
