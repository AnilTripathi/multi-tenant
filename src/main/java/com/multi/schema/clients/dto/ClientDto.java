package com.multi.schema.clients.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface ClientDto {

	@Setter
	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	class ClientRequest {
		private String name;
		private String email;
		private String mobileNumber;
	}

	@Setter
	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	class ClientResponse {
		private String id;
		private String name;
		private String email;
		private String mobileNumber;
		private LocalDateTime createdDate;
		private LocalDateTime updatedDate;
	}
}
