package com.springboot.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.pojo.Student;
import com.springboot.dao.StudentDao;
import com.springboot.service.StudentService;

@Service("studentService")
public class StudentServiceImp implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public int add(Student student) {
//		return this.studentDao.add(student);
		return this.studentDao.insertBySimpleInsert(student);
	}

	@Override
	public int update(Student student) {
		return this.studentDao.update(student);
	}

	@Override
	public int delete(Integer id) {
		return this.studentDao.deleteById(id);
	}

	@Override
	public List<Map<String, Object>> queryStudentListMap() {
		return this.studentDao.queryStudentsListMap();
	}

	@Override
	public Student queryStudentById(Integer id) {
		return this.studentDao.queryStudentById(id);
	}

}
