package com.springboot.dao.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.pojo.Student;
import com.springboot.dao.StudentDao;
import com.springboot.mapper.StudentMapper;

@Repository("studentDao")
public class StudentDaoImp implements StudentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(Student student) {
		// String sql = "insert into student(sno,sname,ssex) values(?,?,?)";
		// Object[] args = { student.getSno(), student.getName(), student.getSex() };
		// int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		// return this.jdbcTemplate.update(sql, args, argTypes);


		String sql = "insert into student(id,name,sex,score) values(:id,:name,:sex,:score)";
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(this.jdbcTemplate.getDataSource());
		return npjt.update(sql, new BeanPropertySqlParameterSource(student));
	}

	@Override
	public int update(Student student) {
		String sql = "update student set sname = ?,ssex = ? where id = ?";
		Object[] args = { student.getName(), student.getSex(), student.getId() };
		int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		return this.jdbcTemplate.update(sql, args, argTypes);
	}

	@Override
	public int deleteById(Integer id) {
		String sql = "delete from student where id = ?";
		Object[] args = { id };
		int[] argTypes = { Types.INTEGER };
		return this.jdbcTemplate.update(sql, args, argTypes);
	}

	@Override
	public List<Map<String, Object>> queryStudentsListMap() {
		String sql = "select * from student";
		return this.jdbcTemplate.queryForList(sql);
	}

	@Override
	public Student queryStudentById(Integer id) {
		String sql = "select * from student where id = ?";
		Object[] args = { id };
		int[] argTypes = { Types.INTEGER };
		List<Student> studentList = this.jdbcTemplate.query(sql, args, argTypes, new StudentMapper());
		if (studentList != null && studentList.size() > 0) {
			return studentList.get(0);
		} else {
			return null;
		}
	}

}
