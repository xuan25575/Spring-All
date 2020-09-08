package com.springboot;

import com.springboot.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.service.UserService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 具体可以看注解方法的描述。
 * 1.@RunWith
 *  使用了Spring的SpringJUnit4ClassRunner，以便在测试开始的时候自动创建Spring的应用上下文
 * 2.@SpringBootTest(classes = Application.class)
 *  Annotation that can be specified on a test class that runs Spring Boot based tests.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

	@Autowired
	private UserService userService;

	/**
	 *
	 * 发现第二次已经存入缓存中了。
	 * @throws Exception
	 */
	@Test
    public void test1() throws Exception {
		User user = userService.queryStudentById(1);
		System.out.println("主键为:" + user.getId() + "的用户姓名为：" + user.getUsername());

		User user2 = userService.queryStudentById(1);
		System.out.println("主键为:" + user2.getId() + "的用户姓名为：" + user2.getUsername());
    }
	
	@Test
	public void test2() throws Exception {

		User user = userService.queryStudentById(2);
		System.out.println("主键为:" + user.getId() + "的用户姓名为：" + user.getUsername());

		user.setUsername("小黑");
		this.userService.update(user);

		// 这个还是从缓存读取的数据，脏数据了
		User user2 = userService.queryStudentById(2);
		System.out.println("主键为:" + user2.getId() + "的用户姓名为：" + user2.getUsername());
	}
}
