package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 多数据源配置
 * 1.DBContextHolder 持有数据库的类型
 * 2.DynamicDataSource 切换数据源的方法
 * 3.DynamicDataSourceConfig 数据源的配置
 * 4.TargetDataSource 数据源切换的注解
 * 5.DataSourceAspect 切面配合切换数据源
 *
 * 逻辑：
 * 方法上面加上注解，就可以指定数据源来查查询数据。
 *
 * mybatis 配置查询待补充.
 */
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
