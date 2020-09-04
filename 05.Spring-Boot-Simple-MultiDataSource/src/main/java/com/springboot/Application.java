package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;


/**
 * 多数据源配置的方式2
 * 1.通过@Primary注解指定数据源
 * 2.去除自动装配的数据源的配置
 *  - 然后可以自己配置多个数据源了。
 *  但是这个目前还不知道怎么使用.
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class})
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
