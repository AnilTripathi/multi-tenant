package com.multi.schema.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.multi.schema.clients", entityManagerFactoryRef = "clientEntityManagerFactory", transactionManagerRef = "clientTransactionManager")
public class ClientDataSourceConfiguration {

	@Primary
	@Bean(name = "clientEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean clientEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.multi.schema.clients").persistenceUnit("eduClient").build();
	}

	@Primary
	@Bean(name = "clientTransactionManager")
	PlatformTransactionManager clientTransactionManager(
			@Qualifier("clientEntityManagerFactory") EntityManagerFactory clientEntityManagerFactory) {
		return new JpaTransactionManager(clientEntityManagerFactory);
	}

}
