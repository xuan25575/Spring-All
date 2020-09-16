package com.springboot.service.impl;

import com.springboot.mapper.UserMapper;
import com.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.service.UserService;

import javax.annotation.Resource;

@Repository("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Resource
	private RedisTemplate redisTemplate;


	// 不能注入。
	public void test(){
		System.out.println("======"+redisTemplate);
	}


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
