package com.reneuby.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

    private static final String DEFAULT_URL = "mysql-database-calc.cs1bbzp1i9yv.eu-west-3.rds.amazonaws.com";//--url
    private static final String DEFAULT_PORT = "3306";//--port
    private static final String DEFAULT_DBNAME = "calc";//--dbname
    private static final String DEFAULT_USERNAME = "admin";//--u
    private static final String DEFAULT_PASSWORD = "rrrrr12345678";//--p

    @Bean
    public ViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        return resolver;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager htm = new JpaTransactionManager();
        htm.setEntityManagerFactory(emf);
        return htm;
    }

    @Bean
    public DataSource dataSource(@Autowired Environment environment) {
        String URL = environment.getProperty("url");
        URL = URL == null ? DEFAULT_URL : URL;
        String PORT = environment.getProperty("port");
        PORT = PORT == null ? DEFAULT_PORT : PORT;
        String DBNAME = environment.getProperty("dbname");
        DBNAME = DBNAME == null ? DEFAULT_DBNAME : DBNAME;
        String USERNAME = environment.getProperty("u");
        USERNAME = USERNAME == null ? DEFAULT_USERNAME : USERNAME;
        String PASSWORD = environment.getProperty("p");
        PASSWORD = PASSWORD == null ? DEFAULT_PASSWORD : PASSWORD;
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://"+ URL +":"+PORT+"/"+DBNAME);
        basicDataSource.setUsername(USERNAME);
        basicDataSource.setPassword(PASSWORD);
        return basicDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactory.setPackagesToScan("com.reneuby.domain");
        entityManagerFactory.setJpaPropertyMap(hibernateJpaProperties());
        return entityManagerFactory;
    }

    private Map<String, String> hibernateJpaProperties() {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.hbm2ddl.import_files", "insert-data.sql");
        return properties;
    }
}