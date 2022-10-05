package br.com.cerberusit.config.datasource;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.HashMap;

import static br.com.cerberusit.config.datasource.AuthDataSourceConfiguration.MODELS;
import static br.com.cerberusit.config.datasource.AuthDataSourceConfiguration.REPOSITORIES;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "authEntityManagerFactory"
        , basePackages = {MODELS, REPOSITORIES}
        , transactionManagerRef = "authTransactionManager")
public class AuthDataSourceConfiguration {

    public static final String MODELS = "br.com.cerberusit.domain.model";
    public static final String REPOSITORIES = "br.com.cerberusit.domain.repositories";

    @Primary
    @Bean(name = "authDataSource")
    public DataSource authDataSource(
            @Qualifier("authDataSourceProperties")DataSourceProperties authDataSourceProperties){
        return authDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "authDataSourceProperties")
    @ConfigurationProperties("spring.datasource.auth")
    public DataSourceProperties authDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "authEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean authEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("authDataSource")DataSource authDataSource){
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.connection.driver_class","org.postgresql.Driver");

        return builder.dataSource(authDataSource)
                .packages(MODELS)
                .persistenceUnit("Authentication")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "authTransactionManager")
    public PlatformTransactionManager authTransactionManager(
            @Qualifier("authEntityManagerFactory")EntityManagerFactory authEntityManagerFactory){
        return new JpaTransactionManager(authEntityManagerFactory);

    }
}
