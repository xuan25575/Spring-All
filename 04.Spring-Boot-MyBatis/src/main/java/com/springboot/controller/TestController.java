package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.Student;
import com.springboot.service.StudentService;

import java.util.List;

@RestController
public class TestController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping( value = "/get")
	public Student getStudentById(String id) {
		Student student = studentService.getStudentById(Integer.valueOf(id));
		return student;
	}

	@GetMapping( value = "/list")
	public List<Student> list() {
		return studentService.getAll();
	}
}
