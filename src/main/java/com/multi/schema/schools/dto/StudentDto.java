package com.multi.schema.schools.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public interface StudentDto {

	@Setter
	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	class StudentRequest {
		private String name;
		private String email;
		private String phoneNumber;
	}

	@Setter
	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	class StudentResponse {
		private Long id;
		private String name;
		private String email;
		private String phoneNumber;
		private LocalDateTime createdDate;
		private LocalDateTime updatedDate;
	}

}
