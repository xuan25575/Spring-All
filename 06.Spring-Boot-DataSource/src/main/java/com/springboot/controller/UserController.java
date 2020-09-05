package com.springboot.controller;

import java.util.List;
import java.util.Map;

import com.springboot.annotation.TargetDataSource;
import com.springboot.constant.DataSourceNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("get-h")
	public List<Map<String, Object>> get(){
		String sql = "select * from student";
		return jdbcTemplate.queryForList(sql);
	}

	@RequestMapping("get-ry")
	@TargetDataSource(value = DataSourceNames.SEMatcherCOND)
	public List<Map<String, Object>> getRy(){
		String sql = "select * from sys_user";
		return jdbcTemplate.queryForList(sql);
	}
}
