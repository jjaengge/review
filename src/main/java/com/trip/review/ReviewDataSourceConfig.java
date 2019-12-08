package com.trip.review;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "reviewEntityManagerFactory", transactionManagerRef = "reviewTransactionManager", basePackages = {"com.trip.review.repo"})
public class ReviewDataSourceConfig {

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource reviewDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "reviewEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean reviewEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                             @Qualifier("dataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.trip.review.entity").persistenceUnit("review").build();
    }

    @Primary
    @Bean(name = "reviewTransactionManager")
    public PlatformTransactionManager reviewTransactionManager(@Qualifier("reviewEntityManagerFactory") EntityManagerFactory reviewEntityManagerFactory) {
        return new JpaTransactionManager(reviewEntityManagerFactory);
    }
}

