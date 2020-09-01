package com.springboot.service.impl;

import com.springboot.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.pojo.Student;
import com.springboot.service.StudentService;

import java.util.List;

@Service("studentService")
public class StudentServiceImp implements StudentService{

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public int add(Student student) {
		return this.studentMapper.insertSelective(student);
	}

	@Override
	public int update(Student student) {
		return this.studentMapper.updateByPrimaryKeySelective(student);
	}

	@Override
	public int delete(String id) {
		return this.studentMapper.deleteByPrimaryKey(Integer.valueOf(id));
	}

	@Override
	public Student getStudentById(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Student> getAll() {
		return studentMapper.selectAll();
	}
}
