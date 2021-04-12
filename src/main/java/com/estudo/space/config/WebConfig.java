package com.estudo.space.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "spaceEntityManager", transactionManagerRef = "spaceTransactionManager", basePackages = "com.estudo.space.repository")
public class WebConfig {

    @Value("${spring.datasource.space.ddlSchema}")
    private String hbm2ddl;

    @Bean(name = "spaceEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean getspaceEntityManager(EntityManagerFactoryBuilder builder,
            @Qualifier("spaceDataSource") HikariDataSource spaceDataSource) {

        return builder.dataSource(spaceDataSource).packages("com.estudo.space.domain").persistenceUnit("space")
                .properties(additionalJpaProperties()).build();
    }

    @Bean("jpaPropertiesSpace")
    @ConfigurationProperties("spring.datasource.space")
    Map<String, String> additionalJpaProperties() {
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.hbm2ddl.auto", hbm2ddl);
        return map;
    }

    @Bean("spaceDataSourceProperties")
    @Primary
    @ConfigurationProperties("spring.datasource.space")
    public DataSourceProperties spaceDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("spaceDataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.space")
    public HikariDataSource spaceDataSource(
            @Qualifier("spaceDataSourceProperties") DataSourceProperties spaceDataSourceProperties) {
        return spaceDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "spaceTransactionManager")
    @Primary
    public JpaTransactionManager transactionManager(
            @Qualifier("spaceEntityManager") EntityManagerFactory spaceEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(spaceEntityManager);
        return transactionManager;
    }
}
