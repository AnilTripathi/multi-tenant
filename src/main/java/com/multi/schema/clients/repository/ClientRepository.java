package com.multi.schema.clients.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multi.schema.clients.entity.client.Clients;

@Repository
public interface ClientRepository extends JpaRepository<Clients, String> {
	@Modifying
	@Query(nativeQuery = true, value = "CALL  create_schema(:schemaName)")
	void createSchema(@Param("schemaName") String schemaName);
}
