package com.springboot.mapper;

import com.springboot.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * spring boot整合 Mybatis
 * @Mapper
 * @MapperScan 扫描
 */
@Mapper
public interface StudentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> selectAll();
}