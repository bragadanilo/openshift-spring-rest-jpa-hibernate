package com.dbraga.springrest.app.configuration;

import java.sql.SQLException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "com.dbraga.springrest.app")
@PropertySource({ "classpath:database-local.properties" })
public class HelloWorldConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/", "classpath:/other-resources/");
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));

		if (env.getProperty("OPENSHIFT_APP_NAME") != null) {
			//
			// running in openshift server
			String host = env.getProperty("OPENSHIFT_MYSQL_DB_HOST");
			String port = env.getProperty("OPENSHIFT_MYSQL_DB_PORT");
			String dbName = env.getProperty("OPENSHIFT_APP_NAME");
			String dbUrl = String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);
			
			dataSource.setUrl(dbUrl);
			dataSource.setUsername(env.getProperty("OPENSHIFT_MYSQL_DB_USERNAME"));
			dataSource.setPassword(env.getProperty("OPENSHIFT_MYSQL_DB_PASSWORD"));
		} else {
			//
			// running in local machine
			dataSource.setUrl(env.getProperty("jdbc.url"));
			dataSource.setUsername(env.getProperty("jdbc.user"));
			dataSource.setPassword(env.getProperty("jdbc.pass"));
		}
		return dataSource;
	}
	
	 @Bean(name = "jpaTx")
	  public PlatformTransactionManager transactionManagerJPA() throws NamingException, SQLException {
	    JpaTransactionManager txManager = new JpaTransactionManager(entityManagerFactory());

	    return txManager;
	  }

	@Bean
    public EntityManagerFactory entityManagerFactory() throws SQLException {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.dbraga.springrest.app.domain");
        factory.setDataSource(getDataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }


	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private Properties hibernateProperties() {
		return new Properties() {
			private static final long serialVersionUID = 1992379800536859653L;
			{
				setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
				setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
				setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
				setProperty("hibernate.globally_quoted_identifiers", "true");
			}
		};
	}

}