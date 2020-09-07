package com.springboot;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author  huang__2
 * Mybatis 自动装配 解析
 * 1.加载 /org/mybatis/spring/boot/mybatis-spring-boot-autoconfigure/1.3.1/
 * mybatis-spring-boot-autoconfigure-1.3.1.jar!/META-INF/spring.factories
 * 2.MybatisAutoConfiguration
 *   （1)必须要有数据源，SqlSessionFactory，SqlSessionFactoryBean.
 *    (2)这样就可以配置核心 SqlSessionFactory，SqlSessionTemplate，自然往后注入相关SqlSession 就可以使用了
 * 3.MybatisProperties 配置 Mybatis 相关属性
 * 4.MybatisAutoConfiguration.AutoConfiguredMapperScannerRegistrar#registerBeanDefinitions()
 *   如何扫描Mapper文件可知两种方式
 *    （1） logger.debug("Searching for mappers annotated with @Mapper"); 通过@Mapper 注解
 *    (2) logger.debug("Using auto-configuration base package '{}'", pkg); @MapperScan指定相关 基础包
 */
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
