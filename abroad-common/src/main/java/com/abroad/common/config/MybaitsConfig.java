package com.abroad.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* @ClassName: MybaitsConfig
* @Description: TODO(Mybatis已经分页插件注册)
* @author: mengxr
* @date 2017年3月23日 下午3:57:08
*/
@Configuration
@MapperScan("com.abroad.*.dao")
@EnableTransactionManagement  //Spring事务管理器
public class MybaitsConfig {
	 @Autowired
	 private Environment env;
	//分页插件
	//private PageInterceptor pageInterceptor = new PageInterceptor();
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		//sessionFactory.setPlugins(new Interceptor[]{pageInterceptor});//分页拦截器插件
		sessionFactory.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
		sessionFactory.setMapperLocations(
	                new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//
		SqlSessionFactory sqlSessionFactory = sessionFactory.getObject();
		sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
		sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
		return sqlSessionFactory;
	}
}
