package com.multi.schema.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@Configuration
public class HibernateConfig {

	@Autowired
	private DataSource dataSource;

	//@Bean("entityManagerFactory")
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.multi.schema");

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.multiTenancy", "SCHEMA");
		properties.put("hibernate.tenant_identifier_resolver", stiResolver());
		properties.put("hibernate.multi_tenant_connection_provider", smtcProvider());

		em.setJpaPropertyMap(properties);

		return em;
	}

	//@Bean
	SchemaTenantIdentifierResolver stiResolver() {
		return new SchemaTenantIdentifierResolver();
	}

	//@Bean
	SchemaMultiTenantConnectionProvider smtcProvider() {
		return new SchemaMultiTenantConnectionProvider(dataSource);
	}
}
