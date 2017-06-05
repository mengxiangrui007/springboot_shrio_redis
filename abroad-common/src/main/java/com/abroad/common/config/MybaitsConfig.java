package com.abroad.common.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
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
	 
	/**
	* @Fields env : TODO(加载所有配置文件)
	*/
	@Autowired
	private Environment env;
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//		把sqlSessionFactory交给Spring管理
		sessionFactory.setDataSource(dataSource);
		// 指定基包dao接口路径.xml文件不用写resultMap<result property="accountId" column="account_id" />
		sessionFactory.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
		//xml文件路径
		sessionFactory.setMapperLocations(
	                new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));
//		sessionFactory：创建SqlSession实例的工厂，SqlSession：执行持久化操作的对象，类似于jdbc中的Connection，
		SqlSessionFactory sqlSessionFactory = sessionFactory.getObject();
		// 开启驼峰映射命名
		sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
//		MyBatis 插入空值时，JDBC类型为空时,某些驱动程序 要指定值,default指定JdbcType ，
		sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
		return sqlSessionFactory;
	}
}
