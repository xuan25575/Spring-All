package com.springboot;

import com.springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootApplication
@EnableCaching // 开启缓存
public class Application implements CommandLineRunner {

//  这里可以注入
	// static 修饰不能注入
	// @Autowired
   // RedisTemplate redisTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	@Override
	public void run(String... args) throws Exception {

		UserServiceImpl userService = new UserServiceImpl();
		//System.out.println("main :" + redisTemplate);

		// 不能 直接实现new 得通过@Autowired  UserServiceImpl 注入。
		// 然后执行 可以拿到 redisTemplate
		userService.test();
	}
}
