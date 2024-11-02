package com.multi.schema.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.UnknownKeyFor;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SchemaMultiTenantConnectionProvider implements MultiTenantConnectionProvider<String> {

	private static final long serialVersionUID = 1L;
	private final DataSource dataSource;

	public SchemaMultiTenantConnectionProvider(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		connection.close();
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		log.info("tenantIdentifier={}", tenantIdentifier);
		final Connection connection = getAnyConnection();
		try {
			connection.createStatement().execute("SET search_path TO " + tenantIdentifier);
		} catch (SQLException e) {
			throw new RuntimeException("Failed to set schema for tenant: " + tenantIdentifier, e);
		}
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		try {
			connection.createStatement().execute("SET search_path TO public");
		} catch (SQLException e) {
			throw new RuntimeException("Failed to reset schema to public", e);
		}
		connection.close();
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

	@Override
	public @UnknownKeyFor @NonNull @Initialized boolean isUnwrappableAs(
			@UnknownKeyFor @NonNull @Initialized Class<@UnknownKeyFor @NonNull @Initialized ?> unwrapType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> @UnknownKeyFor @NonNull @Initialized T unwrap(
			@UnknownKeyFor @NonNull @Initialized Class<@UnknownKeyFor @NonNull @Initialized T> unwrapType) {
		// TODO Auto-generated method stub
		return null;
	}
}
