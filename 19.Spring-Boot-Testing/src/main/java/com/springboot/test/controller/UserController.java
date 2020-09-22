package com.springboot.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.springboot.test.domain.User;
import com.springboot.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("user/{userName}")
	public User getUserByName(@PathVariable(value = "userName") String userName) {

		QueryWrapper<User> query = Wrappers.query();

		query.eq("username",userName);
		return this.userService.getOne(query);
	}

	@PostMapping("user/save")
	public void saveUser(@RequestBody User user) {
		this.userService.save(user);
	}
}
