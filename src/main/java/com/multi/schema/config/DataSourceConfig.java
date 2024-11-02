package com.multi.schema.config;

import java.sql.Connection;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DataSourceConfig {
    @Autowired
	private Environment env;

    @Bean
    DataSource dataSource() {
        log.info("UUID: {}",UUID.randomUUID().toString());
		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setPoolName("student-db-pool");
		hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("useServerPrepStmts", "false");
		hikariConfig.addDataSourceProperty("rewriteBatchedStatements", "true");
		hikariConfig.addDataSourceProperty("cacheResultSetMetadata", "false");
		hikariConfig.addDataSourceProperty("cacheServerConfiguration", "true");
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("spring.jpa.show-sql", env.getProperty("spring.jpa.show-sql"));
		hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.url"));
		hikariConfig.setUsername("postgres");
		hikariConfig.setPassword("Wellness36@");
		hikariConfig.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		hikariConfig.setAutoCommit(false);
		hikariConfig.setIsolateInternalQueries(true);
		hikariConfig.setMinimumIdle(5);
		hikariConfig.setMaximumPoolSize(250);
		hikariConfig.setIdleTimeout(60000);
		hikariConfig.setMaxLifetime(30000);
		hikariConfig.setConnectionTimeout(30000);
		hikariConfig.setTransactionIsolation(String.valueOf(Connection.TRANSACTION_READ_COMMITTED));
		hikariConfig.setLeakDetectionThreshold(200000);

		return new HikariDataSource(hikariConfig);
	}
}
