package com.sample.springmvc.configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class JpaConfiguration {
	
	public JpaConfiguration(){
		System.out.println("..Inside JpaConfiguration...");
	}
	
	private Environment environment;
	
	@Bean
	public DataSource dataSource(){
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
	        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
	        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
	        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
	        return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
		LocalContainerEntityManagerFactoryBean entityFacBean = new LocalContainerEntityManagerFactoryBean();
		entityFacBean.setDataSource(dataSource());
		entityFacBean.setPackagesToScan(new String[]{"com.sample.springmvc.domain"});
		entityFacBean.setJpaVendorAdapter(jpaVendorAdapter());
		entityFacBean.setJpaProperties(jpaProperties());
		return entityFacBean;
	}

    @Bean	
	public JpaVendorAdapter jpaVendorAdapter(){
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		return jpaVendorAdapter();
	}
    
    private Properties jpaProperties(){
    	 Properties properties = new Properties();
         properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
         // properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
         properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
         properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
         return properties;
    }
    
    
    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
    	JpaTransactionManager txnMgr = new JpaTransactionManager();
    	txnMgr.setEntityManagerFactory(emf);
    	return txnMgr;
    }
}
