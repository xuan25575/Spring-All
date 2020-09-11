package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.springboot.pojo.ConfigBean;
import com.springboot.pojo.TestConfigBean;

/**
 *
 * 1.测试banner的替换
 * 2.学习配置springboot 配置文件的读取
 * 3.active 激活配置文件的设置
 * 4.netty的入门案例
 **/
@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class,TestConfigBean.class})
//@ImportResource({"classpath:some-application.xml"})
public class ReadConfigApplication {


	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ReadConfigApplication.class);
		app.setAddCommandLineProperties(false);// 将命令行的配置关闭。
		app.run(args);
	}
}
