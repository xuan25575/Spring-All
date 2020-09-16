package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 * PageHelper
 *  使用和配置
 *  https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
 *
 *  Springboot 集成
 *  PageHelperAutoConfiguration
 *  PageHelperProperties
 *
 *
 *
 *  mybatis plus
 *  官网:
 *  https://baomidou.com/guide/
 *
 *  1.使用起来很方便.
 *
 *  Springboot 集成
 *  MybatisPlusAutoConfiguration
 *  MybatisPlusProperties
 *
 */
@SpringBootApplication
@MapperScan("com.springboot.mapper")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
}
