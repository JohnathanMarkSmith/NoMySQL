package com.johnathanmarksmith.noMySQL;

import com.johnathanmarksmith.noMySQL.model.Message;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Date:   5/24/13 / 8:05 AM
 * Author: Johnathan Mark Smith
 * Email:  john@johnathanmarksmith.com
 *
 * Comments:
 *
 *    This is a example on how to setup a database with Spring's Java Configuration (JavaConfig) style.
 *
 *    As you can see from the code below this is easy and a lot better then using the old style of XML files.
 *
 */

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {Main.class})
@PropertySource("classpath:application.properties")
public class DatabaseConfiguration
{


    /**
     *
     * This is used to setup the database. It will load the schema.sql file which does a create table so we have
     * a table to work with in the project
     */
    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource)
    {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/schema.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }

    /**
     *
     * This will be setting up a datasource using HyperSQL (hsqldb) in memory
     */
    @Bean
    public DataSource hsqlDataSource()
    {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("");
        basicDataSource.setUrl("jdbc:hsqldb:mem:mydb");
        return basicDataSource;
    }

    /**
     *
     * This setups the session factory
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory(Environment environment,
                                                  DataSource dataSource)
    {

        /**
         *
         * Getting packageOfModelBean from package of message bean
         *
         */
        String packageOfModelBeans = Message.class.getPackage().getName();

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(buildHibernateProperties(environment));
        factoryBean.setPackagesToScan(packageOfModelBeans);
        return factoryBean;
    }

    /**
     *
     * Loading all the hibernate properties from a properties file
     */
    protected Properties buildHibernateProperties(Environment env)
    {
        Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        hibernateProperties.setProperty("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
        hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        hibernateProperties.setProperty("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"));

        hibernateProperties.setProperty("javax.persistence.validation.mode", env.getProperty("javax.persistence.validation.mode"));

        //Audit History flags
        hibernateProperties.setProperty("org.hibernate.envers.store_data_at_delete", env.getProperty("org.hibernate.envers.store_data_at_delete"));
        hibernateProperties.setProperty("org.hibernate.envers.global_with_modified_flag", env.getProperty("org.hibernate.envers.global_with_modified_flag"));

        return hibernateProperties;
    }

    /**
     *
     * This is setting up the hibernate transaction manager
     *
     */
    @Bean
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory)
    {
        return new HibernateTransactionManager(sessionFactory);
    }


}
