package com.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.pojo.Student;
import com.springboot.service.StudentService;

@RestController
public class TestController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Student get(int id) {
		return studentService.queryStudentById(id);
	}

	@RequestMapping(value = "/list")
	public List<Map<String, Object>> list() {
		return studentService.queryStudentListMap();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public int saveStudent(@RequestBody Student student) {
		return studentService.add(student);
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public int delete(Integer id) {
		return studentService.delete(id);
	}
}
