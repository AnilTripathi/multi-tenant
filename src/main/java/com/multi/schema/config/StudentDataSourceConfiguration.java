package com.multi.schema.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = {
		"com.multi.schema.schools" }, entityManagerFactoryRef = "schoolEntityManagerFactory", transactionManagerRef = "schoolTransactionManager")
public class StudentDataSourceConfiguration {

	/*@Bean(name = "schoolEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean schoolEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.multi.schema.schools","com.multi.schema.config").persistenceUnit("eduStudent")
				.build();
	}*/
	
	@Bean("schoolEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean schoolEntityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.multi.schema");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.multiTenancy", "SCHEMA");
		properties.put("hibernate.tenant_identifier_resolver", stiResolver());
		properties.put("hibernate.multi_tenant_connection_provider", smtcProvider(dataSource));

		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	SchemaTenantIdentifierResolver stiResolver() {
		return new SchemaTenantIdentifierResolver();
	}

	@Bean
	SchemaMultiTenantConnectionProvider smtcProvider(DataSource dataSource) {
		return new SchemaMultiTenantConnectionProvider(dataSource);
	}

	@Bean(name = "schoolTransactionManager")
	PlatformTransactionManager schoolTransactionManager(
			@Qualifier("schoolEntityManagerFactory") EntityManagerFactory schoolEntityManagerFactory) {
		return new JpaTransactionManager(schoolEntityManagerFactory);
	}

}
