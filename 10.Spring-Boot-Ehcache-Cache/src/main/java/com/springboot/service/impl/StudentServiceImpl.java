package com.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.pojo.Student;
import com.springboot.mapper.StudentMapper;
import com.springboot.service.StudentService;

@Repository("studentService")
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentMapper studentMapper;


	@Override
	public Student update(Student student) {
		 studentMapper.update(student);
		 return student;
	}

	@Override
	public void delete(Integer id) {

		studentMapper.delete(id);
	}

	@Override
	public Student queryById(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}
}
