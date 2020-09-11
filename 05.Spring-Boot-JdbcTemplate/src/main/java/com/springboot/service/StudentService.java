package com.springboot.service;

import java.util.List;
import java.util.Map;

import com.springboot.pojo.Student;

public interface StudentService {
	int add(Student student);
    int update(Student student);
    int delete(Integer id);
    List<Map<String, Object>> queryStudentListMap();
    Student queryStudentById(Integer id);
}
