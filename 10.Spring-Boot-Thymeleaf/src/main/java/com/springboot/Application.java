package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 *
 * 从org/springframework/boot/spring-boot-autoconfigure/2.1.5.RELEASE
 * /spring-boot-autoconfigure-2.1.5.RELEASE.jar!/META-INF/spring.factories
 * 引入.
 * 1.ThymeleafAutoConfiguration 自动装配
 *   实现原理
 * 2.ThymeleafProperties 属性配置.可以查看
 * 3.语法使用
 *  等等.
 */
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
