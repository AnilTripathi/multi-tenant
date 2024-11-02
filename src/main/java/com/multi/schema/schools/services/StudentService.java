package com.multi.schema.schools.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.schema.schools.dto.StudentDto;
import com.multi.schema.schools.entity.student.Student;
import com.multi.schema.schools.mapper.StudentMapper;
import com.multi.schema.schools.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentService {
	@Autowired
	private StudentRepository repository;

	public List<Student> findAllStudent() {
		return repository.findAll();
	}

	public Student findStudentById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Student saveStudent(StudentDto.StudentRequest payload) {
		Student studentObj = Student.builder().createdDate(LocalDateTime.now()).updatedDate(LocalDateTime.now())
				.name(payload.getName()).phoneNumber(payload.getPhoneNumber()).email(payload.getEmail()).build();
		studentObj.setCreatedDate(LocalDateTime.now());
		studentObj.setUpdatedDate(LocalDateTime.now());
		return repository.save(studentObj);
	}

}
