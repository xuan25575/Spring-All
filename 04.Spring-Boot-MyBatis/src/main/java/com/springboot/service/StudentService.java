package com.springboot.service;

import com.springboot.pojo.Student;

import java.util.List;

public interface StudentService {


    int add(Student student);

    int update(Student student);

    int delete(String sno);

    Student getStudentById(Integer id);

    List<Student> getAll();
}
