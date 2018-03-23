package com.converter.config;

import java.net.URI;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/logout");
		registry.addMapping("/signup");
		registry.addMapping("/token/**");
		registry.addMapping("/api/**")
			.allowedMethods("GET", "HEAD", "POST", "PUT", "OPTIONS");
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		String username = null;
		String password = null;
		String dbUrl = null;

		String envDatabaseUrl = System.getenv("ENV_DATABASE_URL");
		System.out.println("=======ENV_DATABASE_URL: " + envDatabaseUrl);
        if( envDatabaseUrl != null ) {
            try {
            	URI dbUri = new URI(envDatabaseUrl);
                username = dbUri.getUserInfo().split(":")[0];
                password = dbUri.getUserInfo().split(":")[1];
                dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?ssl=true&sslmode=require&sslfactory=org.postgresql.ssl.NonValidatingFactory";
            }catch(Exception ex) {}
        }
        
        if( dbUrl == null ) {
        	System.out.println("GETTING DATABASE CONFIG FROM SPRING BOOT CONFIG");
        	username = env.getProperty("spring.datasource.username");
        	password = env.getProperty("spring.datasource.password");
        	dbUrl = env.getProperty("spring.datasource.url");
        }
        System.out.printf("\nusername: %1s, password: %2s, databaseurl: %3s", username, password, dbUrl);
        
        DataSource dataSource = new DataSource();
	    dataSource.setDriverClassName("org.postgresql.Driver");
	    dataSource.setUrl(dbUrl);
	    dataSource.setUsername(username);
	    dataSource.setPassword(password);
	    dataSource.setTestOnBorrow(true);
	    dataSource.setTestWhileIdle(true);
	    dataSource.setTestOnReturn(true);
	    dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }
	
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[] {"com.converter.model"});
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, env.getProperty("spring.jpa.database-platform"));
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty("spring.jpa.hibernate.ddl-auto"));
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }	
    
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    	JpaTransactionManager transactionManager = new JpaTransactionManager();
    	transactionManager.setEntityManagerFactory(entityManagerFactory);
    	return transactionManager;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("swagger-ui.html")
          .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
      registry.addResourceHandler("/webjars/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/");

      super.addResourceHandlers(registry);
    }    
}

