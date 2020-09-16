package com.springboot;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

	@Autowired
	private UserService userService;

	@Test
	public void test() throws Exception {

		 User user = new User();
		 user.setUsername("faker");
		 user.setPassword("ac089b11709f9b9e9980e7c497268dfa");
		 user.setCreateDate(new Date());
		 user.setStatus("0");
		 this.userService.save(user);


//		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//		queryWrapper.eq("username","张三");
//		List<User> userList = this.userService.list(queryWrapper);
//		for (User u : userList) {
//			System.out.println(u.getUsername());
//		}
//
//		List<User> all = this.userService.list();
//		for (User u : all) {
//			System.out.println(u.getUsername());
//		}
//		


//		PageHelper.startPage(2, 2);
//		List<User> list = userService.list();
//		PageInfo<User> pageInfo = new PageInfo<User>(list);
//		List<User> result = pageInfo.getList();
//		for (User u : result) {
//			System.out.println(u.getUsername());
//		}
	}
}
