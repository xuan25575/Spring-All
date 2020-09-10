package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  RBAC 权限模型
 *  1. 一个用户对应一个或者多个角色。
 *  2. 一个角色对应一个或者多个权限。
 *  3. 一个权限对应能够访问对应的API或url资源
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
