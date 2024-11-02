package com.multi.schema.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantContext {

	private static ThreadLocal<String> currentTenant = new ThreadLocal<>();

	public static void setCurrentTenant(String tenant) {
		log.info("SET tenant Context={}", tenant);
		currentTenant.set(tenant);
	}

	public static String getCurrentTenant() {
		String tenant = currentTenant.get();
		log.info("GET tenant Context={}", tenant);
		return tenant;
	}

	public static void clear() {
		log.info("Clearing tenant");
		currentTenant.remove();
	}
}
