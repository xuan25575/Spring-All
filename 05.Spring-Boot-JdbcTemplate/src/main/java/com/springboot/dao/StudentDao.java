package com.springboot.dao;

import java.util.List;
import java.util.Map;

import com.springboot.pojo.Student;

public interface StudentDao {
    int add(Student student);

    int update(Student student);

    int deleteById(Integer id);

    List<Map<String, Object>> queryStudentsListMap();

    Student queryStudentById(Integer id);

    int insertBySimpleInsert(Student student);
}
