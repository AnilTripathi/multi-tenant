package com.multi.schema.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class SchemaTenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

	private static final String DEFAULT_TENANT_ID = "public";

	@Override
	public String resolveCurrentTenantIdentifier() {
		String tenantId = TenantContext.getCurrentTenant();
		log.info("STIR tenantId = ", tenantId);
		return (tenantId != null) ? tenantId : DEFAULT_TENANT_ID;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
