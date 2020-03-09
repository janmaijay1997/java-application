package com.springMvc.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

@Configuration
@PropertySource("classpath:/DataBaseConfig.properties")
@EnableTransactionManagement

public class DataBaseConfig {
	
	@Autowired
	Environment env;
	
	@Bean( name="dataSource",destroyMethod = "close")
	public DataSource dataSource() {
		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
		dsLookup.setResourceRef(true);
		DataSource dataSource = dsLookup.getDataSource(env.getProperty("database.datasource"));
		return dataSource;
		/*BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));*/
		//return dataSource;
	}
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.scanPackages("com.springMvc.model");
		builder.addProperties(getHibernateProperties());
		return builder.buildSessionFactory();
	}
	
	
	protected Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.jdbc.batch_size", "1000");
		properties.put("hibernate.cache.provider_class", "net.sf.ehcache.hibernate.EhCacheRegionFactory");
		properties.put("hibernate.cache.use_second_level_cache", "false");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.use_sql_comments", "true");
		properties.put("hibernate.order_inserts", "true");
		properties.put("hibernate.order_updates", "true");
		properties.put("hibernate.connection.CharSet", "utf8");
		properties.put("hibernate.connection.characterEncoding", "utf8");
		properties.put("hibernate.connection.useUnicode", "true");
		return properties;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	public TransactionProxyFactoryBean tpFactoryBean(HibernateTransactionManager hibernateTransactionManager,
			@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		Properties properties = new Properties();
		properties.put("*", "PROPAGATION_REQUIRED");
		TransactionProxyFactoryBean trPrFacBean = new TransactionProxyFactoryBean();
		trPrFacBean.setProxyTargetClass(true);
		trPrFacBean.setTransactionManager(hibernateTransactionManager);
		trPrFacBean.setTransactionAttributes(properties);

		return trPrFacBean;
	}
	

}
