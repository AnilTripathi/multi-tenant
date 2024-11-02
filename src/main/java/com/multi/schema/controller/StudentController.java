package com.multi.schema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.schema.schools.dto.StudentDto;
import com.multi.schema.schools.entity.student.Student;
import com.multi.schema.schools.services.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService service;

	@GetMapping
	public List<Student> getAllStudent() {
		return service.findAllStudent();
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return service.findStudentById(id);
	}

	@PostMapping
	public Student saveStudent(@RequestBody StudentDto.StudentRequest payload) {
		return service.saveStudent(payload);
	}

}
