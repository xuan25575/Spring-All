package com.springboot.service.impl;

import com.springboot.mapper.UserMapper;
import com.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.service.UserService;

@Repository("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;


	@Override
	public User update(User user) {
		userMapper.insert(user);
		return user;
	}

	@Override
	public void deleteStudentById(Integer id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public User queryStudentById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
}
